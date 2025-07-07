package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class SwerveModule {
    /* Motors */
    public SparkMax drive_spark;
    public SparkMax steer_spark;

    /* Motor Configurations */
    public SparkMaxConfig drive_config;
    public SparkMaxConfig steer_config;

    /* Encoders */
    public CANcoder steer_cancoder;
    public RelativeEncoder drive_encoder;

    /* PID Controllers */
    public SimpleMotorFeedforward drive_feedforward_controller;
    public PIDController drive_controller;
    public PIDController angle_controller;

    public SwerveModuleState last_state;

    public double kMeterPerMotorRotation;

    /* Configuration */
    public boolean closedLoop;

    public SwerveModule(int steer_id, int drive_id, int angle_id, boolean reverse_drive) {
        /* Drive Motor */
        drive_spark = new SparkMax(drive_id, MotorType.kBrushless);
        drive_config = new SparkMaxConfig();

        drive_config.idleMode(IdleMode.kBrake);
        drive_config.inverted(reverse_drive);
        /*
         * By limiting the ramp rate to 0.5 seconds the peak current goes down from 120A
         * to 80A
         */
        drive_config.openLoopRampRate(0.25);

        /* Drive Encoder */
        drive_encoder = drive_spark.getEncoder();

        double kWheelCircumference = Units.inchesToMeters(Constants.Drive.kwheelDiameter) * Math.PI;
        kMeterPerMotorRotation = kWheelCircumference / Constants.Drive.kDriveRatio;

        drive_config.encoder.velocityConversionFactor(kMeterPerMotorRotation);
        drive_config.encoder.positionConversionFactor(kMeterPerMotorRotation);
        drive_spark.configure(drive_config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

        /* Steering Motor */
        steer_spark = new SparkMax(steer_id, MotorType.kBrushless);
        steer_config = new SparkMaxConfig();
        steer_config.inverted(true);
        steer_spark.configure(steer_config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

        /* Steering Encoder */
        steer_cancoder = new CANcoder(angle_id);

        /* Feedforward and PID Controllers */
        drive_feedforward_controller = new SimpleMotorFeedforward(Constants.Drive.kMotorkS, Constants.Drive.kMotorkV);
        drive_controller = new PIDController(Constants.Drive.kDriveP, Constants.Drive.kDriveI, Constants.Drive.kDriveD);

        angle_controller = new PIDController(Constants.Drive.kSteerP, Constants.Drive.kSteerI, Constants.Drive.kSteerD);
        angle_controller.setTolerance(Constants.Drive.kSteerTolerance);
        angle_controller.enableContinuousInput(-180, 180);

        last_state = new SwerveModuleState(0, new Rotation2d(0));

        closedLoop = true;
    }

    public void set_module_state(SwerveModuleState state) {
        /*
         * Optimize the state - this handles reversing direction and minimizes the
         * change in heading
         */
        state.optimize(last_state.angle);
        /* Calculate Feedforward Voltage */
        /* (m/s) / (m/rev) * (60s/min) = rev/min */
        double requestedMotorSpeed = (state.speedMetersPerSecond / kMeterPerMotorRotation) * 60;
        double feedfowardVoltage = MathUtil.clamp(drive_feedforward_controller.calculate(requestedMotorSpeed),
                -Constants.Drive.kFeedForwardMaxVoltage, Constants.Drive.kFeedForwardMaxVoltage);
                
        SmartDashboard.putNumber("Meter per Motor rev", kMeterPerMotorRotation);
        SmartDashboard.putNumber("Requested Wheel Speed mps", state.speedMetersPerSecond);
        SmartDashboard.putNumber("Requested Wheel Speed rpm", requestedMotorSpeed);

        SmartDashboard.putNumber("Feed Forward Voltage", feedfowardVoltage);

        /* Calculate PID Voltage */
        double closedLoopVoltage;

        if (closedLoop) {
            closedLoopVoltage = MathUtil.clamp(
                    drive_controller.calculate(drive_encoder.getVelocity(), requestedMotorSpeed),
                    -Constants.Drive.kClosedLoopMaxVoltage, Constants.Drive.kClosedLoopMaxVoltage);
        } else {
            closedLoopVoltage = 0;
        }

        /* Calculate Steer PID */
        double steerVoltage = MathUtil.clamp(angle_controller.calculate(get_raw_angle(), state.angle.getDegrees()),
                -Constants.Drive.kMaxSteerVoltage, Constants.Drive.kMaxSteerVoltage);

        /* Set the new powers to the SPARK Max controllers */
        drive_spark.setVoltage(feedfowardVoltage + closedLoopVoltage);
        steer_spark.setVoltage(steerVoltage);

        /* Update last angle for use next time */
        last_state = state;
    }

    public double get_raw_angle() {
        return steer_cancoder.getAbsolutePosition().getValueAsDouble() * 360 - 180;
    }

    public SwerveModulePosition get_module_position() {
        return new SwerveModulePosition(drive_encoder.getPosition(), Rotation2d.fromDegrees(this.get_raw_angle()));
    }

}

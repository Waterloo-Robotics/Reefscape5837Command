// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class DeAligiferSubsystem extends SubsystemBase {

  public SparkMax DeAligifierMotor;
  public SparkMaxConfig DeAligifierConfig;
  public RelativeEncoder DeAligifierEncoder;
  private double LOW = 30;
  private double HIGH = 45;

  public PIDController pid_controller;
  public double target_position;
  public Joystick controller;

  public boolean DeAligifier_found;

  private Timer homing_timer;


  /** Creates a new ExampleSubsystem. */
  public DeAligifierModule(int DeAligifierMotorID, Joystick drive_Controller) {
    this.DeAligifierMotor = new SparkMax(DeAligifierMotorID, MotorType.kBrushless);

    this.DeAligifierConfig = new SparkMaxConfig();
    this.DeAligifierConfig.smartCurrentLimit(20);
    this.DeAligifierConfig.inverted(false);

    this.DeAligifierMotor.configure(this.DeAligifierConfig, ResetMode.kNoResetSafeParameters,
            PersistMode.kPersistParameters);

    this.DeAligifierEncoder = this.DeAligifierMotor.getEncoder();

    this.homing_timer = new Timer();

    this.controller = drive_Controller;
    this.pid_controller = new PIDController(0.02, 0, 0);
    this.pid_controller.setTolerance(1);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command High() {
    return new FunctionalCommand(
      // Init Function
      () -> {
        this.target_position = this.HIGH;
      },
      // On Execute
      () -> {},
      // On End
      
      // isFinished
      () -> {}
      this
    );
  }

  public Command Low() {
    return new FunctionalCommand(
      // Init Function
      () -> {
        this.target_position = this.LOW;
      },
      // On Execute
      () -> {},
      // On End
      
      // isFinished
      () -> {}
      this
    );
  }
  public Command Home() {
    return new FunctionalCommand(
      // Init Function
      () -> {
        this.target_position = 0
      },
      // On Execute
      () -> {},
      // On End
      
      // isFinished
      () -> {
        return pid_controller.atSetpoint()
      }
      this
    );
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

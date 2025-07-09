// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.commands.ElevatorL4Command;
import frc.robot.commands.L4Homing;
import frc.robot.commands.L4HomingCommand;
public class ElevatorSubsystem extends SubsystemBase {

  public SparkMax rightMotor;
  public SparkMaxConfig rightConfig;
  public RelativeEncoder rightEncoder;

  public SparkMax leftMotor;
  public SparkMaxConfig leftConfig;
  public RelativeEncoder leftEncoder;

  public CommandJoystick controller;

  public Timer homing_timer;

  public double elevator_feedforward = 0.02;

  /* Elevator PID */
  public boolean elevator_found;

  public static double L1_HEIGHT = 9;
  public static double L2_HEIGHT = 22.5;
  public static double L3_HEIGHT = 50;
  public static double L4_HEIGHT = 91.25;

  public PIDController pid_controller;
  public double target_position;
  public boolean Manual_Override;

  /** Creates a new ExampleSubsystem. */
  public ElevatorSubsystem(int rightMotorID, int leftMotorID, CommandJoystick drive_Controller) {
    this.rightMotor = new SparkMax(rightMotorID, MotorType.kBrushless);
    this.rightEncoder = this.rightMotor.getEncoder();
    this.rightConfig = new SparkMaxConfig();
    this.rightConfig.idleMode(IdleMode.kBrake);
    this.rightConfig.openLoopRampRate(0.5);
    this.rightConfig.smartCurrentLimit(20);
    this.rightMotor.configure(this.rightConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    this.leftMotor = new SparkMax(leftMotorID, MotorType.kBrushless);
    this.leftEncoder = this.leftMotor.getEncoder();
    this.leftConfig = new SparkMaxConfig();
    this.leftConfig.follow(rightMotorID, true);
    this.leftConfig.idleMode(IdleMode.kBrake);
    this.leftConfig.openLoopRampRate(0.5);
    this.leftConfig.smartCurrentLimit(20);
    this.leftMotor.configure(this.leftConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    this.controller = drive_Controller;

    this.homing_timer = new Timer();

    this.elevator_found = false;
    this.pid_controller = new PIDController(0.043, 0, 0.0036);
    this.pid_controller.setTolerance(0.3);
    target_position = 0;
    Manual_Override = false;
  }

  @Override
  public void periodic() { }

  public void run_elevator() {
    if (elevator_found && !Manual_Override) {
      if (Math.abs(pid_controller.getError()) < 2) {
        pid_controller.setI(0.05);
      } else {
        pid_controller.setI(0);
      }

      double pid_term = pid_controller.calculate(rightEncoder.getPosition(), target_position);

      double auto_power = MathUtil.clamp(pid_term + elevator_feedforward, -0.9, 0.9);

      rightMotor.set(auto_power);

      if (pid_controller.atSetpoint()) {
        rightMotor.set(elevator_feedforward);
      }
    }
  }
  
  
   
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

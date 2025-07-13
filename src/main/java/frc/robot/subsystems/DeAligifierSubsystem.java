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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DeAligifierSubsystem extends SubsystemBase {

  public SparkMax DeAligifierMotor;
  public SparkMaxConfig DeAligifierConfig;
  public RelativeEncoder DeAligifierEncoder;
  private double LOW = 30;
  private double HIGH = 45;

  public PIDController pid_controller;
  public double target_position;

  public boolean DeAligifier_found;

  public Timer homing_timer;

  /** Creates a new ExampleSubsystem. */
  public DeAligifierSubsystem(int DeAligifierMotorID) {
    this.DeAligifierMotor = new SparkMax(DeAligifierMotorID, MotorType.kBrushless);

    this.DeAligifierConfig = new SparkMaxConfig();
    this.DeAligifierConfig.smartCurrentLimit(20);
    this.DeAligifierConfig.inverted(false);

    this.DeAligifierMotor.configure(this.DeAligifierConfig, ResetMode.kNoResetSafeParameters,
        PersistMode.kPersistParameters);

    this.DeAligifierEncoder = this.DeAligifierMotor.getEncoder();

    this.homing_timer = new Timer();

    this.pid_controller = new PIDController(0.02, 0, 0);
    this.pid_controller.setTolerance(1);
  }

  public void run_arm() {
    if (DeAligifier_found) {
      double pid_term = pid_controller.calculate(DeAligifierEncoder.getPosition(), target_position);

      double auto_power = MathUtil.clamp(pid_term, -0.9, 0.9);

      DeAligifierMotor.set(auto_power);

      if (pid_controller.atSetpoint()) {
        DeAligifierMotor.set(0);
      }
    }
  }

  public Command High() {
    return new FunctionalCommand(
        // Init Function
        () -> {
          this.target_position = this.HIGH;
        },
        // On Execute
        () -> {run_arm();},
        // On End
        interrupted -> {
        },
        // isFinished
        () -> (this.pid_controller.atSetpoint()),
        this);
  }

  public Command Low() {
    return new FunctionalCommand(
        // Init Function
        () -> {
          this.target_position = this.LOW;
        },
        // On Execute
        () -> {run_arm();},
        // On End
        interrupted -> {
        },
        // isFinished
        () -> (this.pid_controller.atSetpoint()),
        this);
  }

  public Command Home() {
    return new FunctionalCommand(
        // Init Function
        () -> {
          this.target_position = 0;
        },
        // On Execute
        () -> {run_arm();},
        // On End
        interrupted -> {
        },
        // isFinished
        () -> (this.pid_controller.atSetpoint()),
        this);
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

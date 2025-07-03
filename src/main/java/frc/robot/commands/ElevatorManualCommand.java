// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ElevatorManualCommand extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final ElevatorSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ElevatorManualCommand(ElevatorSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.Manual_Override = true;
  }

  @Override
  public void execute() {
    double power = MathUtil.applyDeadband(-m_subsystem.controller.getY(), 0.15, 1);
    power = MathUtil.clamp(power, -0.3, .3);
    m_subsystem.rightMotor.set(power + m_subsystem.elevator_feedforward);
  }

  @Override
  public void end(boolean interrupted) {
    m_subsystem.Manual_Override = false;
  }
}

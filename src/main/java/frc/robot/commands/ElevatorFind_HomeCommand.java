// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ElevatorFind_HomeCommand extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final ElevatorSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ElevatorFind_HomeCommand(ElevatorSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.homing_timer.restart();
    m_subsystem.rightMotor.set(-0.1);
    m_subsystem.elevator_found = false;
  }

  @Override
  public void end(boolean interrupted) {
   m_subsystem.elevator_found = true;
   m_subsystem.homing_timer.stop();
   m_subsystem.rightMotor.set( m_subsystem.elevator_feedforward);

   m_subsystem.rightEncoder.setPosition(0);
   m_subsystem.leftEncoder.setPosition(0);
   m_subsystem.target_position = 0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ((m_subsystem.rightMotor.getOutputCurrent() > 5) && m_subsystem.homing_timer.hasElapsed(0.1));
  }
}

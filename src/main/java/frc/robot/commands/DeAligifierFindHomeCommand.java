// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DeAligifierSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class DeAligifierFindHomeCommand extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DeAligifierSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DeAligifierFindHomeCommand(DeAligifierSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.homing_timer.restart();
    m_subsystem.DeAligifierMotor.set(-0.1);
    m_subsystem.DeAligifier_found = false;
  }

  @Override
  public void execute() {
    m_subsystem.DeAligifierMotor.set(-0.1);
  }


  @Override
  public void end(boolean interrupted) {
   m_subsystem.DeAligifier_found = true;
   m_subsystem.homing_timer.stop();
   m_subsystem.DeAligifierMotor.set(0);

   m_subsystem.DeAligifierEncoder.setPosition(0);
   m_subsystem.target_position = 0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ((m_subsystem.DeAligifierMotor.getOutputCurrent() > 2) && m_subsystem.homing_timer.hasElapsed(0.1));
  }
}
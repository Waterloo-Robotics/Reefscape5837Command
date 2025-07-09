// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ElevatorL4Command;
import frc.robot.commands.L4Homing;

/** An example command that uses an example subsystem. */
public class L4HomingCommand extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final ElevatorSubsystem m_subsystem;
 
  public Command Homing() {
    return Commands.sequence(new ElevatorL4Command((m_subsystem)), new L4Homing((m_subsystem)));
  }
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public L4HomingCommand(ElevatorSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
   
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_subsystem.pid_controller.atSetpoint();
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.sequence.HumanPlayerSequence;
import frc.robot.subsystems.OuttakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

/** An example command that uses an example subsystem. */
public class CreateHumanPlayerSequence extends Command {
  HumanPlayerSequence sequence;
  CommandJoystick joystick1;
  CommandJoystick joystick2;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public CreateHumanPlayerSequence(HumanPlayerSequence sequence, CommandJoystick joystick1, CommandJoystick joystick2) {
    this.sequence = sequence;
    this.joystick1 = joystick1;
    this.joystick2 = joystick2;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.sequence.PopulateSequence(joystick1, joystick2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.sequence.isComplete();
  }
}

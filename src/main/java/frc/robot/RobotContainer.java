// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ElevatorFind_HomeCommand;
import frc.robot.commands.ElevatorHomeCommand;
import frc.robot.commands.ElevatorL1Command;
import frc.robot.commands.ElevatorL2Command;
import frc.robot.commands.ElevatorL3Command;
import frc.robot.commands.ElevatorL4Command;
import frc.robot.commands.ElevatorManualCommand;
import frc.robot.commands.OuttakeEmptyCommand;
import frc.robot.commands.OuttakePositioningCoral;
import frc.robot.commands.OuttakeScoreCoralCommand;
import frc.robot.commands.OuttakeWaitForCoralCommand;
import frc.robot.commands.OuttakeStopCommand;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.OuttakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);

  private final CommandJoystick farmSim1 = new CommandJoystick(2);
  private final CommandJoystick farmSim2 = new CommandJoystick(3);

  // The robot's subsystems and commands are defined here...
  public final OuttakeSubsystem m_OuttakeSubsystem = new OuttakeSubsystem(22, 7, 6);
  public final ElevatorSubsystem m_ElevatorSubsystem = new ElevatorSubsystem(20, 21, farmSim1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    m_driverController.b().onTrue(m_OuttakeSubsystem.intakeCoralCommand());
    m_driverController.x().onTrue((new OuttakeScoreCoralCommand(m_OuttakeSubsystem)));

    /* Elevator Buttons */
    farmSim2.button(5).onTrue(new ElevatorHomeCommand(m_ElevatorSubsystem));
    farmSim1.button(12).onTrue(new ElevatorL1Command(m_ElevatorSubsystem));
    farmSim1.button(11).onTrue(new ElevatorL2Command(m_ElevatorSubsystem));
    farmSim1.button(6).onTrue(new ElevatorL3Command(m_ElevatorSubsystem));
    farmSim1.button(1).onTrue(new ElevatorL4Command(m_ElevatorSubsystem));
    farmSim1.button(5).onTrue(new ElevatorFind_HomeCommand(m_ElevatorSubsystem));
    farmSim2.button(7).onTrue(new ElevatorManualCommand(m_ElevatorSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_OuttakeSubsystem);
  }
}

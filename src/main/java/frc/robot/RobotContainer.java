// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.OuttakeScoreCoralCommand;
import frc.robot.subsystems.OuttakeSubsystem;
import frc.robot.commands.ElevatorFind_HomeCommand;
import frc.robot.commands.ElevatorHomeCommand;
import frc.robot.commands.ElevatorL1Command;
import frc.robot.commands.ElevatorL2Command;
import frc.robot.commands.ElevatorL3Command;
import frc.robot.commands.ElevatorL4Command;
import frc.robot.commands.ElevatorManualCommand;
import frc.robot.commands.DeAligifierFindHomeCommand;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.DeAligifierSubsystem;
import frc.robot.subsystems.DrivebaseSubsystem;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import com.pathplanner.lib.auto.NamedCommands;

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
  /* Joysticks */
  private final CommandXboxController m_driverController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);

  private final CommandJoystick farmSim1 = new CommandJoystick(2);
  private final CommandJoystick farmSim2 = new CommandJoystick(3);

  /* Robot Subsystems */
  public final DrivebaseSubsystem m_drivebaseSubsystem = new DrivebaseSubsystem(m_driverController);
  public final OuttakeSubsystem m_OuttakeSubsystem = new OuttakeSubsystem(22, 7, 6);
  public final ElevatorSubsystem m_ElevatorSubsystem = new ElevatorSubsystem(20, 21, farmSim1);
  public final DeAligifierSubsystem m_DeAligifierSubsystem = new DeAligifierSubsystem(25);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    NamedCommands.registerCommand("ElevatorL4Command", new ElevatorL4Command((m_ElevatorSubsystem)));
    NamedCommands.registerCommand("Outtake", new OuttakeScoreCoralCommand((m_OuttakeSubsystem)));
    NamedCommands.registerCommand("Intake", m_OuttakeSubsystem.intakeCoralCommand());
    configureBindings();

    m_drivebaseSubsystem.setDefaultCommand(m_drivebaseSubsystem.driverControlledCommand());
  }

  /* Configure trigger->command mappings */
  private void configureBindings() {
    m_driverController.b().onTrue(m_OuttakeSubsystem.intakeCoralCommand());
    m_driverController.x().onTrue((new OuttakeScoreCoralCommand(m_OuttakeSubsystem)));

    m_driverController.pov(180).onTrue(m_DeAligifierSubsystem.Home());
    m_driverController.a().onTrue(m_DeAligifierSubsystem.Low());
    m_driverController.b().onTrue(m_DeAligifierSubsystem.High());
    m_driverController.start().onTrue(new DeAligifierFindHomeCommand(m_DeAligifierSubsystem));

    /* Elevator Buttons */
    farmSim2.button(5).onTrue(new ElevatorHomeCommand(m_ElevatorSubsystem));
    farmSim1.button(12).onTrue(new ElevatorL1Command(m_ElevatorSubsystem));
    farmSim1.button(11).onTrue(new ElevatorL2Command(m_ElevatorSubsystem));
    farmSim1.button(6).onTrue(new ElevatorL3Command(m_ElevatorSubsystem));
    farmSim1.button(1).onTrue(new ElevatorL4Command(m_ElevatorSubsystem));
    farmSim1.button(5).onTrue(new ElevatorFind_HomeCommand(m_ElevatorSubsystem));
    farmSim2.button(7).onTrue(new ElevatorManualCommand(m_ElevatorSubsystem));
  }
}

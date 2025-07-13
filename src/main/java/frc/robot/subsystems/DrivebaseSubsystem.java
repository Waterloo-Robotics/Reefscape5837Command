// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class DrivebaseSubsystem extends SubsystemBase {

    SwerveBaseSubsystem swerve_modules;

    public DrivebaseSubsystem(CommandXboxController driver_controller) {
        swerve_modules = new SwerveBaseSubsystem(driver_controller);
    }

    public Command driverControlledCommand() {
        return Commands.run(swerve_modules::drive_xbox, this);
    }

    public Command driverControlledHoldAngle() {
        return Commands.run(swerve_modules::drive_xbox_hold_angle, this);
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

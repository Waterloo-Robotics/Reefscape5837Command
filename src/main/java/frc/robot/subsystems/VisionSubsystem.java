// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class VisionSubsystem extends SubsystemBase {
    Pose3d right_camera_result;


  /** Creates a new ExampleSubsystem. */
  public VisionSubsystem() {
  }

  @Override
  public void periodic() {
    right_camera_result = LimelightHelpers.getBotPose3d_TargetSpace("limelight-front");
    SmartDashboard.putNumber("Camera X", right_camera_result.getX());
    SmartDashboard.putNumber("Camera Y", right_camera_result.getY());
    SmartDashboard.putNumber("Camera z", right_camera_result.getZ());

    SmartDashboard.putNumber("ID", LimelightHelpers.getFiducialID("limelight-front"));
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

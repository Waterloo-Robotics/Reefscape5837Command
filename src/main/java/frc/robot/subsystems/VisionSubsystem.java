
package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class VisionSubsystem extends SubsystemBase {
  CameraSubsystem rightCamera;
  CameraSubsystem leftCamera;

  public VisionSubsystem() {
    // TODO: Create the rightCamera and leftCamera objects
  }

  @Override
  public void periodic() {
    // TODO: Put the results from each camera onto the dashboard, see example below


    /*
      right_camera_result = LimelightHelpers.getBotPose3d_TargetSpace("limelight-front");
      SmartDashboard.putNumber("Camera X", right_camera_result.getX());
      SmartDashboard.putNumber("Camera Y", right_camera_result.getY());
      SmartDashboard.putNumber("Camera z", right_camera_result.getZ());

      SmartDashboard.putNumber("ID", LimelightHelpers.getFiducialID("limelight-front"));
    */
  }
}

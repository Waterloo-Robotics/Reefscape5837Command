package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.PoseEstimate;

/** {@link CameraSubsystem} represents a single physical camera on the robot. */
public class CameraSubsystem extends SubsystemBase {
    private String cameraName;
    public PoseEstimate cameraResult;

    public CameraSubsystem(String cameraName) {
        this.cameraName = cameraName;
    }

    @Override
    public void periodic() {
        // TODO: Get the latest robot position from the camera
    }
}

package frc.robot.subsystems;

import java.util.List;

import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.path.Waypoint;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants;
import frc.robot.sequence.HumanPlayerSequence;
import frc.robot.sequence.ReefSequence;

public class TeleOpPathPlannerFactory {
    /* Velocity Limits */
    private static final double maxVelcoity_m_s = Constants.Drivebase.kMaxDriveSpeed_m_s;
    // TODO: This is just a random value for now
    private static final double maxAcceleration_m_s2 = Units.feetToMeters(1);

    /* Rotational Limits */
    private static final double maxRotationalVelocity_rad_s = Units
            .rotationsToRadians(Constants.Drivebase.kMaxSpinSpeed_rev_s);
    // TODO: This is just a random value for now
    private static final double maxRotationalAcceleration_rad_s2 = Units.rotationsToRadians(0.5);

    /**
     * Limits the speed and acceleration of generated paths so that the robot
     * is capable of executing them.
     */
    static final PathConstraints constraints = new PathConstraints(maxVelcoity_m_s, maxAcceleration_m_s2,
            maxRotationalVelocity_rad_s, maxRotationalAcceleration_rad_s2);

    /** List of waypoints that the path will be generated from. */
    List<Waypoint> waypoints;

    /** Generated Path */
    PathPlannerPath path;

    public TeleOpPathPlannerFactory() {

    }

    public PathPlannerPath CreatePathToReef(Pose3d currentPosition, ReefSequence reefPosition) {
        /*
         * TODO: Using the current position and where we want to be on the reef, create
         * a
         * PathPlannerPath and store it in 'path'. This will be returned to whoever
         * requested
         * a path to the reef for them to run.
         */

        // Write code here

        // Prevent the path from being flipped if the coordinates are already correct
        path.preventFlipping = true;

        return path;
    }

    public PathPlannerPath CreatePathToCoralStation(Pose3d currentPosition, HumanPlayerSequence humanPlayerPosition) {
        /*
         * TODO: Using the current position and where we want to be on the coral
         * station, create a
         * PathPlannerPath and store it in 'path'. This will be returned to whoever
         * requested
         * a path to the coral station for them to run.
         */

        // Write code here

        // Prevent the path from being flipped if the coordinates are already correct
        path.preventFlipping = true;

        return path;
    }
}

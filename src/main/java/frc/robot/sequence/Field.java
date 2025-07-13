package frc.robot.sequence;

/**
 * Contains enumerations for creating commands for the robot to follow
 */
public class Field {

    /** Which Physical human player station, left or right, relative to the driver's POV */
    enum FieldSide {
        UNKNOWN,
        RIGHT,
        LEFT
    }

    /** Within the human player station, what side do we want to be on? */
    enum HPSide {
        UNKNOWN,
        RIGHT,
        LEFT
    }

    /** Which Physical side of the reef. One is the side facing the driver station and increasing counter-clockwise */
    enum ReefSide {
        UNKNOWN,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
    }

    /** Within the {@link ReefSide}, which branch  */
    enum BranchSide {
        UNKNOWN,
        RIGHT,
        LEFT
    }

    /** What height of the reef do we want to be on? */
    enum Height {
        UNKNOWN,
        L1,
        L2,
        L3,
        L4
    }

}

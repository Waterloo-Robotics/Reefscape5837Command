package frc.robot.sequence;

public class Field {
    /* Which Physical human player station, left or right, on the field? */
    enum FieldSide {
        UNKNOWN,
        RIGHT,
        LEFT
    }

    /* Within the human player station, what side do we want to be on? */
    enum HPSide {
        UNKNOWN,
        RIGHT,
        LEFT
    }

    /* Which Physical side of the reef? */
    enum ReefSide {
        UNKNOWN,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
    }

    /* Within the reef, what side do we want to be on? */
    enum BranchSide {
        UNKNOWN,
        RIGHT,
        LEFT
    }

    /* what height of the reef do we want to be on? */
    enum Height {
        UNKNOWN,
        L1,
        L2,
        L3,
        L4
    }

}

package frc.robot.sequence;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

public class ReefSequence {
    enum SequenceAge {
        OLD,
        NEW
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

    enum LRside {
        /* Within the reef, what side do we want to be on? */
        UNKNOWN,
        RIGHT,
        LEFT
    }

    enum Height {
        /* what height of the reef do we want to be on? */
        UNKNOWN,
        L1,
        L2,
        L3,
        L4
    }

    public SequenceAge age;
    public ReefSide reefside;
    public LRside lrside;
    public Height height;

    public ReefSequence() {
        this.age = SequenceAge.NEW;
        this.reefside = reefside.UNKNOWN;
        this.lrside = LRside.UNKNOWN;
        this.height = Height.UNKNOWN;
    }

    public Boolean isComplete() {
        return (this.reefside != reefside.UNKNOWN) &&
                (this.lrside != LRside.UNKNOWN) &&
                (this.height != Height.UNKNOWN);
    }

    public void PopulateSequence (CommandJoystick joystick1, CommandJoystick joystick2){
        if (joystick1.button(4).getAsBoolean()){ 
            this.reefside = ReefSide.ONE;
        }
        if (joystick1.button(5).getAsBoolean()){ 
            this.reefside = ReefSide.TWO;
        }
        if (joystick1.button(8).getAsBoolean()){ 
            this.reefside = ReefSide.THREE;
        }
        if (joystick1.button(9).getAsBoolean()){ 
            this.reefside = ReefSide.FOUR;
        }
        if (joystick1.button(10).getAsBoolean()){ 
            this.reefside = ReefSide.FIVE;
        }
        if (joystick1.button(3).getAsBoolean()){ 
            this.reefside = ReefSide.SIX;
        }

        if (joystick2.button(3).getAsBoolean()){ 
            this.lrside = LRside.LEFT;
        } else if (joystick2.button(4).getAsBoolean()){ 
            this.lrside = LRside.RIGHT;
        }

        if (joystick1.button(12).getAsBoolean()){ 
            this.height = Height.L1;
        }
        if (joystick1.button(11).getAsBoolean()){ 
            this.height = Height.L2;
        }
        if (joystick1.button(6).getAsBoolean()){ 
            this.height = Height.L3;
        }
        if (joystick1.button(1).getAsBoolean()){ 
            this.height = Height.L4;
        }

        SmartDashboard.putString("Age", this.age.toString());
        SmartDashboard.putString("ReefSide", this.reefside.toString());
        SmartDashboard.putString("LRSide", this.lrside.toString());
        SmartDashboard.putString("Height", this.height.toString());
    }

}

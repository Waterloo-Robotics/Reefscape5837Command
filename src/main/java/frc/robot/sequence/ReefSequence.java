package frc.robot.sequence;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

public class HumanPlayerSequence {
    enum SequenceAge {
        OLD,
        NEW
    }

    /* Which Physical human player station, left or right, on the field? */
    enum FieldSide {
        UNKNOWN,
        RIGHT,
        LEFT
    }

    enum HPside {
        /* Within the human player station, what side do we want to be on? */
        UNKNOWN,
        RIGHT,
        LEFT
    }

    public SequenceAge age;
    public FieldSide fieldside;
    public HPside hpside;

    public HumanPlayerSequence() {
        this.age = SequenceAge.NEW;
        this.fieldside = FieldSide.UNKNOWN;
        this.hpside = HPside.UNKNOWN;
    }

    public Boolean isComplete() {
        return (this.fieldside != FieldSide.UNKNOWN) &&
                (this.hpside != HPside.UNKNOWN);
    }

    public void PopulateSequence (CommandJoystick joystick1, CommandJoystick joystick2){
        if (joystick2.button(1).getAsBoolean()){ 
            this.fieldside = FieldSide.LEFT;
        } else if (joystick2.button(2).getAsBoolean()){ 
            this.fieldside = FieldSide.RIGHT;
        }

        if (joystick2.button(3).getAsBoolean()){ 
            this.hpside = HPside.LEFT;
        } else if (joystick2.button(4).getAsBoolean()){ 
            this.hpside = HPside.RIGHT;
        }

        SmartDashboard.putString("Age", this.age.toString());
        SmartDashboard.putString("FieldSide", this.fieldside.toString());
        SmartDashboard.putString("HPSide", this.hpside.toString());
    }

}

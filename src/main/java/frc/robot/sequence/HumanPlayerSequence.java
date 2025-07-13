package frc.robot.sequence;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.sequence.Field.FieldSide;
import frc.robot.sequence.Field.HPSide;

public class HumanPlayerSequence {
    enum SequenceAge {
        OLD,
        NEW
    }

    public SequenceAge age;
    public FieldSide fieldside;
    public HPSide hpside;

    public HumanPlayerSequence() {
        this.age = SequenceAge.NEW;
        this.fieldside = FieldSide.UNKNOWN;
        this.hpside = HPSide.UNKNOWN;
    }

    public Boolean isComplete() {
        return (this.fieldside != FieldSide.UNKNOWN) &&
                (this.hpside != HPSide.UNKNOWN);
    }

    public void PopulateSequence (CommandJoystick joystick1, CommandJoystick joystick2){
        if (joystick2.button(1).getAsBoolean()){ 
            this.fieldside = FieldSide.LEFT;
        } else if (joystick2.button(2).getAsBoolean()){ 
            this.fieldside = FieldSide.RIGHT;
        }

        if (joystick2.button(3).getAsBoolean()){ 
            this.hpside = HPSide.LEFT;
        } else if (joystick2.button(4).getAsBoolean()){ 
            this.hpside = HPSide.RIGHT;
        }

        SmartDashboard.putString("Age", this.age.toString());
        SmartDashboard.putString("FieldSide", this.fieldside.toString());
        SmartDashboard.putString("HPSide", this.hpside.toString());
    }

}

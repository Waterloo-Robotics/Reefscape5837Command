package frc.robot.sequence;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.sequence.Field.ReefSide;
import frc.robot.sequence.Field.BranchSide;
import frc.robot.sequence.Field.Height;

public class ReefSequence {
    enum SequenceAge {
        OLD,
        NEW
    }    

    public SequenceAge age;
    public ReefSide reefside;
    public BranchSide lrside;
    public Height height;

    public ReefSequence() {
        this.age = SequenceAge.NEW;
        this.reefside = ReefSide.UNKNOWN;
        this.lrside = BranchSide.UNKNOWN;
        this.height = Height.UNKNOWN;
    }

    public Boolean isComplete() {
        return (this.reefside != ReefSide.UNKNOWN) &&
                (this.lrside != BranchSide.UNKNOWN) &&
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
            this.lrside = BranchSide.LEFT;
        } else if (joystick2.button(4).getAsBoolean()){ 
            this.lrside = BranchSide.RIGHT;
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

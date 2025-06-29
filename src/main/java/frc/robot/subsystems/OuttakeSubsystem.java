// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Commands;

public class OuttakeSubsystem extends SubsystemBase {

  public SparkMax outtakeMotor;
  public SparkMaxConfig outtakeConfig;
  public RelativeEncoder outtakeEncoder;
  
  public DigitalInput backBeam;
  public DigitalInput frontBeam;

  /** Creates a new ExampleSubsystem. */
  public OuttakeSubsystem(int outtakeMotorID, int backBeamID, int frontBeamID) {
    this.outtakeMotor = new SparkMax(outtakeMotorID, MotorType.kBrushless);

    this.outtakeConfig = new SparkMaxConfig();
    this.outtakeConfig.smartCurrentLimit(24);
    this.outtakeMotor.configure(this.outtakeConfig, ResetMode.kNoResetSafeParameters,
            PersistMode.kPersistParameters);
    
    this.backBeam = new DigitalInput(backBeamID);
    this.frontBeam = new DigitalInput(frontBeamID);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command waitForCoralCommand() {
    return new FunctionalCommand(
      // Init Function
      () -> {
        if (!this.coralInFront()) {
          this.outtakeMotor.set(.65);
        }
      },
      // On Execute
      () -> {},
      // On End
      interrupted -> this.outtakeMotor.set(0.25),
      // isFinished
      () -> (this.coralInFront() && this.coralInBack()),
      this
    );
  }

  public Command positionCoralCommand() {
    return new FunctionalCommand(
      // Init Function
      () -> {
        if (this.coralInBack()) {
          this.outtakeMotor.set(.25);
        }
      },
      // On Execute
      () -> {},
      // On End
      interrupted -> this.outtakeMotor.set(0),
      // isFinished
      () -> (!this.coralInBack()),
      this
    );
  }

  public Command intakeCoralCommand() {
    return Commands.sequence(this.waitForCoralCommand(), this.positionCoralCommand());
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean coralInFront() {
    // Query some boolean state, such as a digital sensor.
    return !this.frontBeam.get();
  }

  public boolean coralInBack() {
    // Query some boolean state, such as a digital sensor.
    return !this.backBeam.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

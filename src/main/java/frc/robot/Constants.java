// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class Drive {
    public static final int kMotorkS = 0;
    /* kV for in Volts * RPM */
    public static final int kMotorkV = 473;
    public static final double kwheelDiameter = Units.inchesToMeters(4.0);
    public static final double kDriveRatio = 6.12 / 1.0;
    /* Drive PID */
    public static final double kDriveP = 0.02;
    public static final double kDriveI = 0.0;
    public static final double kDriveD = 0.0;
    public static final double kFeedForwardMaxVoltage = 12;
    public static final double kClosedLoopMaxVoltage = 12;
    /* Steer PID */
    public static final double kSteerP = 0.048;
    public static final double kSteerI = 0.0;
    public static final double kSteerD = 0.0;
    public static final double kSteerTolerance = 1;
    public static final double kMaxSteerVoltage = 6;
  }

  public static class Drivebase {
    public static final double kWheelOffset = Units.inchesToMeters(12);
    public static final double kMaxDriveSpeed = Units.feetToMeters(14);
    public static final double kMaxSpinSpeed = 1.5; // Rps
  }
}

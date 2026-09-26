// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import org.wpilib.command2.SubsystemBase;
import org.wpilib.hardware.bus.CANPort;

/** Simple subsystem wrapping a single REV SparkMax on CAN. */
public class MotorSubsystem extends SubsystemBase {
  // TODO: picks this ID to match your wiring. You can also move it to Constants.
  private static final int MOTOR_CAN_ID = 1;

  private final SparkMax motor =
      new SparkMax(CANPort.CAN_S0, MOTOR_CAN_ID, MotorType.kBrushless);

  /** Sets the motor output (-1.0 to 1.0). */
  public void setSpeed(double speed) {
    motor.setThrottle(speed);
  }

  /** Stops the motor. */
  public void stop() {
    motor.stopMotor();
  }
}

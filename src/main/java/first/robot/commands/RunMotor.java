// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot.commands;

import org.wpilib.command2.Command;
import first.robot.subsystems.MotorSubsystem;

/** Runs the motor at a fixed speed until the command ends or is interrupted. */
public class RunMotor extends Command {
  private final MotorSubsystem subsystem;
  private final double speed;

  /** @param speed motor output from -1.0 to 1.0 */
  public RunMotor(MotorSubsystem subsystem, double speed) {
    this.subsystem = subsystem;
    this.speed = speed;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    System.out.println("RunMotor initialized at speed " + speed);
  }

  @Override
  public void execute() {
    subsystem.setSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.stop();
    System.out.println("RunMotor ended (interrupted=" + interrupted + ")");
  }

  @Override
  public boolean isFinished() {
    return false; // runs until interrupted (e.g. whileTrue button released)
  }
}

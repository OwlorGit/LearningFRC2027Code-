// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.command2.Command;
import org.wpilib.command2.button.CommandGamepad;
import first.robot.Constants.OperatorConstants;
import first.robot.commands.Autos;
import first.robot.commands.ExampleCommand;
import first.robot.commands.RunMotor;
import first.robot.subsystems.ExampleSubsystem;
import first.robot.subsystems.MotorSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
  private final MotorSubsystem motorSubsystem = new MotorSubsystem();

  private final CommandGamepad driverController =
      new CommandGamepad(OperatorConstants.DRIVER_CONTROLLER_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Default: motor spins continuously at 30% whenever nothing else uses the subsystem.
    motorSubsystem.setDefaultCommand(new RunMotor(motorSubsystem, 0.3));
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link org.wpilib.command2.button.CommandGenericHID}'s
   * subclasses for {@link CommandGamepad Gamepad} gamepads or {@link
   * org.wpilib.command2.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings() {
    // Run `ExampleCommand` while the Gamepad's left face button is held.
    // This lets you see its initialize/execute/end prints in sim.
    driverController.faceLeft().whileTrue(new ExampleCommand(exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Gamepad's right face button is pressed,
    // cancelling on release. Swapped to RunMotor so you can see/feel the motor in sim.
    driverController.faceRight().whileTrue(new RunMotor(motorSubsystem, 0.5));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Run motor at 30% for 2 seconds in autonomous so you can see it with zero button presses.
    return new RunMotor(motorSubsystem, 0.3).withTimeout(2.0);
  }
}

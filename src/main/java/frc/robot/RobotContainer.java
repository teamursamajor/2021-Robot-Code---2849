// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ClimbCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // define the joystick.

  XboxController xboxController = new XboxController(1); // Creates an XboxController on port 0.

  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

  private final DriveCommand driveCommand = new DriveCommand(driveSubsystem);

  private final ClimbCommand climbCommand = new ClimbCommand(climbSubsystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    driveSubsystem.setDefaultCommand(driveCommand);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //JoystickButton driveButton = new JoystickButton(xboxController, 0); // Creates a new JoystickButton object for
                                                                        // button 1 on exampleStick
    // Binds an ExampleCommand to be scheduled when the trigger of the example
    // joystick is pressed

    /*
    new JoystickButton(xboxController, XboxController.Axis.kLeftX.value)
    .and(new JoystickButton(xboxController, XboxController.Axis.kLeftY.value)).and(new JoystickButton(xboxController,XboxController.Axis.kRightX.value)).
    and(new JoystickButton(xboxController, XboxController.Axis.kRightY.value)).whenActive(new DriveCommand(driveSubsystem));
    */
    //driveButton.whileHeld(new DriveCommand(driveSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}

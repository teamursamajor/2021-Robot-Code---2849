// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.ClimbCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static frc.robot.Constants.*;
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

  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem DRIVE_SUBSYSTEM = new DriveSubsystem();

  private final DriveCommand DRIVE_COMMAND = new DriveCommand(DRIVE_SUBSYSTEM);

  private final ClimbSubsystem CLIMB_SUBSYSTEM = new ClimbSubsystem();

  private final ClimbCommand CLIMB_COMMAND = new ClimbCommand(CLIMB_SUBSYSTEM);

  private final ShooterSubsystem SHOOTER_SUBSYSTEM = new ShooterSubsystem();

  private final ShooterCommand SHOOTER_COMMAND = new ShooterCommand(SHOOTER_SUBSYSTEM);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    DRIVE_SUBSYSTEM.setDefaultCommand(DRIVE_COMMAND);
    CLIMB_SUBSYSTEM.setDefaultCommand(CLIMB_COMMAND);
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
    JoystickButton shootButton = new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kA.value);
    shootButton.whileHeld(SHOOTER_COMMAND);
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

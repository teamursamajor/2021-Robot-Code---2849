// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static frc.robot.Constants.XBOX_CONTROLLER;
import frc.robot.commands.AlignCommand;
import frc.robot.commands.DistanceCommand;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
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

  private final IntakeSubsystem INTAKE_SUBSYSTEM = new IntakeSubsystem();
  private final ColorSubsystem COLOR_SUBSYSTEM = new ColorSubsystem();

  private final IntakeCommand INTAKE_COMMAND = new IntakeCommand(INTAKE_SUBSYSTEM, COLOR_SUBSYSTEM);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the button bindings
    DRIVE_SUBSYSTEM.setDefaultCommand(DRIVE_COMMAND);
    CLIMB_SUBSYSTEM.setDefaultCommand(CLIMB_COMMAND);
    INTAKE_SUBSYSTEM.setDefaultCommand(INTAKE_COMMAND);
    SHOOTER_SUBSYSTEM.setDefaultCommand(SHOOTER_COMMAND);
    COLOR_SUBSYSTEM.setDefaultCommand(INTAKE_COMMAND);
    configureButtonBindings();
    LOGGER.start();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XBOX_CONTROLLER}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton shootButton = new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kA.value);
    shootButton.whileHeld(SHOOTER_COMMAND);
    //JoystickButton driveButton = new JoystickButton(XBOX_CONTROLLER, 0); // Creates a new JoystickButton object for
                                                                        // button 1 on exampleStick
    // Binds an ExampleCommand to be scheduled when the trigger of the example
    // joystick is pressed


    //new JoystickButton(XBOX_CONTROLLER, XBOX_CONTROLLER.Button.kY.value).whenPressed(new AutoAlignCommand(DRIVE_SUBSYSTEM));
    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kX.value).whenPressed((new AlignCommand(DRIVE_SUBSYSTEM)).withTimeout(5));
    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kB.value).whenPressed((new DistanceCommand(DRIVE_SUBSYSTEM)).withTimeout(5));
    //driveButton.whileHeld(new DriveCommand(driveSubsystem));
  }

  /***
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}

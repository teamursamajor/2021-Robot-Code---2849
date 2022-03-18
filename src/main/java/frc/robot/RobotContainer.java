// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DistanceCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.autoCommands.AlignCommand;
import frc.robot.commands.autoCommands.AutoCommand1;
import frc.robot.commands.autoCommands.AutoCommand2;
import frc.robot.commands.autoCommands.AutoCommand3;
import frc.robot.commands.autoCommands.AutoCommand4;
import frc.robot.commands.manualCommands.ActuatorCommand;
import frc.robot.commands.manualCommands.ManualBeltCommand;
import frc.robot.commands.manualCommands.ManualClimbCommand;
import frc.robot.commands.manualCommands.ManualIntakeCommand;
import frc.robot.commands.manualCommands.ShooterCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import java.util.function.BooleanSupplier;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  public final DriveSubsystem DRIVE_SUBSYSTEM = new DriveSubsystem();

  // private final BeltSubsystem BELT_SUBSYSTEM = new BeltSubsystem();

  final DriveCommand DRIVE_COMMAND = new DriveCommand(DRIVE_SUBSYSTEM);

  private final ClimbSubsystem CLIMB_SUBSYSTEM = new ClimbSubsystem();

  public final ShooterSubsystem SHOOTER_SUBSYSTEM = new ShooterSubsystem();

  public final IntakeSubsystem INTAKE_SUBSYSTEM = new IntakeSubsystem();
  // Auto Commands
  public final Command m_driveShootAuto = new AutoCommand1(DRIVE_SUBSYSTEM, INTAKE_SUBSYSTEM, SHOOTER_SUBSYSTEM);
  private final Command m_driveAuto = new AutoCommand2(DRIVE_SUBSYSTEM);
  private final Command m_nothingAuto = new AutoCommand3();

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // set up the autoCommand
    m_chooser.setDefaultOption("Drive and Shoot Auto", m_driveShootAuto);
    m_chooser.addOption("Drive Auto", m_driveAuto);
    m_chooser.addOption("Nothing Auto", m_nothingAuto);

    SmartDashboard.putData(m_chooser);

    SmartDashboard.putNumber("Shooting Multiplier", 0.95);
    SmartDashboard.putBoolean("Actuator Open", false);
    

    // set the drive default command
    // DRIVE_SUBSYSTEM.setDefaultCommand(DRIVE_COMMAND);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XBOX_CONTROLLER}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //  new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kX.value)
    //     .whenPressed(new AutoShooterCommand(SHOOTER_SUBSYSTEM, INTAKE_SUBSYSTEM));

    //  new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kY.value)
    //     .whenPressed(
    //       (new AlignCommand(DRIVE_SUBSYSTEM))
    //     .withTimeout(5)
    //     .andThen(new DistanceCommand(DRIVE_SUBSYSTEM).withTimeout(5)));

    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kA.value)
        .whileHeld(new ManualBeltCommand(INTAKE_SUBSYSTEM, true));

    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kY.value)
        .whileHeld(new ManualBeltCommand(INTAKE_SUBSYSTEM, false));

    /*
    BooleanSupplier rightTrigger = () -> XBOX_CONTROLLER.getRightTriggerAxis() > 0.2;
    new Trigger(rightTrigger).whileActiveContinuous(new ShooterCommand(SHOOTER_SUBSYSTEM));
    */

    BooleanSupplier leftTrigger = () -> XBOX_CONTROLLER.getLeftTriggerAxis() > 0.2;
    new Trigger(leftTrigger).whileActiveContinuous(new ManualIntakeCommand(INTAKE_SUBSYSTEM));

    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kBack.value).whenPressed(new AlignCommand(DRIVE_SUBSYSTEM).withTimeout(5).andThen(new DistanceCommand(DRIVE_SUBSYSTEM).withTimeout(5)));
    /* 

    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kRightBumper.value)
        .whenPressed(new ClimbCommand(CLIMB_SUBSYSTEM, true));
    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kLeftBumper.value)
        .whenPressed(new ClimbCommand(CLIMB_SUBSYSTEM, false));
    */
    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kRightBumper.value)
        .whileHeld(new ManualClimbCommand(CLIMB_SUBSYSTEM, true, .5));

    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kLeftBumper.value)
        .whileHeld(new ManualClimbCommand(CLIMB_SUBSYSTEM, false, .5));

    

    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kB.value)
        .whenPressed(new ManualClimbCommand(CLIMB_SUBSYSTEM, true, .2));

   // new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kX.value)
       // .whileHeld(new ActuatorCommand(CLIMB_SUBSYSTEM, false));

    new JoystickButton(XBOX_CONTROLLER, XboxController.Button.kX.value)
        .whenPressed(new ActuatorCommand(CLIMB_SUBSYSTEM));

    
    
  }

  

/***
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}

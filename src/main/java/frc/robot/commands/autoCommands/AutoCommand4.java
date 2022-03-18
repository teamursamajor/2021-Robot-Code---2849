package frc.robot.commands.autoCommands;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.AutoDriveCommand;
import frc.robot.commands.DistanceCommand;
import frc.robot.commands.manualCommands.ShooterCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/** An example command that uses an example subsystem. */
public class AutoCommand4 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem DRIVE_SUBSYSTEM;

  private final IntakeSubsystem INTAKE_SUBSYSTEM;
  private final ShooterSubsystem SHOOTER_SUBSYSTEM;

  public AutoCommand4(
      DriveSubsystem driveSubsystem,
      IntakeSubsystem intakeSubsystem,
      ShooterSubsystem shooterSubsystem) {
    DRIVE_SUBSYSTEM = driveSubsystem;
    INTAKE_SUBSYSTEM = intakeSubsystem;
    SHOOTER_SUBSYSTEM = shooterSubsystem;
    addRequirements(driveSubsystem, shooterSubsystem);
    setName("Auto Drive (Command)");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    new AutoDriveCommand(DRIVE_SUBSYSTEM)
        .withTimeout(1.75)
        .andThen(new AlignCommand(DRIVE_SUBSYSTEM))
        .andThen(new DistanceCommand(DRIVE_SUBSYSTEM))
        .andThen(new ShooterCommand(SHOOTER_SUBSYSTEM))
        .withTimeout(7.0)
        .schedule();
    INTAKE_SUBSYSTEM.beltSpark.set(1.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    INTAKE_SUBSYSTEM.beltSpark.set(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

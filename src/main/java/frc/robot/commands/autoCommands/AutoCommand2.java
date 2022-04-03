package frc.robot.commands.autoCommands;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.AutoDriveCommand;
import frc.robot.commands.DistanceCommand;
import frc.robot.subsystems.DriveSubsystem;

/** An example command that uses an example subsystem. */
public class AutoCommand2 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem DRIVE_SUBSYSTEM;

  public AutoCommand2(DriveSubsystem driveSubsystem) {
    DRIVE_SUBSYSTEM = driveSubsystem;
    addRequirements(driveSubsystem);
    setName("Auto Drive (Command)");
  }
  public String toString(){
    return "Auto Command2";
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    new AutoDriveCommand(DRIVE_SUBSYSTEM)
        .withTimeout(2)
        .schedule();
        System.out.println("Aligned");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

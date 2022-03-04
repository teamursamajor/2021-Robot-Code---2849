package frc.robot.commands.manualCommands;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

/** An example command that uses an example subsystem. */
public class ManualBeltCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem INTAKE_SUBSYSTEM;

  private final double BELT_SPEED = -1.0;

  public ManualBeltCommand(IntakeSubsystem intakeSubsystem) {
    INTAKE_SUBSYSTEM = intakeSubsystem;
    addRequirements(intakeSubsystem);
    setName("Auto Drive (Command)");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    INTAKE_SUBSYSTEM.beltSpark.set(BELT_SPEED);
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
    return false;
  }
}
package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {

    private final IntakeSubsystem INTAKE_SUBSYSTEM;

    public IntakeCommand(IntakeSubsystem intake, ColorSubsystem color) {
        INTAKE_SUBSYSTEM = intake;
        addRequirements(intake, color);
        setName("Intake (Command)");
    }

    @Override
    public void initialize() {
        log(INTAKE_SUBSYSTEM.getName(), "intialzied");
    }

    @Override
    public void execute() {
        log(INTAKE_SUBSYSTEM.getName(), "Executing");
    }

    @Override
    public void end(boolean interrupted) {
        log(INTAKE_SUBSYSTEM.getName(), "Done");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

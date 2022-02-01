package frc.robot.commands;

import frc.robot.subsystems.CatapultSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ClimbSubsystem climbSubsystem;

    public ClimbCommand(ClimbSubsystem subsystem){
        climbSubsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        System.out.println("intialzied");
      }
    
    @Override
    public void execute() {
        
    }

}
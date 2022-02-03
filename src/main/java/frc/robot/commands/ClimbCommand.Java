package frc.robot.commands;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;


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
    

    public void execute() {
        System.out.println("Execute");
        boolean isAPressed = Constants.XBOXCONTROLLER.getAButtonPressed();
        boolean isBPressed = Constants.XBOXCONTROLLER.getBButtonPressed();
        if(isAPressed){

        }
    }
        

    

    @Override
    public void end(boolean interrupted) {
        System.out.println("Done");
    }

    @Override
    public boolean isFinished(){
        return false;
    }


}
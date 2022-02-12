package frc.robot.commands;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj2.command.Subsystem;
import static frc.robot.Constants.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 * Going to add a comment when sensors are recieved to
 * allow for the driver to know if:
 * 1.) an arm is on the robot (left arm and right arm)
 * 2.) both arms are on the robot
 * 3.) the robot is ready to move
 * 
 * Will say on Driver Station "Ready to go."
 */
public class ClimbCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private static int num = 0;
    private int count;
    private int falconMaxSensor = 7;
    private int falconMinSensor = 0;
    boolean touchRight;
    boolean touchLeft;
    private boolean isExtended;
    public double sensor;
    boolean isFinished = false;
    private final ClimbSubsystem CLIMB_SUBSYSTEM;

    public ClimbCommand(ClimbSubsystem subsystem) {
      CLIMB_SUBSYSTEM = subsystem;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(subsystem);
    }

    @Override
    public void initialize() {
      touchRight = false;
      touchLeft = false;
      isExtended = false;
      System.out.println("intialzied");
      }
    

    public void execute() {
      System.out.println("Execute");
      num++;
      count = num;
      if(isExtended == false){
        CLIMB_SUBSYSTEM.moterMove(.25);
      }else if((isExtended == true)&&(touchRight == true)&&(touchLeft == true)){
        CLIMB_SUBSYSTEM.moterMove(-.25);
      }
      if(count >= 5){
        sensor = CLIMB_SUBSYSTEM.climbOne.getSelectedSensorPosition();
        if((sensor == falconMaxSensor)&&(isExtended == false)){
          System.out.println("We are extended");
          isExtended = true;
        }else if((sensor==falconMinSensor)&&(touchRight == true)&&(touchLeft == true)){
          isFinished = true;
        }

      }

       /*
       Old Code
        boolean isAPressed = Constants.XBOXCONTROLLER.getAButton();
        System.out.println("Varible a "+ isAPressed );
        boolean isBPressed = Constants.XBOXCONTROLLER.getBButton();
        System.out.println("Varible b "+ isBPressed );
        if(isAPressed){
            climbSubsystem.moterMove(.25);
        }else if(isBPressed){
            climbSubsystem.moterMove(-.25);
        }else{
            climbSubsystem.moterMove(0);
            
        }
        */
        //num++;
        //count = num;

      
  }
}
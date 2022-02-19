package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.BeltSubsystem;

public class BeltCommand extends CommandBase{
    private final BeltSubsystem BELT_SUBSYSTEM;

    public BeltCommand(BeltSubsystem subsystem) {
        System.out.println("construct");
        BELT_SUBSYSTEM = subsystem;
        addRequirements(subsystem);
    }

    public void balltoTopOfBelt(){
        if(BELT_SUBSYSTEM.lineBroken = false){
            while(BELT_SUBSYSTEM.lineBroken == false){
                BELT_SUBSYSTEM.beltSpark.set(.25);
            }
            BELT_SUBSYSTEM.beltSpark.set(0.0);
        }
    }

    public void ballToShooter(){

        if(BELT_SUBSYSTEM.lineBroken == true){
            while(BELT_SUBSYSTEM.lineBroken == true){
                 BELT_SUBSYSTEM.beltSpark.set(.25);
                 new WaitCommand(.5).schedule();

            }
            BELT_SUBSYSTEM.beltSpark.set(0.0);   
        }
    }




}

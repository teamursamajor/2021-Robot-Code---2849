package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BeltSubsystem extends SubsystemBase {
    DigitalInput lineSensor = new DigitalInput(LINE_BREAK);
    boolean lineBroken;
    Spark beltSpark = new Spark(BELT_PORT);

    public void periodic() {
        lineBroken = lineSensor.get();

      }
    
  
    public void simulationPeriodic() {
     
    
      
    }

    
    public void newBallIntake(){
        if(lineBroken = false){
            while(lineBroken == false){
                beltSpark.set(.25);
            }
            beltSpark.set(0.0);
        }
    }


    
}

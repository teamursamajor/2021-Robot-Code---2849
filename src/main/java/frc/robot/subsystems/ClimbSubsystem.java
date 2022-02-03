package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
public class ClimbSubsystem extends SubsystemBase{
   // private final ClimbSubsystem m_ClimbSubsystem;
    public Spark climber = new Spark(3);
    public Talon climbOne = new Talon(3);
    public Talon climbTwo = new Talon(0);
    public ClimbSubsystem() {

    }

    //@Override
    public void periodic() {

    }

    //@Override
    public void SimulationPeriodic() {
        
    }
}

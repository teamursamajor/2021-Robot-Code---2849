package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.HashMap;

public abstract class Subsystem extends SubsystemBase {

    private static HashMap<String, Subsystem> subsystems = new HashMap<String, Subsystem>();

    static {
        new Drive();
    }

    protected void register(String k, Subsystem v) {
        subsystems.put(k, v);
    }

    public static Subsystem getSubsystem(String k) {
        return subsystems.get(k);
    }

    @Override
    public abstract void periodic();

    @Override
    public abstract void simulationPeriodic();

}

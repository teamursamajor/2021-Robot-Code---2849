package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.HashMap;

/**
 * @author Emilyn Heikell
 * <p>Generic Subsystems class, all subsystems should extend this class.</p>
 */

public abstract class Subsystem extends SubsystemBase {


    /**
     * <p>This is static list of all instances of the subsystems</p>
     */
    private static HashMap<String, Subsystem> subsystems = new HashMap<String, Subsystem>();


    /**
     * <p>Initialize all subsystems ONCE in this bracket.</p>
     */
    static {
        
    }

    /**
     * <p>All children of this class should call this method with paramters of the subsystem name, and this, it adds it to {@link #subsystems}</p>
     * @param k key for subsystem
     * @param v subsystem value, should usually be "this"
     */
    protected void register(String k, Subsystem v) {
        subsystems.put(k, v);
    }

    /**
     * 
     * @param k key or subsystem name to search for
     * @return The subsystem at the given key, or null if key does not exist
     */
    public static Subsystem getSubsystem(String k) {
        return subsystems.get(k);
    }

    @Override
    public abstract void periodic();

    @Override
    public abstract void simulationPeriodic();

}

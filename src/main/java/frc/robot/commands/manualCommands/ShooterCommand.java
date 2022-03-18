package frc.robot.commands.manualCommands;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import static frc.robot.Constants.*;
//import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterCommand extends CommandBase {
  public boolean isShooterFinished = false;
  public double maxYValue = -10.0; //
  public double minYValue = 10.0;
  public double maxMotorSpeed = 400; // find out what the rpm is when the motor speed is at 1
  public double minMotorSpeed = 300;
  public double maxMotorPower = 1.0;
  public double shootingSpeed = -14000;
  private final ShooterSubsystem SHOOTER_SUBSYSTEM;

  /**
   * Creates a new ShooterCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterCommand(ShooterSubsystem subsystem) {
    // System.out.println("construct");
    SHOOTER_SUBSYSTEM = subsystem;
    addRequirements(subsystem);

  }

  public double getY() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry("pipeline").setNumber(SHOOTING_PIPELINE);
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tv = table.getEntry("tv");
    
    double y;
    boolean canDetectLimelight = tv.getDouble(0) > 0;
    System.out.println(canDetectLimelight + " " + tv.getDouble(0));
    if(canDetectLimelight == true){
      return ty.getDouble(Double.MIN_VALUE);
    }else{
      return 10.575;
    }
    
    // SmartDashboard.putNumber("LimelightX", y);
    
    
  }


  @Override
  public void initialize() {
    isShooterFinished = false;
    // System.out.println("initlazed");
    //shootingSpeed = SmartDashboard.getNumber("Shooting Speed", -14000);
    
    
    

    // SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, -1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("Execute");
    // try {
    //  wait(500L);
    // } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // finished = true;

    //SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.Velocity, -300);
    
    shootingSpeed = -10000 + 206.19 * (getY() - 22.7);
    shootingSpeed *= SmartDashboard.getNumber("Shooting Multiplier", 1.0);
    System.out.println("Shooting multiplier: " + SmartDashboard.getNumber("Shooting Multiplier", 1.0));
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.Velocity, shootingSpeed);
    System.out.println("Motor speed at " + SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity() + "Shooting speed at: " + shootingSpeed);
    
  }

  @Override
  public void end(boolean interrupted) {
    // System.out.println("end");
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, 0.0);
  }

  public boolean isFinished() {
    return false;
  }
}

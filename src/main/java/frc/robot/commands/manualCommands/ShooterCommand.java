package frc.robot.commands.manualCommands;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
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
  public double shootingSpeed = -10500;
  public boolean ready = false;
  private final ShooterSubsystem SHOOTER_SUBSYSTEM;
  private final IntakeSubsystem INTAKE_SUBSYSTEM;
  private boolean readyToShoot = false;

  /**
   * Creates a new ShooterCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterCommand(ShooterSubsystem subsystem, IntakeSubsystem intakeSubsystem) {
    // System.out.println("construct");
    SHOOTER_SUBSYSTEM = subsystem;
    INTAKE_SUBSYSTEM = intakeSubsystem;
    addRequirements(subsystem, intakeSubsystem);

  }

  public double getY() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry("pipeline").setNumber(SHOOTING_PIPELINE);
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tv = table.getEntry("tv");
    
    double y;
    boolean canDetectLimelight = tv.getDouble(0) > 0;
    //System.out.println(canDetectLimelight + " " + tv.getDouble(0));
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
    readyToShoot = false;
    ready = false;
    shootingSpeed = SmartDashboard.getNumber("Shooting Speed", -10500);
    shootingSpeed *= SmartDashboard.getNumber("Shooting Multiplier", 1.0);
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.Velocity, shootingSpeed);
    // System.out.println("initlazed");
    //shootingSpeed = SmartDashboard.getNumber("Shooting Speed", -14000);
    
    
    

    // SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, -1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(readyToShoot);
    // System.out.println("Execute");
    // try {
    //  wait(500L);
    // } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // finished = true;
    //System.out.println("Desired Shooting Speed: " + shootingSpeed);
    //System.out.println("Actual Shooting Speed: " + SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity());

    //SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.Velocity, -300);
    
    
    //shootingSpeed = -10000 + 206.19 * (getY() - 22.7);
    //System.out.println("Shooting multiplier: " + SmartDashboard.getNumber("Shooting Multiplier", 1.0));
    //SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.Velocity, shootingSpeed);
    //System.out.println("Motor speed at " + SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity() + "Shooting speed at: " + shootingSpeed);
    if(ready == false){
      if(Math.abs(shootingSpeed - SHOOTER_SUBSYSTEM.SHOOTER.getSelectedSensorVelocity()) < 60){
        readyToShoot = true;
        ready = true;
      }
      if(readyToShoot){
        readyToShoot = false;
        INTAKE_SUBSYSTEM.beltSpark.set(-1.0);
        INTAKE_SUBSYSTEM.INTAKE.set(0.5);
      }
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    SHOOTER_SUBSYSTEM.SHOOTER.set(TalonFXControlMode.PercentOutput, 0.0);
    INTAKE_SUBSYSTEM.beltSpark.set(0);
    INTAKE_SUBSYSTEM.INTAKE.set(0.0);
    ready = false;
    readyToShoot = false;
    //shootingSpeed = 0;
    System.out.println("Shooting end");
    //-9500 2ft 10 inches perivouslt set to -14000
  }

  public boolean isFinished() {
    return false;
  }
}

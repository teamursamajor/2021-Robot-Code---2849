package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShooterCommand extends CommandBase {
    public boolean isFinished = false;
    public double maxYValue = -10.0;
    public double minYValue = 10.0;
    public double maxMotorSpeed = 400;
    public double minMotorSpeed = 300;
    public double maxMotorPower = 1.0;
    public double minMotorPower = .25;
    private final ShooterSubsystem shooterSubsystem;
    
    public AutoShooterCommand(ShooterSubsystem subsystem) {
        System.out.println("construct");
        shooterSubsystem = subsystem;
        addRequirements(subsystem);
    }
    
      @Override
    public void initialize() {
    System.out.println("initialized");

    }
    
    
    public double getY() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry tv = table.getEntry("tv");
        double y;
        double canDetectLimelight = tv.getDouble(Double.MIN_VALUE);
        if (canDetectLimelight == 0) {
          y = Double.MIN_VALUE;
        } else {
          y = ty.getDouble(Double.MIN_VALUE);
        }
        SmartDashboard.putNumber("LimelightX", y);
        return y;
    }
    
    @Override
    public void execute() {
        int count = 0;
        double y = getY();
        if(y == Double.MIN_VALUE){
            System.out.println("Can't detect limelight");
            return;
        }else{
            //double linearScale = (maxMotorSpeed-minMotorSpeed)/(minYValue-maxYValue);
            //double power = minMotorSpeed + (y-minYValue)*linearScale;
            //call the shooter the power
            //double power = maxMotorSpeed *((y-minYValue)/(maxYValue-minYValue));
            double speed = maxMotorSpeed * (y/maxYValue);
            double power = maxMotorPower * (y/maxYValue);
            shooterSubsystem.SHOOTER.set(TalonFXControlMode.PercentOutput, power);
            if((shooterSubsystem.SHOOTER.getSelectedSensorVelocity()>=speed - 10)&&(shooterSubsystem.SHOOTER.
            getSelectedSensorVelocity()<=speed+10)){
                //set belt power for 5 seconds use belt command 
                //count++
               
            }
        }
        if(count == 2){
            isFinished = true;
        }

        
    }

    @Override
    public boolean isFinished() {
        return isFinished();

    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("end");
        shooterSubsystem.SHOOTER.set(TalonFXControlMode.PercentOutput, 0.0);


    }
    



}

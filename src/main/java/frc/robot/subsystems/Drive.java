package frc.robot.subsystems;

import static frc.robot.Constants.*;

import frc.robot.XboxController;

/**
 * @author Emilyn Heikell
 * <p>Driving makes the wheels go 'round</p>
 */
public class Drive extends Subsystem {

    public Drive() {
        super.register("drive", this);
        System.out.println("Drive is made");
    }

    @Override
    public void periodic() {
        // read controls
        sticksBox();
        
    }

    @Override
    public void simulationPeriodic() {
        // TODO Auto-generated method stub
    }

    private final void sticksBox() {
        // System.out.println(getHeading() + " " + getRawHeading());
		double leftSpeed, rightSpeed, leftStickY, rightStickX;
        
        leftStickY = CONTROLLER.getAxis(XboxController.AXIS_LEFTSTICK_Y);
        rightStickX = -CONTROLLER.getAxis(XboxController.AXIS_RIGHTSTICK_X);

        leftSpeed = leftStickY + rightStickX;
        rightSpeed = leftStickY - rightStickX;

        double max = Math.max(leftSpeed, rightSpeed); // the greater of the two values
        double min = Math.min(leftSpeed, rightSpeed); // the lesser of the two values

        if (max > 1) {
            leftSpeed /= max;
            rightSpeed /= max;
        } else if (min < -1) {
            leftSpeed /= -min;
            rightSpeed /= -min;
        }
    
    setLeftPower(leftSpeed);
    setRightPower(rightSpeed);
    }

    public void setLeftPower(final double power) {
        backLeftWheelSpark.set(-power);
        frontLeftWheelSpark.set(-power);
        System.out.println("left speed: " + power);
    }

    public void setRightPower(final double power) {
        backRightWheelSpark.set(-power);
        frontRightWheelSpark.set(-power);
        System.out.println("right speed: " + power);
    }
}

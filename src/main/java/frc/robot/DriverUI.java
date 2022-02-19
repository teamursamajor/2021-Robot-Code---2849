package frc.robot;

import java.net.ConnectException;
import java.util.function.Supplier;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.shuffleboard.*;

public final class DriverUI {

    private final ShuffleboardTab CONTROLS_TAB = Shuffleboard.getTab("Controls");
    private final ShuffleboardTab CONSOLE_TAB = Shuffleboard.getTab("Console");
    private final ShuffleboardTab HOME_TAB = Shuffleboard.getTab("Home");

    public DriverUI() {
        
    }

    private void tabInit() {
        homeTabInit();
        consoleTabInit();
        controlsTabInit();
    }

    private void homeTabInit() {
        HOME_TAB.addString("Team Color", () -> TEAM_COLOR);
    }

    private void consoleTabInit() {
        CONSOLE_TAB.addStringArray("Log", () -> getLogs());
    }

    private void controlsTabInit() {

    }
}

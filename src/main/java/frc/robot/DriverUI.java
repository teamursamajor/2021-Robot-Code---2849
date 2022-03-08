package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.shuffleboard.*;

public final class DriverUI {

  
  private final ShuffleboardTab HOME_TAB = Shuffleboard.getTab("Home");

  public DriverUI() {}

  private void tabInit() {
    homeTabInit();
    consoleTabInit();
    controlsTabInit();
  }

  private void homeTabInit() {
    HOME_TAB.addString("Team Color", () -> TEAM_COLOR);
  }

  private void consoleTabInit() {
    // CONSOLE_TAB.addStringArray("Log", valueSupplier);
  }

  private void controlsTabInit() {}
}

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public final class ColorRGB {

  public final int RED, GREEN, BLUE;

  public ColorRGB(Color c) {
    this.RED = (int) (c.red * 255);
    this.GREEN = (int) (c.green * 255);
    this.BLUE = (int) (c.blue * 255);
  }

  @Override
  public String toString() {
    return String.format("%d:%d:%d", RED, GREEN, BLUE);
  }
}

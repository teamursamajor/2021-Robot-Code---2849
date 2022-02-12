package frc.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements Runnable {
  private final SimpleDateFormat FILE_FMT = new SimpleDateFormat("yyyy-MM-dd");
  private final SimpleDateFormat LOG_FMT = new SimpleDateFormat("HH:mm:ss");
  private Thread t;
  private boolean running = false;
  private Object src = null;
  private String msg = null;

  public static enum Level {
    INFO,
    WARN,
    CRIT,
  }

  public synchronized void log(Object source, String message, Level lvl) {
    this.src = source;
    this.msg =
        String.format(
            "[%s] (%s) @ %s -> %s",
            LOG_FMT.format(new Date(System.currentTimeMillis())),
            lvl,
            source.getClass().getName(),
            message);
    System.out.println(msg);
  }

  @Override
  public void run() {
    while (running) {
      if (src != null && msg != null) {
        try {
          String fn =
              String.format(
                  "/home/lvuser/logs/%s_%s.log",
                  src.getClass().getName(), FILE_FMT.format(new Date(System.currentTimeMillis())));
          File file = new File(fn);
          file.createNewFile();
          BufferedWriter bf = new BufferedWriter(new FileWriter(file));
          bf.write(msg);
          bf.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    stop();
  }

  public synchronized void start() {
    if (running) return;
    running = true;
    t = new Thread(this);
    t.run();
  }

  public synchronized void stop() {
    if (!running) return;
    try {
      running = false;
      t.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

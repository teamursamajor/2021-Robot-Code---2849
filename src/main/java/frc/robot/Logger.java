package frc.robot;

import java.io.File;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Logger implements Runnable {
  private final SimpleDateFormat FILE_FMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
  private final SimpleDateFormat LOG_FMT = new SimpleDateFormat("HH:mm:ss");
  private boolean running = false;
  private Thread logger;
  private HashMap<String, File> logs = new HashMap<String, File>();

  public void log(Object source, String message) {
    String src = source.getClass().getName();
    String msg =
        String.format(
            "[%s]\t%s -> %s\n",
            LOG_FMT.format(new Date(System.currentTimeMillis())),
            source.getClass().getName(),
            message);
    System.out.print(msg);
    if (logs.get(src) == null) {
      logs.put(src, new ArrayList<String>());
    }
    ArrayList<String> list = logs.get(src);
    list.add(msg);
    logs.put(src, list);
  }

  public void run() {
    while (running) {
      
    }
    stop();
  }

  public synchronized void start() {
    if (running) return;
    running = true;
    logger = new Thread(this);
  }
  public synchronized void stop() {
    if (!running) return;
    try {
      running = false;
      logger.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

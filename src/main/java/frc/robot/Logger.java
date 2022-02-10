package frc.robot;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Logger {
  private final SimpleDateFormat FILE_FMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
  private final SimpleDateFormat LOG_FMT = new SimpleDateFormat("HH:mm:ss");
  private HashMap<String, ArrayList<String>> logs = new HashMap<String, ArrayList<String>>();

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

  public void exit() {
    logs.forEach(
        (k, v) -> {
          try {
            File f = new File("logs/" + k + FILE_FMT.format(new Date(System.currentTimeMillis())));
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            v.forEach(
                (x) -> {
                  try {
                    fw.write(x);
                  } catch (Exception e) {
                    e.printStackTrace();
                  }
                });
            fw.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
  }
}

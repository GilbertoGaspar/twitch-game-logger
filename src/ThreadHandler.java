import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by home on 2/28/2016.
 */
public class ThreadHandler {
    String[] names;
    List<Thread> threadList = new ArrayList<>();
    public ThreadHandler(String[] names) {
        this.names = names;
    }
    public void makeThreads() {
        for (String text: names) {
            threadList.add(new Thread(new CustomBot(text)));
        }
    }

    public void startThreads() {
        for (Thread thread: threadList) {
            thread.start();
        }
    }

    public void stopThreads() {
        for (Thread thread: threadList) {
            thread.interrupt();
        }
    }

    public void clearThreads() {
        threadList.clear();
    }

}

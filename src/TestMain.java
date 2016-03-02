import com.mb3364.twitch.api.models.Game;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by home on 2/27/2016.
 */
public class TestMain{
    public static void main(String[] args) {
        while(true) {
            GameScanner scanner = new GameScanner(100);
            scanner.refresh();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadHandler threadHandler = new ThreadHandler(scanner.getNames());
            threadHandler.makeThreads();
            threadHandler.startThreads();
            try {
                Thread.sleep((long)1.8e+6);
                System.out.println("Restarting...");
                threadHandler.stopThreads();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

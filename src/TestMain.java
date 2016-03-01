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
    /*private static String name;
    private static String twitchServer;
    private static String oauth;
    private static String channel = "#imaqtpie";

    public static void main(String[] args) throws IOException, IrcException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(".\\Config\\config.txt"));
        name = prop.getProperty("NAME");
        oauth = prop.getProperty("OAUTH");
        twitchServer = prop.getProperty("TWITCHSERVER");
        Configuration configuration = new Configuration.Builder()
                .setName(name) //Set the nick of the bot. CHANGE IN YOUR CODE
                .setServerPassword(oauth)
                .setServerHostname(twitchServer) //Join the freenode network
                .addAutoJoinChannel(channel) //Join the official #pircbotx channel
                .addListener(new IRCWriter(channel.replace("#","")))
                .buildConfiguration();

        //Create our bot with the configuration
        PircBotX bot = new PircBotX(configuration);
        //Connect to the server
        bot.startBot();*/
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

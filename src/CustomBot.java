import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by home on 2/28/2016.
 */
public class CustomBot implements Runnable {
    String channel;
    public CustomBot(String channel) { this.channel = "#" + channel; }

    @Override
    public void run() {
        String name;
        String twitchServer;
        String oauth;
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(".\\Config\\config.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = prop.getProperty("NAME");
        oauth = prop.getProperty("OAUTH");
        twitchServer = prop.getProperty("TWITCHSERVER");
        Configuration configuration = new Configuration.Builder()
                .setName(name) //Set the nick of the bot. CHANGE IN YOUR CODE
                .setServerPassword(oauth)
                .setServerHostname(twitchServer) //Join the freenode network
                .addAutoJoinChannel(channel) //Join the official #pircbotx channel
                .addListener(new IRCWriter(channel))
                .buildConfiguration();

        //Create our bot with the configuration
        PircBotX bot = new PircBotX(configuration);
        //Connect to the server
        try {
                bot.startBot();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        }
    }
}

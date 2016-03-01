import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.io.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by home on 2/27/2016.
 */
public class IRCWriter extends ListenerAdapter{
    private String channel;
    private String game;

    public IRCWriter(String channel) {
        this.channel = channel;
    }

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(".\\Config\\config.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        game = prop.getProperty("GAME");
        new File(".\\Logs\\" + game).mkdir();
        try {
            File outFile = new File(".\\Logs\\" + game + "\\" + channel + ".txt");
            //File outFile = new File("test.txt");
            if(!outFile.exists()) {
                outFile.createNewFile();
            }
            FileWriter fWriter = new FileWriter(outFile, true);
            PrintWriter pWriter = new PrintWriter(fWriter);
            pWriter.println("#" + new Date(event.getTimestamp()) + "# " + event.getUser().getNick() + ": " + event.getMessage());
            pWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

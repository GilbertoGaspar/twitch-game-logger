import com.mb3364.http.RequestParams;
import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.handlers.StreamsResponseHandler;
import com.mb3364.twitch.api.models.Stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by home on 2/25/2016.
 */
public class GameScanner {
    private String[] names;
    private String game;
    private int size;
    private Twitch twitch = new Twitch();

    public GameScanner() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(".\\Config\\config.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        game = prop.getProperty("GAME");
        size = 20; // Default
    }

    public GameScanner(int size) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(".\\Config\\config.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        game = prop.getProperty("GAME");
        this.size = size;
    }

    private void addAString(int index, String string, String[] stringArray) {
        stringArray[index] = string;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void refresh() {
        names = new String[size];
        RequestParams params = new RequestParams();
        params.put("game", game);
        params.put("limit", size);
        twitch.streams().get(params, new StreamsResponseHandler() {
            @Override
            public void onSuccess(int i, List<Stream> list) {

                i = 0;
                for (Stream stream : list) {
                    try {
                            System.out.println("Adding stream: " + stream.getChannel().getName());
                            addAString(i, stream.getChannel().getName(), names);
                            i++;
                    }
                    catch(ArrayIndexOutOfBoundsException aiofbe) {
                        System.out.println("Array size is too small!");
                    }
                }
            }

            @Override
            public void onFailure(int i, String s, String s1) {
                System.out.println("Scanner Error!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }
    public void printNames() {
        for (String text: names) {
            if(text != null) {
                System.out.println(text);
            }
        }
    }

    public String[] getNames() {
        return names;
    }
}

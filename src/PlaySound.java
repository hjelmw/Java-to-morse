import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by william on 12/2/16.
 */
public class PlaySound {

    final static int shortStop = 180;
    final static int longStop = 300;

    public static Clip shortclip;
    public static Clip longclip;

    public static void playShort() {
        try {
            AudioInputStream ShortInputStream = AudioSystem.getAudioInputStream(new File("short.wav").getAbsoluteFile());
            shortclip = AudioSystem.getClip();
            shortclip.open(ShortInputStream);
            shortclip.start();
            Thread.sleep(shortStop);
            shortclip.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void playLong() {
        try {
            AudioInputStream LongInputStream = AudioSystem.getAudioInputStream(new File("long.wav").getAbsoluteFile());
            longclip = AudioSystem.getClip();
            longclip.open(LongInputStream);
            longclip.start();
            Thread.sleep(longStop);
            longclip.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
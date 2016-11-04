import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

/**
 * Created by william on 2016-11-01.
 */

public class Main {
    static int shortStop = 180;
    static int longStop = 300;

    static Clip shortclip;
    static Clip longclip;

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

    public static String parse_morse(char input) {
        String result = "";
        String line;
        input = Character.toUpperCase(input);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("morse.txt"));
            while((line = reader.readLine()) != null) {
                if (line.charAt(0) == input) {
                    for (int i=1; i < line.length(); i++) {
                        result += line.charAt(i);
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String multiparse_morse(String input) {
        String result = "";
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == ' ') {
                result+="/";
            }
            else {
                result+=parse_morse(input.charAt(i));
            }
        }
        return result;
    }

    public static void parse_audio(String input) throws InterruptedException {
        for (int i=0; i<input.length(); i++) {
            switch (input.charAt(i)) {
                case '.':
                    playShort();
                    break;
                case '-':
                    playLong();
                    break;
                case '/':
                    Thread.sleep(500);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        String output = "BATTLEFIELD 1 BATTLEFIELD 1 BATTLEFIELD 1 BATTLEFIELD 1 BATTLEFIELD 1 BATTLEFIELD 1 ";
        System.out.println(multiparse_morse(output));
        parse_audio(multiparse_morse(output));
    }
}

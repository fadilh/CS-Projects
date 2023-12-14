import java.util.ArrayList;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardResizeToggleHandler;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        double CONCERT_A = 440.0;
        ArrayList<GuitarString> guitarStrings = new ArrayList<GuitarString>();
        for (int i = 0; i < keyboard.length(); i++) {
            guitarStrings.add(new GuitarString(CONCERT_A * Math.pow(1.05956, i - 24)));
        }


        // the main input loop
        while (true) {
            double sample = 0.0;

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();
                // pluck the corresponding string
                if (keyboard.indexOf(key) != -1) {
                    int curr = keyboard.indexOf(key);
                    guitarStrings.get(curr).pluck();
                }

            }
            for (GuitarString guitarString : guitarStrings) {
                sample += guitarString.sample();
            }

            StdAudio.play(sample);
            for (GuitarString guitarString : guitarStrings) {
                guitarString.tic();
            }
        }
    }
}

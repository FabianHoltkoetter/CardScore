package cardscore.fabianholtkoetter.de.cardscore.ColorPicker;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import cardscore.fabianholtkoetter.de.cardscore.R;

/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class Utils {


    /**
     * Utility class for colors
     *
     * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
     */
    public static class ColorUtils {

        /**
         * Create an array of int with colors.
         *
         * @param context Context of the Activity.
         * @return The Color Choice of the user.
         */
        public static int[] colorChoice(Context context) {

            int[] mColorChoices = null;
            String[] color_array = context.getResources().getStringArray(R.array.color_chooser);

            if (color_array != null && color_array.length > 0) {
                mColorChoices = new int[color_array.length];
                for (int i = 0; i < color_array.length; i++) {
                    mColorChoices[i] = Color.parseColor(color_array[i]);
                }
            }
            return mColorChoices;
        }

        /**
         * Parse whiteColor
         *
         * @return Int representing parsed color.
         */
        public static int parseWhiteColor() {
            return Color.parseColor("#FFFFFF");
        }

    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
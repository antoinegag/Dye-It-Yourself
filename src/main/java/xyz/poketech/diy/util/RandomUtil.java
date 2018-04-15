package xyz.poketech.diy.util;

import org.apache.commons.lang3.RandomUtils;
import xyz.poketech.diy.ConfigHandler;
import xyz.poketech.diy.DyeItYourself;

public class RandomUtil {

    /**
     * @return a time in tick until the next dye drop based on the config
     */
    public static int getNextDye() {
        int min =ConfigHandler.dyeDrop.rngLowerBoundTime;
        int max = ConfigHandler.dyeDrop.rngUpperBoundTime;

        if(min > max || max - min < 0) {
            DyeItYourself.LOGGER.error("Tried to get a random next dye time but min > max, min = " + min + " max = " + max);
            return 0;
        }
        return RandomUtils.nextInt(min, max);
    }

    public static int getDyeDropAmountSafe() {
        int min =ConfigHandler.dyeDrop.minDyeDrop;
        int max = ConfigHandler.dyeDrop.maxDyeDrop;

        if(min > max || max - min < 0) {
            DyeItYourself.LOGGER.error("Tried to get a random amount of dye but min > max, min = " + min + " max = " + max);
            return 0;
        }
        return RandomUtils.nextInt(min, max + 1);
    }
}

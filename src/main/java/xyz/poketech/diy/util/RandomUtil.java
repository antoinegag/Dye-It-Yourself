package xyz.poketech.diy.util;

import org.apache.commons.lang3.RandomUtils;
import xyz.poketech.diy.ConfigHandler;
import xyz.poketech.diy.DyeItYourself;

public class RandomUtil {

    /**
     * @return a time in tick until the next dye pooped based on the config
     */
    public static int getNextDye() {
        int min =ConfigHandler.dyePooping.rngLowerBoundTime;
        int max = ConfigHandler.dyePooping.rngUpperBoundTime;

        if(min > max || max - min < 0) {
            DyeItYourself.LOGGER.error("Tried to get a random next dye time but min > max, min = " + min + " max = " + max);
            return 0;
        }
        return RandomUtils.nextInt(min, max);
    }

    /**
     * @return a random dye ammount based on the config
     */
    public static int getDyePoopedAmount() {
        return RandomUtils.nextInt(ConfigHandler.dyePooping.minDyePoop, ConfigHandler.dyePooping.maxDyePoop + 1);
    }

    public static int getDyePoopedAmountSafe() {
        int min =ConfigHandler.dyePooping.minDyePoop;
        int max = ConfigHandler.dyePooping.maxDyePoop;

        if(min > max || max - min < 0) {
            DyeItYourself.LOGGER.error("Tried to get a random amount of dye but min > max, min = " + min + " max = " + max);
            return 0;
        }
        return RandomUtils.nextInt(min, max + 1);
    }
}

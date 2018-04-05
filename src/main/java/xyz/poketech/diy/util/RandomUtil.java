package xyz.poketech.diy.util;

import org.apache.commons.lang3.RandomUtils;
import xyz.poketech.diy.ConfigHandler;

public class RandomUtil {

    /**
     * @return a time in tick until the next dye pooped based on the config
     */
    public static int getNextDye() {
        return RandomUtils.nextInt(ConfigHandler.dyePooping.rngLowerBoundTime, ConfigHandler.dyePooping.rngUpperBoundTime);
    }

    /**
     * @return a random dye ammount based on the config
     */
    public static int getDyePoopedAmount() {
        return RandomUtils.nextInt(ConfigHandler.dyePooping.minDyePoop, ConfigHandler.dyePooping.maxDyePoop + 1);
    }
}

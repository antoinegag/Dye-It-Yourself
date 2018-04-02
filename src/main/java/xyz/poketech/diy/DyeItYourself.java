package xyz.poketech.diy;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
@Mod(modid = DyeItYourself.MODID, name = DyeItYourself.NAME, version = DyeItYourself.VERSION, acceptedMinecraftVersions = DyeItYourself.VERSION_RANGE)
public class DyeItYourself {
    public static final String MODID = "diy";
    public static final String NAME = "Dye it yourself";
    public static final String VERSION = "1.0";
    public static final String VERSION_RANGE = "[1.12,1.13)";
    public static final boolean DEV_MODE = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    @Mod.Instance
    public static DyeItYourself instance;

    public static final Logger LOGGER = LogManager.getLogger(MODID);
}
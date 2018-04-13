package xyz.poketech.diy;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.poketech.diy.proxy.CommonProxy;

@Mod.EventBusSubscriber
@Mod(modid = DyeItYourself.MODID, name = DyeItYourself.NAME, version = DyeItYourself.VERSION, acceptedMinecraftVersions = DyeItYourself.VERSION_RANGE)
public class DyeItYourself {
    public static final String MODID = "diy";

    public static final String NAME = "Dye it yourself";
    public static final String VERSION = "1.1";
    public static final String VERSION_RANGE = "[1.12,1.13)";
    public static final boolean DEV_MODE = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    public static final SimpleNetworkWrapper NETWORK = new SimpleNetworkWrapper(MODID);

    @Mod.Instance
    public static DyeItYourself instance;

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "xyz.poketech.diy.proxy.ClientProxy", serverSide = "xyz.poketech.diy.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
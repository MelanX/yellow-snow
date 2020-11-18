package de.melanx.yellowsnow;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(YellowSnow.MODID)
public class YellowSnow {

    public static final String MODID = "yellowsnow";
    private static final Logger LOGGER = LogManager.getLogger(MODID);
    public YellowSnow instance;

    public YellowSnow() {
        instance = this;

        MinecraftForge.EVENT_BUS.register(this);
    }
}

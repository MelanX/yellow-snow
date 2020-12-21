package de.melanx.yellowsnow;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ServerConfig {
    public static final ForgeConfigSpec SERVER_CONFIG;
    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

    static {
        init(SERVER_BUILDER);
        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    public static ForgeConfigSpec.BooleanValue playerCanPee;
    public static ForgeConfigSpec.BooleanValue spreadable;
    public static ForgeConfigSpec.IntValue peeDuration;

    public static void init(ForgeConfigSpec.Builder builder) {
        playerCanPee = builder.comment("If this is turned on, players can turn snow into yellow snow")
                .define("player_can_pee", false);
        spreadable = builder.comment("If this is turned on, yellow snow is able to spread to other snow")
                .define("spreadable", true);
        peeDuration = builder.comment("Time in seconds a living entity needs to stand on snow to turn into yellow snow")
                .defineInRange("pee_duration", 5, 1, Integer.MAX_VALUE / 20);
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        YellowSnow.getInstance().logger.debug("Loading config file {}", path);
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }
}

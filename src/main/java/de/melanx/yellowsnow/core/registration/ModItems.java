package de.melanx.yellowsnow.core.registration;

import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.items.YellowSnowballItem;
import net.minecraft.item.Item;

public class ModItems {
    public static final Item YELLOW_SNOWBALL = new YellowSnowballItem(YellowSnow.getInstance(), new Item.Properties().maxStackSize(16));

    public static void register() {
        YellowSnow.getInstance().register("yellow_snowball", YELLOW_SNOWBALL);
    }
}

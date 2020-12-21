package de.melanx.yellowsnow.core.registration;

import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.blocks.YellowSnowBlock;
import de.melanx.yellowsnow.blocks.YellowSnowLayerBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.ToolType;

public class ModBlocks {
    public static final Block YELLOW_SNOW = new YellowSnowLayerBlock(AbstractBlock.Properties.from(Blocks.SNOW).harvestTool(ToolType.SHOVEL).tickRandomly());
    public static final Block YELLOW_SNOW_BLOCK = new YellowSnowBlock(YellowSnow.getInstance(), AbstractBlock.Properties.from(Blocks.SNOW_BLOCK).harvestTool(ToolType.SHOVEL).tickRandomly());

    public static void register() {
        YellowSnow.getInstance().register("yellow_snow", YELLOW_SNOW);
        YellowSnow.getInstance().register("yellow_snow_block", YELLOW_SNOW_BLOCK);
    }
}

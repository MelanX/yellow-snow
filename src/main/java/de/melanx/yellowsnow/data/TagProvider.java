package de.melanx.yellowsnow.data;

import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import io.github.noeppi_noeppi.libx.data.provider.BlockTagProviderBase;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagProvider extends BlockTagProviderBase {
    public TagProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(YellowSnow.getInstance(), generator, helper);
    }

    public static final ITag.INamedTag<Block> SLEDGE_SURFACE = BlockTags.createOptional(new ResourceLocation("next_christmas", "sledge_surface"));

    @Override
    protected void registerTags() {
        this.getOrCreateBuilder(SLEDGE_SURFACE).add(ModBlocks.YELLOW_SNOW).add(ModBlocks.YELLOW_SNOW_BLOCK);
    }
}

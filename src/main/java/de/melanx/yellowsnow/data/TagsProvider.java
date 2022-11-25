package de.melanx.yellowsnow.data;

import de.melanx.yellowsnow.core.registration.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.CommonTagsProviderBase;
import org.moddingx.libx.mod.ModX;

@Datagen
public class TagsProvider extends CommonTagsProviderBase {

    public TagsProvider(ModX mod, DataGenerator generator, ExistingFileHelper helper) {
        super(mod, generator, helper);
    }

    public static final TagKey<Block> SLEDGE_SURFACE = BlockTags.create(new ResourceLocation("next_christmas", "sledge_surface"));

    @Override
    public void setup() {
        this.block(SLEDGE_SURFACE)
                .add(ModBlocks.yellowSnow)
                .add(ModBlocks.yellowSnowBlock);
        this.block(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.yellowSnow)
                .add(ModBlocks.yellowSnowBlock);
    }
}

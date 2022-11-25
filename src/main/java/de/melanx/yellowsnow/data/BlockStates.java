package de.melanx.yellowsnow.data;

import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.BlockStateProviderBase;
import org.moddingx.libx.mod.ModX;

import java.util.function.Supplier;

@Datagen
public class BlockStates extends BlockStateProviderBase {

    public BlockStates(ModX mod, DataGenerator generator, ExistingFileHelper fileHelper) {
        super(mod, generator, fileHelper);
    }

    @Override
    protected void setup() {
        //noinspection ConstantConditions
        this.manualModel(ModBlocks.yellowSnowBlock, this.models().cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.yellowSnowBlock).getPath(), YellowSnow.getInstance().resource("block/yellow_snow")));
    }

    @Override
    protected void defaultState(ResourceLocation id, Block block, Supplier<ModelFile> model) {
        if (block == ModBlocks.yellowSnow) {
            VariantBlockStateBuilder builder = this.getVariantBuilder(block);
            for (int height = 1; height <= 8; height++) {
                if (height == 8) {
                    builder.partialState().with(BlockStateProperties.LAYERS, height).addModels(
                            new ConfiguredModel(this.models().cubeAll(id.getPath() + height, new ResourceLocation(YellowSnow.getInstance().modid, "block/yellow_snow")))
                    );
                } else {
                    builder.partialState().with(BlockStateProperties.LAYERS, height).addModels(
                            new ConfiguredModel(this.models()
                                    .withExistingParent(id.getPath() + (height == 1 ? "" : Integer.toString(height)), new ResourceLocation("minecraft", "snow_height" + 2 * height))
                                    .texture("texture", new ResourceLocation(YellowSnow.getInstance().modid, "block/yellow_snow"))
                                    .texture("particle", new ResourceLocation(YellowSnow.getInstance().modid, "block/yellow_snow"))
                            )
                    );
                }
            }
        } else {
            super.defaultState(id, block, model);
        }
    }

    @Override
    protected ModelFile defaultModel(ResourceLocation id, Block block) {
        if (block == ModBlocks.yellowSnow) {
            return null;
        } else {
            return super.defaultModel(id, block);
        }
    }
}

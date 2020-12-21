package de.melanx.yellowsnow.data;

import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import io.github.noeppi_noeppi.libx.data.provider.BlockStateProviderBase;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProviderBase {
    public BlockStates(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(YellowSnow.getInstance(), generator, fileHelper);
    }

    @Override
    protected void setup() {
        this.manualModel(ModBlocks.YELLOW_SNOW_BLOCK, this.models().cubeAll(ModBlocks.YELLOW_SNOW_BLOCK.getRegistryName().getPath(), new ResourceLocation(YellowSnow.getInstance().modid, "block/yellow_snow")));
    }

    @Override
    protected void defaultState(ResourceLocation id, Block block, ModelFile model) {
        if (block == ModBlocks.YELLOW_SNOW) {
            VariantBlockStateBuilder builder = getVariantBuilder(block);
            for (int height = 1; height <= 8; height++) {
                if (height == 8) {
                    builder.partialState().with(BlockStateProperties.LAYERS_1_8, height).addModels(
                            new ConfiguredModel(models().cubeAll(id.getPath() + height, new ResourceLocation(YellowSnow.getInstance().modid, "block/yellow_snow")))
                    );
                } else {
                    builder.partialState().with(BlockStateProperties.LAYERS_1_8, height).addModels(
                            new ConfiguredModel(models()
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
        if (block == ModBlocks.YELLOW_SNOW) {
            return null;
        } else {
            return super.defaultModel(id, block);
        }
    }
}

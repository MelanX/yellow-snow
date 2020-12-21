package de.melanx.yellowsnow.blocks;

import com.google.common.collect.ImmutableSet;
import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import io.github.noeppi_noeppi.libx.mod.registration.Registerable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.Set;

public class YellowSnowLayerBlock extends SnowBlock implements Registerable {
    private final Item item;

    public YellowSnowLayerBlock(Properties properties) {
        super(properties);
        this.item = new BlockItem(this, new Item.Properties().group(YellowSnow.getInstance().tab));
    }

    @Override
    public Set<Object> getAdditionalRegisters() {
        return ImmutableSet.of(this.item);
    }

    @Override
    public void randomTick(@Nonnull BlockState state, @Nonnull ServerWorld world, @Nonnull BlockPos pos, @Nonnull Random random) {
        if (world.getWorldInfo().isRaining() && world.getBiome(pos).getPrecipitation() == Biome.RainType.SNOW) {
            world.setBlockState(pos, Blocks.SNOW.getDefaultState().with(BlockStateProperties.LAYERS_1_8, state.get(BlockStateProperties.LAYERS_1_8)));
        }
        YellowSnowBlock.spreadYellowSnow(world, pos, random);
    }
}

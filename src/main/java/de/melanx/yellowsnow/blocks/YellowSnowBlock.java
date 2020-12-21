package de.melanx.yellowsnow.blocks;

import de.melanx.yellowsnow.ServerConfig;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import io.github.noeppi_noeppi.libx.mod.ModX;
import io.github.noeppi_noeppi.libx.mod.registration.BlockBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.Random;

public class YellowSnowBlock extends BlockBase {
    public YellowSnowBlock(ModX mod, Properties properties) {
        super(mod, properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(@Nonnull BlockState state, @Nonnull ServerWorld world, @Nonnull BlockPos pos, @Nonnull Random random) {
        if (world.getWorldInfo().isRaining() && world.getBiome(pos).getPrecipitation() == Biome.RainType.SNOW) {
            world.setBlockState(pos, Blocks.SNOW_BLOCK.getDefaultState());
        }
        spreadYellowSnow(world, pos, random);
    }

    public static void spreadYellowSnow(ServerWorld world, BlockPos pos, Random random) {
        if (!ServerConfig.spreadable.get()) return;
        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.isIn(Blocks.SNOW_BLOCK)) {
            world.setBlockState(blockpos, ModBlocks.YELLOW_SNOW_BLOCK.getDefaultState());
        } else if (blockstate.isIn(Blocks.SNOW)) {
            int layers = blockstate.get(BlockStateProperties.LAYERS_1_8);
            world.setBlockState(blockpos, ModBlocks.YELLOW_SNOW.getDefaultState().with(BlockStateProperties.LAYERS_1_8, layers));
        }
    }
}

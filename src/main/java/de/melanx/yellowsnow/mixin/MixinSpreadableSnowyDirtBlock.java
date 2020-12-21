package de.melanx.yellowsnow.mixin;

import de.melanx.yellowsnow.core.registration.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SpreadableSnowyDirtBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

// [VanillaCopy]
@Mixin(SpreadableSnowyDirtBlock.class)
public abstract class MixinSpreadableSnowyDirtBlock {

    private static boolean isSnowyConditions(IWorldReader worldReader, BlockPos pos) {
        BlockState blockState = worldReader.getBlockState(pos.up());
        return blockState.isIn(ModBlocks.YELLOW_SNOW) && blockState.get(SnowBlock.LAYERS) == 1;
    }

    private static boolean isSnowyAndNotUnderwater(IWorldReader worldReader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return isSnowyConditions(worldReader, pos) && !worldReader.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    @Inject(at = @At("HEAD"), method = "randomTick", cancellable = true)
    private void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random, CallbackInfo info) {
        if (isSnowyConditions(worldIn, pos)) {
            if (worldIn.getLight(pos.up()) >= 9) {
                BlockState blockstate = ((SpreadableSnowyDirtBlock) (Object) this).getDefaultState();

                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (worldIn.getBlockState(blockpos).isIn(Blocks.DIRT) && isSnowyAndNotUnderwater(worldIn, blockpos)) {
                        worldIn.setBlockState(blockpos, blockstate.with(BlockStateProperties.SNOWY, true));
                    }
                }
                info.cancel();
            }
        }
    }
}

package de.melanx.yellowsnow.mixin;

import de.melanx.yellowsnow.core.registration.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowyDirtBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowyDirtBlock.class)
public class MixinSnowyDirtBlock {

    @Shadow
    @Final
    public static BooleanProperty SNOWY;

    @Inject(at = @At("HEAD"), method = "updatePostPlacement", cancellable = true)
    private void updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos, CallbackInfoReturnable<BlockState> info) {
        if (facing == Direction.UP) {
            if (facingState.isIn(ModBlocks.YELLOW_SNOW) || facingState.isIn(ModBlocks.YELLOW_SNOW_BLOCK)) {
                info.setReturnValue(stateIn.with(SNOWY, true));
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "getStateForPlacement", cancellable = true)
    private void getStateForPlacement(BlockItemUseContext context, CallbackInfoReturnable<BlockState> info) {
        if (!context.getWorld().isRemote) {
            BlockState blockstate = context.getWorld().getBlockState(context.getPos().up());
            if (blockstate.isIn(ModBlocks.YELLOW_SNOW) || blockstate.isIn(ModBlocks.YELLOW_SNOW_BLOCK)) {
                info.setReturnValue(((SnowyDirtBlock) (Object) this).getDefaultState().with(SNOWY, true));
            }
        }
    }
}

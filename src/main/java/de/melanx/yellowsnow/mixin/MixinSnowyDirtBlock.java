package de.melanx.yellowsnow.mixin;

import de.melanx.yellowsnow.core.registration.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowyDirtBlock.class)
public abstract class MixinSnowyDirtBlock {

    @Shadow
    @Final
    public static BooleanProperty SNOWY;

    @Inject(at = @At("HEAD"), method = "updateShape", cancellable = true)
    private void updatePostPlacement(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos, CallbackInfoReturnable<BlockState> cir) {
        if (facing == Direction.UP) {
            if (facingState.is(ModBlocks.yellowSnow) || facingState.is(ModBlocks.yellowSnowBlock)) {
                cir.setReturnValue(state.setValue(SNOWY, true));
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "getStateForPlacement", cancellable = true)
    private void getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
        if (!context.getLevel().isClientSide) {
            BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().above());
            if (blockstate.is(ModBlocks.yellowSnow) || blockstate.is(ModBlocks.yellowSnowBlock)) {
                cir.setReturnValue(((SnowyDirtBlock) (Object) this).defaultBlockState().setValue(SNOWY, true));
            }
        }
    }
}

package de.melanx.yellowsnow.blocks;

import com.google.common.collect.ImmutableSet;
import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import de.melanx.yellowsnow.items.YellowSnowballItem;
import io.github.noeppi_noeppi.libx.mod.registration.Registerable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
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

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable IBlockReader world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(YellowSnowballItem.DONT_EAT.mergeStyle(TextFormatting.DARK_RED));
    }
}

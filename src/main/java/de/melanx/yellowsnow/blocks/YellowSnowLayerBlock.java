package de.melanx.yellowsnow.blocks;

import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.items.YellowSnowballItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.ForgeRegistries;
import org.moddingx.libx.registration.Registerable;
import org.moddingx.libx.registration.RegistrationContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class YellowSnowLayerBlock extends SnowLayerBlock implements Registerable {
    private final Item item;

    public YellowSnowLayerBlock(Properties properties) {
        super(properties);
        //noinspection ConstantConditions
        this.item = new BlockItem(this, new Item.Properties().tab(YellowSnow.getInstance().tab));
    }

    @Override
    public void randomTick(@Nonnull BlockState state, @Nonnull ServerLevel level, @Nonnull BlockPos pos, @Nonnull RandomSource random) {
        if (level.getLevelData().isRaining() && level.getBiome(pos).value().getPrecipitation() == Biome.Precipitation.SNOW) {
            level.setBlock(pos, Blocks.SNOW.defaultBlockState().setValue(BlockStateProperties.LAYERS, state.getValue(BlockStateProperties.LAYERS)), Block.UPDATE_ALL);
        }
        YellowSnowBlock.spreadYellowSnow(level, pos, random);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable BlockGetter level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(YellowSnowballItem.DONT_EAT.withStyle(ChatFormatting.DARK_RED));
    }

    @Override
    public void registerAdditional(RegistrationContext ctx, EntryCollector builder) {
        builder.register(Registry.ITEM_REGISTRY, this.item);
    }

    @Override
    public void initTracking(RegistrationContext ctx, TrackingCollector builder) throws ReflectiveOperationException {
        builder.track(ForgeRegistries.ITEMS, YellowSnowLayerBlock.class.getDeclaredField("item"));
    }
}

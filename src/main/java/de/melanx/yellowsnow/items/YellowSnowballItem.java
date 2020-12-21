package de.melanx.yellowsnow.items;

import io.github.noeppi_noeppi.libx.mod.ModX;
import io.github.noeppi_noeppi.libx.mod.registration.ItemBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class YellowSnowballItem extends ItemBase {
    public YellowSnowballItem(ModX mod, Properties properties) {
        super(mod, properties);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, @Nonnull PlayerEntity player, @Nonnull Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote) {
            SnowballEntity snowballentity = new SnowballEntity(world, player);
            snowballentity.setItem(stack);
            snowballentity.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.addEntity(snowballentity);
        }

        player.addStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.isCreativeMode) {
            stack.shrink(1);
        }

        return ActionResult.func_233538_a_(stack, world.isRemote());
    }
}

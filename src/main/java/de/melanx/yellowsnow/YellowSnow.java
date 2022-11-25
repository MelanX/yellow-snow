package de.melanx.yellowsnow;

import de.melanx.yellowsnow.core.EventHandler;
import de.melanx.yellowsnow.core.registration.ModItems;
import net.minecraft.Util;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.moddingx.libx.mod.ModXRegistration;
import org.moddingx.libx.registration.RegistrationBuilder;

import javax.annotation.Nonnull;

@Mod("yellowsnow")
public final class YellowSnow extends ModXRegistration {

    private static YellowSnow instance;

    public YellowSnow() {
        super(new CreativeModeTab("yellowsnow") {

            @Nonnull
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.yellowSnowball);
            }
        });
        instance = this;

        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    @Override
    protected void initRegistration(RegistrationBuilder builder) {

    }

    @Nonnull
    public static YellowSnow getInstance() {
        return instance;
    }

    @Override
    protected void setup(FMLCommonSetupEvent event) {
        DispenserBlock.registerBehavior(ModItems.yellowSnowball, new AbstractProjectileDispenseBehavior() {
            @Nonnull
            protected Projectile getProjectile(@Nonnull Level level, @Nonnull Position pos, @Nonnull ItemStack stack) {
                return Util.make(new Snowball(level, pos.x(), pos.y(), pos.z()), snowball -> snowball.setItem(stack));
            }
        });
    }

    @Override
    protected void clientSetup(FMLClientSetupEvent fmlClientSetupEvent) {

    }
}

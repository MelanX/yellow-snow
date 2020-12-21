package de.melanx.yellowsnow;

import de.melanx.yellowsnow.core.registration.ModBlocks;
import de.melanx.yellowsnow.core.registration.ModItems;
import de.melanx.yellowsnow.data.DataCreator;
import io.github.noeppi_noeppi.libx.mod.registration.ModXRegistration;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import javax.annotation.Nonnull;

@Mod("yellowsnow")
public class YellowSnow extends ModXRegistration {

    private static YellowSnow instance;

    public YellowSnow() {
        super("yellowsnow", new ItemGroup("yellowsnow") {
            @Nonnull
            @Override
            public ItemStack createIcon() {
                return new ItemStack(ModItems.YELLOW_SNOWBALL);
            }
        });
        instance = this;

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SERVER_CONFIG);
        ServerConfig.loadConfig(ServerConfig.SERVER_CONFIG, FMLPaths.GAMEDIR.get().resolve(FMLConfig.defaultConfigPath()).resolve(this.modid + "-server.toml"));

        this.addRegistrationHandler(ModBlocks::register);
        this.addRegistrationHandler(ModItems::register);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(DataCreator::onGatherData);
    }

    @Nonnull
    public static YellowSnow getInstance() {
        return instance;
    }

    @Override
    protected void setup(FMLCommonSetupEvent fmlCommonSetupEvent) {
        DispenserBlock.registerDispenseBehavior(ModItems.YELLOW_SNOWBALL, new ProjectileDispenseBehavior() {
            @Nonnull
            protected ProjectileEntity getProjectileEntity(@Nonnull World world, @Nonnull IPosition pos, ItemStack stack) {
                return Util.make(new SnowballEntity(world, pos.getX(), pos.getY(), pos.getZ()), (snowball) -> {
                    snowball.setItem(stack);
                });
            }
        });
    }

    @Override
    protected void clientSetup(FMLClientSetupEvent fmlClientSetupEvent) {

    }
}

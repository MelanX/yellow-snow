package de.melanx.yellowsnow.core;

import de.melanx.yellowsnow.ServerConfig;
import de.melanx.yellowsnow.compat.NaughtyOrNice;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import de.melanx.yellowsnow.core.registration.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "yellowsnow", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Events {

    private static final HashMap<UUID, Pair<BlockPos, Integer>> entityMap = new HashMap<>();

    @SubscribeEvent
    public static void onHitEntity(LivingAttackEvent event) {
        DamageSource source = event.getSource();
        if (source.getImmediateSource() instanceof SnowballEntity) {
            SnowballEntity snowball = (SnowballEntity) source.getImmediateSource();
            if (snowball.getItem().getItem() == ModItems.YELLOW_SNOWBALL) {
                event.getEntityLiving().attackEntityFrom(ModDamageSources.PEE, event.getEntity().getEntityWorld().rand.nextInt(2) + (event.getEntityLiving() instanceof BlazeEntity ? 4 : 1));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entityLiving = event.getEntityLiving();
        World world = entityLiving.getEntityWorld();

        if (entityLiving instanceof PlayerEntity && !ServerConfig.playerCanPee.get()) return;

        if (!world.isRemote) {
            UUID uuid = entityLiving.getUniqueID();
            BlockPos pos = entityLiving.getPosition();

            boolean cond1 = world.getBlockState(pos).getBlock() != Blocks.SNOW;
            boolean cond2 = world.getBlockState(pos.down()).getBlock() != Blocks.SNOW_BLOCK;
            if (cond1 && cond2) {
                entityMap.remove(uuid);
                return;
            }

            if (entityMap.containsKey(uuid)) {
                Pair<BlockPos, Integer> pair = entityMap.get(uuid);
                BlockPos oldPos = pair.getKey();
                if (isSamePos(pos, oldPos)) {
                    int timer = pair.getRight();
                    if (timer < ServerConfig.peeDuration.get() * 20) {
                        timer++;
                        entityMap.put(uuid, Pair.of(pos, timer));
                    } else {
                        if (!cond1) {
                            int i = world.getBlockState(pos).get(BlockStateProperties.LAYERS_1_8);
                            BlockState state = ModBlocks.YELLOW_SNOW.getDefaultState().with(BlockStateProperties.LAYERS_1_8, i);
                            world.setBlockState(pos, state);
                        } else {
                            world.setBlockState(pos.down(), ModBlocks.YELLOW_SNOW_BLOCK.getDefaultState());
                        }
                    }
                } else {
                    entityMap.put(uuid, Pair.of(pos, 0));
                }
            } else {
                entityMap.put(uuid, Pair.of(pos, 0));
            }
        }
    }

    @SubscribeEvent
    public static void onBreakBlock(BlockEvent.BreakEvent event) {
        if (ModList.get().isLoaded("naughtyornice")) {
            NaughtyOrNice.onHarvestBlock(event);
        }
    }

    private static boolean isSamePos(BlockPos oldPos, BlockPos currentPos) {
        return oldPos.getX() == currentPos.getX() && oldPos.getY() == currentPos.getY() && oldPos.getZ() == currentPos.getZ();
    }
}

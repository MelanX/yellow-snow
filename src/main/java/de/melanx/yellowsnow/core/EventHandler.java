package de.melanx.yellowsnow.core;

import de.melanx.yellowsnow.ModConfig;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import de.melanx.yellowsnow.core.registration.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.UUID;

public class EventHandler {

    private static final HashMap<UUID, Pair<BlockPos, Integer>> ENTITY_MAP = new HashMap<>();

    @SubscribeEvent
    public void onHitEntity(LivingAttackEvent event) {
        DamageSource source = event.getSource();
        if (source.getDirectEntity() instanceof Snowball snowball) {
            if (snowball.getItem().getItem() == ModItems.yellowSnowball) {
                event.getEntity().hurt(ModDamageSources.PEE, event.getEntity().level.random.nextInt(2) + (event.getEntity() instanceof Blaze ? 4 : 1));
            }
        }
    }

    @SubscribeEvent
    public void onEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entityLiving = event.getEntity();
        Level level = entityLiving.level;

        if (!ModConfig.forbiddenToPee.test(ForgeRegistries.ENTITY_TYPES.getKey(entityLiving.getType()))) return;

        if (!level.isClientSide) {
            UUID uuid = entityLiving.getUUID();
            BlockPos pos = entityLiving.getOnPos();

            boolean cond1 = level.getBlockState(pos.above()).getBlock() != Blocks.SNOW;
            boolean cond2 = level.getBlockState(pos).getBlock() != Blocks.SNOW_BLOCK;
            if (cond1 && cond2) {
                ENTITY_MAP.remove(uuid);
                return;
            }

            BlockPos layerPos = pos.above();
            if (ENTITY_MAP.containsKey(uuid)) {
                Pair<BlockPos, Integer> pair = ENTITY_MAP.get(uuid);
                BlockPos oldPos = pair.getKey();
                if (isSamePos(layerPos, oldPos)) {
                    int timer = pair.getRight();
                    if (timer < ModConfig.peeDuration * 20) {
                        timer++;
                        ENTITY_MAP.put(uuid, Pair.of(layerPos, timer));
                    } else {
                        if (!cond1) {
                            int i = level.getBlockState(layerPos).getValue(BlockStateProperties.LAYERS);
                            BlockState state = ModBlocks.yellowSnow.defaultBlockState().setValue(BlockStateProperties.LAYERS, i);
                            level.setBlock(layerPos, state, Block.UPDATE_ALL);
                            ENTITY_MAP.remove(uuid);
                        } else {
                            level.setBlock(pos, ModBlocks.yellowSnowBlock.defaultBlockState(), Block.UPDATE_ALL);
                            ENTITY_MAP.remove(uuid);
                        }
                    }
                } else {
                    ENTITY_MAP.put(uuid, Pair.of(layerPos, 0));
                }
            } else {
                ENTITY_MAP.put(uuid, Pair.of(layerPos, 0));
            }
        }
    }

    private static boolean isSamePos(BlockPos oldPos, BlockPos currentPos) {
        return oldPos.getX() == currentPos.getX() && oldPos.getY() == currentPos.getY() && oldPos.getZ() == currentPos.getZ();
    }
}

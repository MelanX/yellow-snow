package de.melanx.yellowsnow.compat;

import de.melanx.yellowsnow.core.registration.ModBlocks;
import io.github.championash5357.naughtyornice.api.capability.CapabilityInstances;
import net.minecraft.block.Block;
import net.minecraftforge.event.world.BlockEvent;

public class NaughtyOrNice {
    public static void onHarvestBlock(BlockEvent.BreakEvent event) {
        if (event.isCanceled() || event.getState() == null || event.getPlayer() == null || event.getPlayer().world.isRemote) return;
        Block block = event.getState().getBlock();
        if (block == ModBlocks.YELLOW_SNOW_BLOCK || block == ModBlocks.YELLOW_SNOW) {
            event.getPlayer().getCapability(CapabilityInstances.NICENESS_CAPABILITY).ifPresent(inst -> {
                inst.changeNiceness(0.005D);
            });
        }
    }
}

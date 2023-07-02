package de.melanx.yellowsnow.data;

import de.melanx.yellowsnow.core.registration.ModBlocks;
import de.melanx.yellowsnow.core.registration.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.loot.BlockLootProviderBase;
import org.moddingx.libx.mod.ModX;

@Datagen
public class LootTables extends BlockLootProviderBase {

    public LootTables(ModX mod, DataGenerator generator) {
        super(mod, generator);
    }

    @Override
    protected void setup() {
        this.customLootTable(ModBlocks.yellowSnowBlock, BlockLoot.createSingleItemTableWithSilkTouch(ModBlocks.yellowSnowBlock, ModItems.yellowSnowball, ConstantValue.exactly(4)));
        this.customLootTable(ModBlocks.yellowSnow, block -> LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemEntityPropertyCondition.entityPresent(LootContext.EntityTarget.THIS))
                        .add(AlternativesEntry.alternatives(
                                        AlternativesEntry.alternatives(SnowLayerBlock.LAYERS.getPossibleValues(), (amount) -> LootItem.lootTableItem(ModItems.yellowSnowball)
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(SnowLayerBlock.LAYERS, amount)))
                                                .apply(SetItemCountFunction
                                                        .setCount(ConstantValue.exactly(amount)))).when(BlockLoot.HAS_NO_SILK_TOUCH),
                                        AlternativesEntry.alternatives(SnowLayerBlock.LAYERS.getPossibleValues(), (amount) -> amount == 8
                                                ? LootItem.lootTableItem(ModBlocks.yellowSnowBlock)
                                                : LootItem.lootTableItem(ModBlocks.yellowSnow)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly((float) amount)))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(SnowLayerBlock.LAYERS, amount)))
                                        )
                                )
                        )
                )
        );
    }
}

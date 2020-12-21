package de.melanx.yellowsnow.data;

import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import de.melanx.yellowsnow.core.registration.ModItems;
import io.github.noeppi_noeppi.libx.data.provider.BlockLootProviderBase;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.SnowBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.functions.SetCount;

public class LootTables extends BlockLootProviderBase {
    public LootTables(DataGenerator generator) {
        super(YellowSnow.getInstance(), generator);
    }

    @Override
    protected void setup() {
        this.customLootTable(ModBlocks.YELLOW_SNOW_BLOCK, BlockLootTables.droppingWithSilkTouchOrRandomly(ModBlocks.YELLOW_SNOW_BLOCK, ModItems.YELLOW_SNOWBALL, ConstantRange.of(4)));
        this.customLootTable(ModBlocks.YELLOW_SNOW, LootTable.builder().addLootPool(LootPool.builder()
                .acceptCondition(EntityHasProperty
                        .builder(LootContext
                                .EntityTarget
                                .THIS))
                .addEntry(AlternativesLootEntry
                        .builder(AlternativesLootEntry
                                .builder(ItemLootEntry
                                        .builder(ModItems
                                                .YELLOW_SNOWBALL)
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 1))), ItemLootEntry
                                        .builder(ModItems.YELLOW_SNOWBALL)
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 2)))
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(2))), ItemLootEntry
                                        .builder(ModItems.YELLOW_SNOWBALL)
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 3)))
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(3))), ItemLootEntry
                                        .builder(ModItems
                                                .YELLOW_SNOWBALL)
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 4)))
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(4))), ItemLootEntry
                                        .builder(ModItems
                                                .YELLOW_SNOWBALL)
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 5)))
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(5))), ItemLootEntry
                                        .builder(ModItems
                                                .YELLOW_SNOWBALL)
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 6)))
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(6))), ItemLootEntry
                                        .builder(ModItems
                                                .YELLOW_SNOWBALL)
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 7)))
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(7))), ItemLootEntry
                                        .builder(ModItems
                                                .YELLOW_SNOWBALL)
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(8))))
                                .acceptCondition(BlockLootTables
                                        .NO_SILK_TOUCH), AlternativesLootEntry
                                .builder(ItemLootEntry
                                        .builder(ModBlocks
                                                .YELLOW_SNOW)
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 1))), ItemLootEntry
                                        .builder(ModBlocks
                                                .YELLOW_SNOW)
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(2)))
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 2))), ItemLootEntry
                                        .builder(ModBlocks
                                                .YELLOW_SNOW)
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(3)))
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 3))), ItemLootEntry
                                        .builder(ModBlocks
                                                .YELLOW_SNOW)
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(4)))
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 4))), ItemLootEntry
                                        .builder(ModBlocks
                                                .YELLOW_SNOW)
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(5)))
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 5))), ItemLootEntry
                                        .builder(ModBlocks
                                                .YELLOW_SNOW)
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(6)))
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 6))), ItemLootEntry
                                        .builder(ModBlocks
                                                .YELLOW_SNOW)
                                        .acceptFunction(SetCount
                                                .builder(ConstantRange.of(7)))
                                        .acceptCondition(BlockStateProperty
                                                .builder(ModBlocks
                                                        .YELLOW_SNOW)
                                                .fromProperties(StatePropertiesPredicate
                                                        .Builder
                                                        .newBuilder()
                                                        .withIntProp(SnowBlock
                                                                .LAYERS, 7))), ItemLootEntry
                                        .builder(ModBlocks
                                                .YELLOW_SNOW_BLOCK))))));
    }
}

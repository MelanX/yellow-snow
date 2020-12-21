package de.melanx.yellowsnow.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataCreator {
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(new LootTables(generator));
            generator.addProvider(new RecipesProvider(generator));
            generator.addProvider(new TagProvider(generator, helper));
        }
        if (event.includeClient()) {
            generator.addProvider(new BlockStates(generator, helper));
            generator.addProvider(new ItemModels(generator, helper));
        }
    }
}

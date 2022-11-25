package de.melanx.yellowsnow.data;

import de.melanx.yellowsnow.core.registration.ModBlocks;
import de.melanx.yellowsnow.core.registration.ModItems;
import net.minecraft.data.DataGenerator;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.recipe.RecipeProviderBase;
import org.moddingx.libx.datagen.provider.recipe.crafting.CompressionExtension;
import org.moddingx.libx.mod.ModX;

@Datagen
public class RecipesProvider extends RecipeProviderBase implements CompressionExtension {

    public RecipesProvider(ModX mod, DataGenerator generator) {
        super(mod, generator);
    }

    @Override
    protected void setup() {
        this.smallCompress(ModItems.yellowSnowball, ModBlocks.yellowSnowBlock, false);
    }
}

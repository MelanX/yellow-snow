package de.melanx.yellowsnow.data;

import de.melanx.yellowsnow.YellowSnow;
import de.melanx.yellowsnow.core.registration.ModBlocks;
import de.melanx.yellowsnow.core.registration.ModItems;
import io.github.noeppi_noeppi.libx.data.provider.recipe.RecipeProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class RecipesProvider extends RecipeProviderBase {
    public RecipesProvider(DataGenerator generator) {
        super(YellowSnow.getInstance(), generator);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        this.makeSmallBlockItem(consumer, ModBlocks.YELLOW_SNOW_BLOCK, ModItems.YELLOW_SNOWBALL);
    }
}

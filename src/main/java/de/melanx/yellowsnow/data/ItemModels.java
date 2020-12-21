package de.melanx.yellowsnow.data;

import de.melanx.yellowsnow.YellowSnow;
import io.github.noeppi_noeppi.libx.data.provider.ItemModelProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProviderBase {
    public ItemModels(DataGenerator generator, ExistingFileHelper helper) {
        super(YellowSnow.getInstance(), generator, helper);
    }

    @Override
    protected void setup() {

    }
}

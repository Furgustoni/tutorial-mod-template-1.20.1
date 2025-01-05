package net.furgus.furgusmenagerie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.furgus.furgusmenagerie.block.ModBlocks;
import net.furgus.furgusmenagerie.block.custom.LeekCropBlock;
import net.furgus.furgusmenagerie.item.ModItems;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // Add custom loot drops for various blocks
        addDrop(ModBlocks.HATSUNE_MIKU_PLUSHIE);
        addDrop(ModBlocks.HEIROGLITH_HEAD);
        addDrop(ModBlocks.LEEK_MIDDLE_BOTTOM_BLOCK);
        addDrop(ModBlocks.LEEK_MIDDLE_TOP_BLOCK);
        addDrop(ModBlocks.LEEK_BOTTOM_BLOCK);
        addDrop(ModBlocks.LEEK_TOP_BLOCK);
        addDrop(ModBlocks.LEEK_TIP_TOP_BLOCK);

        addDrop(ModBlocks.LEEK_TOP_UPPER_BLOCK, drops(ModItems.LEEK));

        // Add drop for Leek Crop when fully grown
        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.LEEK_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(LeekCropBlock.AGE, LeekCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.LEEK_CROP, this.cropDrops(ModBlocks.LEEK_CROP, ModItems.LEEK, ModItems.LEEK, builder2));
    }
}

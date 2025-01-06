package net.furgus.furgusmenagerie.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.furgus.furgusmenagerie.block.ModBlocks;
import net.furgus.furgusmenagerie.block.custom.LeekCropBlock;
import net.furgus.furgusmenagerie.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup){
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate(){
        addDrop(ModBlocks.HATSUNE_MIKU_PLUSHIE);
        addDrop(ModBlocks.HEIROGLITH_HEAD);
        addDrop(ModBlocks.LEEK_MIDDLE_BOTTOM_BLOCK);
        addDrop(ModBlocks.LEEK_MIDDLE_TOP_BLOCK);
        addDrop(ModBlocks.LEEK_BOTTOM_BLOCK);
        addDrop(ModBlocks.LEEK_TOP_BLOCK);
        addDrop(ModBlocks.LEEK_TIP_TOP_BLOCK);
        addDrop(ModBlocks.LEEK_TOP_UPPER_BLOCK);

        addDrop(ModBlocks.HEIROGLITH_HEAD);

        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.LEEK_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(LeekCropBlock.AGE, LeekCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.LEEK_CROP, this.cropDrops(ModBlocks.LEEK_CROP, ModItems.LEEK, ModItems.LEEK, builder2));


    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}



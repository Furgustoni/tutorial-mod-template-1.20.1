package net.furgus.furgusmenagerie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.furgus.furgusmenagerie.block.ModBlocks;
import net.furgus.furgusmenagerie.item.ModItems;
import net.furgus.furgusmenagerie.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.HATSUNE_MIKU_PLUSHIE, 1)
            .pattern("SSS")
                .pattern("SRS")
                .pattern("SSS")
                .input('S', Items.CYAN_WOOL)
                .input('R', ModTags.Items.HATSUNE_MUSIC_DISC)
                .criterion(hasItem(Items.CYAN_WOOL), conditionsFromItem(Items.CYAN_WOOL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.HATSUNE_MIKU_PLUSHIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCULK_HOVERBOARD_ITEM, 1)
                .pattern("SBS")
                .pattern("SAS")
                .pattern("BCB")
                .input('S', Blocks.SCULK)
                .input('A', Blocks.SCULK_SHRIEKER)
                .input('B', Items.BONE_BLOCK)
                .input('C', Items.AMETHYST_BLOCK)
                .criterion(hasItem(Blocks.SCULK), conditionsFromItem(Items.SCULK_SHRIEKER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SCULK_HOVERBOARD_ITEM)));
}
}
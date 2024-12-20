package net.furgus.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.furgus.tutorialmod.block.ModBlocks;
import net.furgus.tutorialmod.entity.ModEntities;
import net.furgus.tutorialmod.entity.client.MonoglyphModel;
import net.furgus.tutorialmod.entity.custom.MonoglyphEntity;
import net.furgus.tutorialmod.item.ModItemGroups;
import net.furgus.tutorialmod.item.ModItems;
import net.furgus.tutorialmod.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Very important comment
public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModSounds.registerSounds();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FabricDefaultAttributeRegistry.register(ModEntities.MONOGLYPH, MonoglyphEntity.setAttributes());

		CompostingChanceRegistry.INSTANCE.add(ModItems.LEEK, 0.3F);
	}
}
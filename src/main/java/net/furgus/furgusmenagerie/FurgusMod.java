package net.furgus.furgusmenagerie;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.furgus.furgusmenagerie.block.ModBlocks;
import net.furgus.furgusmenagerie.block.entity.ModBlockEntities;
import net.furgus.furgusmenagerie.entity.ModEntities;
import net.furgus.furgusmenagerie.entity.custom.HeiroglithEntity;
import net.furgus.furgusmenagerie.entity.custom.MonoglyphEntity;
import net.furgus.furgusmenagerie.entity.custom.SculkHoverboardEntity;
import net.furgus.furgusmenagerie.item.ModItemGroups;
import net.furgus.furgusmenagerie.item.ModItems;
import net.furgus.furgusmenagerie.particle.ModParticles;
import net.furgus.furgusmenagerie.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

// Very important comment
public class FurgusMod implements ModInitializer {
	public static final String MOD_ID = "furgusmenagerie";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModSounds.registerSounds();

		ModBlockEntities.registerAllBlockEntities();

		GeckoLib.initialize ();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModParticles.registerParticles ();

		FabricDefaultAttributeRegistry.register(ModEntities.MONOGLYPH, MonoglyphEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.HEIROGLITH, HeiroglithEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SCULK_HOVERBOARD, SculkHoverboardEntity.setAttributes());

		CompostingChanceRegistry.INSTANCE.add(ModItems.LEEK, 0.3F);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.LEEK_BOTTOM_BLOCK, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.LEEK_MIDDLE_BOTTOM_BLOCK, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.LEEK_MIDDLE_TOP_BLOCK, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.LEEK_TOP_BLOCK, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.LEEK_TIP_TOP_BLOCK, 0.7F);
		CompostingChanceRegistry.INSTANCE.add(ModBlocks.LEEK_TOP_UPPER_BLOCK, 0.7F);
	}
}
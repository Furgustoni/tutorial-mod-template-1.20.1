package net.furgus.furgusmenagerie.sound;

import net.furgus.furgusmenagerie.FurgusMod;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent HATSUNE_MIKU_PLACE = registerSoundEvent("hatsune_miku_place");
    public static final SoundEvent HATSUNE_MIKU_STEP = registerSoundEvent("hatsune_miku_step");
    public static final SoundEvent HATSUNE_MIKU_BREAK = registerSoundEvent("hatsune_miku_break");
    public static final SoundEvent HATSUNE_MIKU_FALL = registerSoundEvent("hatsune_miku_fall");
    public static final SoundEvent HATSUNE_MIKU_HIT = registerSoundEvent("hatsune_miku_hit");

    public static final SoundEvent MONOGLYPH_AMBIENT = registerSoundEvent("monoglyph_ambient");
    public static final SoundEvent MONOGLYPH_STEP = registerSoundEvent("monoglyph_step");
    public static final SoundEvent MONOGLYPH_HURT = registerSoundEvent("monoglyph_hurt");
    public static final SoundEvent MONOGLYPH_DEATH = registerSoundEvent("monoglyph_death");

    public static final SoundEvent HATSUNE_MIKU_MUSIC_1 = registerSoundEvent("hatsune_miku_music_1");
    public static final SoundEvent HATSUNE_MIKU_MUSIC_2 = registerSoundEvent("hatsune_miku_music_2");

    public static final SoundEvent SCULK_HOVERBOARD_HOVERING = registerSoundEvent("sculk_hoverboard_hovering");
    public static final SoundEvent SCULK_HOVERBOARD_AMBIENT = registerSoundEvent("sculk_hoverboard_ambient");

    public static final BlockSoundGroup HATSUNE_MIKU_SOUND = new BlockSoundGroup(
            0.055f, 1.2f,
            HATSUNE_MIKU_BREAK, // break sound
            HATSUNE_MIKU_STEP, // step sound
            HATSUNE_MIKU_PLACE, // place sound
            HATSUNE_MIKU_HIT, // hit sound
            HATSUNE_MIKU_FALL  // fall sound
    );

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.of(FurgusMod.MOD_ID,name);
        return Registry.register(Registries.SOUND_EVENT,id, SoundEvent.of(id));
    }

    public static void registerSounds(){
        FurgusMod.LOGGER.info("Registering Mod sounds for " + FurgusMod.MOD_ID);
    }
}

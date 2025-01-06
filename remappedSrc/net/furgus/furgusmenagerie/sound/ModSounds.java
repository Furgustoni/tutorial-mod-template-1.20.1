package net.furgus.furgusmenagerie.sound;

import net.furgus.furgusmenagerie.FurgusMod;
import net.minecraft.block.jukebox.JukeboxSong;
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

    public static final SoundEvent HATSUNE_MIKU_DISC_1 = registerSoundEvent("hatsune_miku_disc_1");
    public static final RegistryKey<JukeboxSong> HATSUNE_MIKU_DISC_1_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(FurgusMod.MOD_ID, "hatsune_miku_disc_1"));

    public static final SoundEvent HATSUNE_MIKU_DISC_2 = registerSoundEvent("hatsune_miku_disc_2");
    public static final RegistryKey<JukeboxSong> HATSUNE_MIKU_DISC_2_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(FurgusMod.MOD_ID, "hatsune_miku_disc_2"));

    public static final SoundEvent BAR_BRAWL = registerSoundEvent("bar_brawl");
    public static final RegistryKey<JukeboxSong> BAR_BRAWL_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(FurgusMod.MOD_ID, "bar_brawl"));


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

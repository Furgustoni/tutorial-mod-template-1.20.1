package net.furgus.tutorialmod.sound;

import net.furgus.tutorialmod.TutorialMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent HATSUNE_MIKU_PLACE = registerSoundEvent("hatsune_miku_place");
    public static final SoundEvent HATSUNE_MIKU_STEP = registerSoundEvent("hatsune_miku_step");
    public static final SoundEvent HATSUNE_MIKU_BREAK = registerSoundEvent("hatsune_miku_break");
    public static final SoundEvent HATSUNE_MIKU_FALL = registerSoundEvent("hatsune_miku_fall");
    public static final SoundEvent HATSUNE_MIKU_HIT = registerSoundEvent("hatsune_miku_hit");

    public static final BlockSoundGroup HATSUNE_MIKU_SOUND = new BlockSoundGroup(
            0.055f, 1f,
            HATSUNE_MIKU_BREAK, // break sound
            HATSUNE_MIKU_STEP, // step sound
            HATSUNE_MIKU_PLACE, // place sound
            HATSUNE_MIKU_HIT, // hit sound
            HATSUNE_MIKU_FALL  // fall sound
    );

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.of(TutorialMod.MOD_ID,name);
        return Registry.register(Registries.SOUND_EVENT,id, SoundEvent.of(id));
    }

    public static void registerSounds(){
        TutorialMod.LOGGER.info("Registering Mod sounds for " + TutorialMod.MOD_ID);
    }
}

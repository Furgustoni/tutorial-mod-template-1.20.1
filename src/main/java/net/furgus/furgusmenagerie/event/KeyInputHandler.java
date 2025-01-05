package net.furgus.furgusmenagerie.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.furgus.furgusmenagerie.entity.custom.SculkHoverboardEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_FURGUS = "key.category.furgusmenagerie.keybinds";
    public static final String HOVER_DISMOUNT = "key.furgusmenagerie.hover_dismount";
    public static final String HOVER_UP = "key.furgusmenagerie.hover_up";
    public static final String HOVER_DOWN = "key.furgusmenagerie.hover_down";

    public static KeyBinding dismountKey;
    public static KeyBinding hoverUpKey;
    public static KeyBinding hoverDownKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.player.getVehicle() instanceof SculkHoverboardEntity) {
                SculkHoverboardEntity hoverboard = (SculkHoverboardEntity) client.player.getVehicle();

                // Check key bindings
                if (hoverUpKey.isPressed()) {
                    hoverboard.moveUp();
                }

                if (hoverDownKey.isPressed()) {
                    hoverboard.moveDown();
                }
            }
        });
    }

    public static void register() {
        dismountKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                HOVER_DISMOUNT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_FURGUS
        ));

        hoverUpKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                HOVER_UP,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_CONTROL,
                KEY_CATEGORY_FURGUS
        ));

        hoverDownKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                HOVER_DOWN,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                KEY_CATEGORY_FURGUS
        ));

        registerKeyInputs();
    }
}
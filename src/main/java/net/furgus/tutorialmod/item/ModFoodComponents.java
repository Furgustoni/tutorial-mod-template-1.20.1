package net.furgus.tutorialmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;




public class ModFoodComponents {
    public static final FoodComponent LEEK = new FoodComponent.Builder().nutrition(2).saturationModifier(0.35f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200), 0.4f).build();
}

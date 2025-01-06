package net.furgus.furgusmenagerie.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;




public class ModFoodComponents {
    public static final FoodComponent LEEK = new FoodComponent.Builder().hunger(2).saturationModifier(0.35f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200), 0.4f).build();
}

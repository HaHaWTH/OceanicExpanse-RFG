package com.sirsquidly.oe.mixin.common.late.aquaacrobatics;

import com.fuzs.aquaacrobatics.biome.BiomeWaterFogColors;
import com.sirsquidly.oe.init.OEBiomes;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(value = BiomeWaterFogColors.class, remap = false)
public abstract class BiomeWaterFogColorsMixin {
    @Shadow @Final private static HashMap<ResourceLocation, Integer> baseColorMap;

    @Shadow @Final private static HashMap<ResourceLocation, Integer> fogColorMap;

    @Inject(
            method = "recomputeColors",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/HashMap;clear()V",
                    shift = At.Shift.AFTER,
                    ordinal = 1
            )
    )
    private static void recomputeColors(CallbackInfo ci) {
        baseColorMap.put(OEBiomes.WARM_OCEAN.getRegistryName(), 4445678);
        baseColorMap.put(OEBiomes.DEEP_WARM_OCEAN.getRegistryName(), 4445678);
        baseColorMap.put(OEBiomes.LUKEWARM_OCEAN.getRegistryName(), 4566514);
        baseColorMap.put(OEBiomes.DEEP_LUKEWARM_OCEAN.getRegistryName(), 4566514);
        baseColorMap.put(OEBiomes.COLD_OCEAN.getRegistryName(), 4020182);
        baseColorMap.put(OEBiomes.DEEP_COLD_OCEAN.getRegistryName(), 4020182);

        fogColorMap.put(OEBiomes.WARM_OCEAN.getRegistryName(), 270131);
        fogColorMap.put(OEBiomes.DEEP_WARM_OCEAN.getRegistryName(), 270131);
        fogColorMap.put(OEBiomes.LUKEWARM_OCEAN.getRegistryName(), 267827);
        fogColorMap.put(OEBiomes.DEEP_LUKEWARM_OCEAN.getRegistryName(), 267827);
        fogColorMap.put(OEBiomes.COLD_OCEAN.getRegistryName(), 329011);
        fogColorMap.put(OEBiomes.DEEP_COLD_OCEAN.getRegistryName(), 329011);
    }
}

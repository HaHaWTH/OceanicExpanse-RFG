package com.sirsquidly.oe.mixin.common.biome;

import com.sirsquidly.oe.api.biome.IOceanCustomSurfaceProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Biome.class)
public abstract class BiomeMixin {
    @Redirect(
            method = "generateBiomeTerrain",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/chunk/ChunkPrimer;setBlockState(IIILnet/minecraft/block/state/IBlockState;)V",
                    ordinal = 2
            )
    )
    public void generateBiomeTerrain(ChunkPrimer instance, int x, int y, int z, IBlockState state) {
        if (this instanceof IOceanCustomSurfaceProvider) {
            instance.setBlockState(x, y, z, ((IOceanCustomSurfaceProvider) this).getOceanSurface());
        } else {
            instance.setBlockState(x, y, z, state);
        }
    }
}

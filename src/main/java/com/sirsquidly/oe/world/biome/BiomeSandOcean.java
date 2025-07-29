package com.sirsquidly.oe.world.biome;

import com.sirsquidly.oe.world.BaseBiomeOcean;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BiomeSandOcean extends BaseBiomeOcean {
    public BiomeSandOcean(@Nullable Biome deepOceanBiomeIn, @Nonnull BiomeProperties propertiesIn) {
        super(deepOceanBiomeIn, propertiesIn);
        surfaceBlock = Blocks.SAND.getDefaultState();
    }
}

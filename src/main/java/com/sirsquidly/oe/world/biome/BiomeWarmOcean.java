package com.sirsquidly.oe.world.biome;

import com.sirsquidly.oe.api.biome.OceanType;
import com.sirsquidly.oe.init.OEBiomes;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BiomeWarmOcean extends BiomeSandOcean {
    public BiomeWarmOcean(@Nullable Biome deepOceanBiomeIn, @Nonnull BiomeProperties propertiesIn) {
        super(deepOceanBiomeIn, propertiesIn);
        super.waterColor = 0x43D5EE;
    }

    @Nonnull
    @Override
    public Biome getMixOceanBiome() { return OEBiomes.LUKEWARM_OCEAN; }

    @Nullable
    @Override
    public OceanType getOceanType() { return OceanType.WARM; }
}

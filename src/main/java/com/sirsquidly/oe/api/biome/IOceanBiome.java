package com.sirsquidly.oe.api.biome;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;

public interface IOceanBiome extends IOceanCustomSurfaceProvider {
    /**
     * Returns -1 if this is a deep ocean.
     * This is called often during world gen, so it's recommended to return a constant instead of using Biome.getIdForBiome.
     */
    int getDeepOceanBiomeId();

    /**
     * Returns itself if this has no mix ocean biome.
     * Used to gradually transition from shore/beach biomes.
     */
    Biome getMixOceanBiome();

    /**
     * This method is used to automatically register this biome for generation during runtime, with a generation weight of 100.
     * Returning null on this method will cause subaquatic to not automatically register this biome for generation.
     * @return this biome's ocean type.
     * @since 1.3.0
     */
    default OceanType getOceanType() { return null; }

    /**
     * This is auto-generated at runtime, all ocean biomes are added.
     */
    IntSet OCEAN_IDS = new IntOpenHashSet();
    static boolean isOcean(int biome) { return OCEAN_IDS.contains(biome); }
    static boolean isOcean(Biome biome) { return BiomeManager.oceanBiomes.contains(biome); }

    /**
     * This is auto-generated at runtime, all ocean biomes that have their getDeepOceanBiomeId() not return -1 are added.
     */
    IntSet SHALLOW_OCEAN_IDS = new IntOpenHashSet(new int[] {0, 10});
    static boolean isShallowOcean(int biome) { return SHALLOW_OCEAN_IDS.contains(biome); }
    static boolean isShallowOcean(Biome biome) {
        return biome == Biomes.OCEAN || biome instanceof IOceanBiome && ((IOceanBiome)biome).getDeepOceanBiomeId() != -1;
    }
}
package com.sirsquidly.oe.api.biome;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public interface IOceanCustomSurfaceProvider {
    default IBlockState getOceanSurface() { return Blocks.SAND.getDefaultState(); }
}

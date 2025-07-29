package com.sirsquidly.oe.api.biome;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.Nonnull;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

@Cancelable
public class GetOceanForGenEvent extends Event {
    @Nonnull
    protected final IntUnaryOperator intRandomNumberGenerator;
    @Nonnull
    protected final LongUnaryOperator longRandomNumberGenerator;
    @Nonnull
    protected Biome oceanForGen;
    public final double temperatureNoise;

    public GetOceanForGenEvent(final double temperatureNoiseIn, @Nonnull final IntUnaryOperator intRandomNumberGeneratorIn, @Nonnull final LongUnaryOperator longRandomNumberGeneratorIn) {
        temperatureNoise = temperatureNoiseIn;
        intRandomNumberGenerator = intRandomNumberGeneratorIn;
        longRandomNumberGenerator = longRandomNumberGeneratorIn;
        oceanForGen = Biomes.OCEAN;
    }

    @Nonnull
    public Biome getOcean() {
        return oceanForGen;
    }

    public void setOcean(@Nonnull final Biome oceanForGenIn) {
        oceanForGen = OceanType.ensureIsOcean(oceanForGenIn);
        setCanceled(true);
    }

    public void setOceanRaw(@Nonnull final Biome oceanForGenIn) {
        oceanForGen = OceanType.ensureIsOcean(oceanForGenIn);
    }

    public int getRandomInt(final int bound) {
        return intRandomNumberGenerator.applyAsInt(bound);
    }

    public long getRandomLong(final long bound) {
        return longRandomNumberGenerator.applyAsLong(bound);
    }
}

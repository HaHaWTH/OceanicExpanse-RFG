package com.sirsquidly.oe.core;

import com.google.common.collect.ImmutableMap;
import com.sirsquidly.oe.Main;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import net.minecraftforge.fml.common.versioning.InvalidVersionSpecificationException;
import net.minecraftforge.fml.common.versioning.VersionRange;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class OEMixinLateLoader implements ILateMixinLoader {
    public static final boolean isClient = FMLLaunchHandler.side().isClient();

    private static final Map<String, Supplier<Boolean>> commonMixinConfigs = ImmutableMap.copyOf(new LinkedHashMap<String, Supplier<Boolean>>() {
        {
            put("mixins.late.aquaacrobatics.json", () -> isModLoaded("aquaacrobatics"));
        }
    });

    private static final Map<String, Supplier<Boolean>> clientsideMixinConfigs = ImmutableMap.copyOf(new LinkedHashMap<String, Supplier<Boolean>>() {
    });

    @Override
    public List<String> getMixinConfigs() {
        List<String> configs = new ArrayList<>(commonMixinConfigs.keySet());
        if (isClient) {
            configs.addAll(clientsideMixinConfigs.keySet());
        }
        return configs;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        Supplier<Boolean> sidedSupplier = isClient ? clientsideMixinConfigs.get(mixinConfig) : null;
        Supplier<Boolean> commonSupplier = commonMixinConfigs.get(mixinConfig);
        if (sidedSupplier != null) {
            return sidedSupplier.get();
        }
        if (commonSupplier != null) {
            return commonSupplier.get();
        }
        return true;
    }

    private static boolean isModLoaded(String modId) {
        return Loader.isModLoaded(modId);
    }

    private static boolean isModVersionInRange(String modId, String versionRange) {
        ModContainer modContainer = FMLCommonHandler.instance().findContainerFor(modId);
        if (modContainer == null) {
            return false;
        }
        String actualVersionString = modContainer.getVersion();
        ArtifactVersion actualVersion = new DefaultArtifactVersion(actualVersionString);

        try {
            VersionRange requiredRange = VersionRange.createFromVersionSpec(versionRange);
            return requiredRange.containsVersion(actualVersion);
        } catch (InvalidVersionSpecificationException e) {
            Main.logger.error("Invalid version range specification: {}", versionRange, e);
            return false;
        }
    }
}

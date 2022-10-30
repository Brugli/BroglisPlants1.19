package net.brogli.broglisplants.world.feature;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.BroglisPlantsBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, BroglisPlants.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> FLYTRAP = CONFIGURED_FEATURES.register("flytrap",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(128, 12, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(BroglisPlantsBlocks.FLYTRAP_BLOCK.get()))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CORPSE_FLOWER = CONFIGURED_FEATURES.register("corpse_flower",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(64, 0, 0, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(BroglisPlantsBlocks.CORPSE_FLOWER_BLOCK.get()))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEADLY_NIGHTSHADE = CONFIGURED_FEATURES.register("deadly_nightshade",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(128, 1, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(BroglisPlantsBlocks.DEADLY_NIGHTSHADE_BLOCK.get()))))));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}

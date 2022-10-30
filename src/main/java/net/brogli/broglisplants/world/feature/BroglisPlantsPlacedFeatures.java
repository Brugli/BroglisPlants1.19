package net.brogli.broglisplants.world.feature;

import net.brogli.broglisplants.BroglisPlants;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class BroglisPlantsPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, BroglisPlants.MOD_ID);

    public static final RegistryObject<PlacedFeature> FLYTRAP_PLACED = PLACED_FEATURES.register("flytrap_placed",
            () -> new PlacedFeature(BroglisPlantsConfiguredFeatures.FLYTRAP.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(2),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> CORPSE_FLOWER_PLACED = PLACED_FEATURES.register("corpse_flower_placed",
            () -> new PlacedFeature(BroglisPlantsConfiguredFeatures.CORPSE_FLOWER.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(2),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> DEADLY_NIGHTSHADE_PLACED = PLACED_FEATURES.register("deadly_nightshade_placed",
            () -> new PlacedFeature(BroglisPlantsConfiguredFeatures.DEADLY_NIGHTSHADE.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(2),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}

package net.brogli.broglisplants.particle;

import net.brogli.broglisplants.BroglisPlants;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BroglisPlants.MOD_ID);

    public static final RegistryObject<SimpleParticleType> CORPSE_FLOWER_PARTICLES =
            PARTICLE_TYPES.register("corpse_flower_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> DEADLY_NIGHTSHADE_PARTICLES =
            PARTICLE_TYPES.register("deadly_nightshade_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}

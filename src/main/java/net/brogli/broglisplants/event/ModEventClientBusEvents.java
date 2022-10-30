package net.brogli.broglisplants.event;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.BroglisPlantsBlockEntities;
import net.brogli.broglisplants.block.client.CorpseFlowerRenderer;
import net.brogli.broglisplants.block.client.FlytrapRenderer;
import net.brogli.broglisplants.block.client.PottedCorpseFlowerRenderer;
import net.brogli.broglisplants.block.client.PottedFlytrapRenderer;
import net.brogli.broglisplants.particle.BroglisPlantsParticles;
import net.brogli.broglisplants.particle.custom.CorpseFlowerParticles;
import net.brogli.broglisplants.particle.custom.DeadlyNightshadeParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BroglisPlants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {

    }
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BroglisPlantsBlockEntities.ENTITY_FLYTRAP.get(), FlytrapRenderer::new);
        event.registerBlockEntityRenderer(BroglisPlantsBlockEntities.POTTED_ENTITY_FLYTRAP.get(), PottedFlytrapRenderer::new);
        event.registerBlockEntityRenderer(BroglisPlantsBlockEntities.ENTITY_CORPSE_FLOWER.get(), CorpseFlowerRenderer::new);
        event.registerBlockEntityRenderer(BroglisPlantsBlockEntities.POTTED_ENTITY_CORPSE_FLOWER.get(), PottedCorpseFlowerRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(BroglisPlantsParticles.CORPSE_FLOWER_PARTICLES.get(),
                CorpseFlowerParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(BroglisPlantsParticles.DEADLY_NIGHTSHADE_PARTICLES.get(),
                DeadlyNightshadeParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(BroglisPlantsParticles.DEADLY_NIGHTSHADE_PARTICLES.get(),
                DeadlyNightshadeParticles.EmissiveProvider::new);
    }
}

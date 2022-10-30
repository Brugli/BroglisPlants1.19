package net.brogli.broglisplants;

import com.mojang.logging.LogUtils;
import net.brogli.broglisplants.block.BroglisPlantsBlockEntities;
import net.brogli.broglisplants.block.BroglisPlantsBlocks;
import net.brogli.broglisplants.item.BroglisPlantsItems;
import net.brogli.broglisplants.particle.BroglisPlantsParticles;
import net.brogli.broglisplants.world.feature.BroglisPlantsConfiguredFeatures;
import net.brogli.broglisplants.world.feature.BroglisPlantsPlacedFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;


@Mod(BroglisPlants.MOD_ID)
public class BroglisPlants {
    public static final String MOD_ID = "broglisplants";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BroglisPlants() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BroglisPlantsItems.register(eventBus);
        BroglisPlantsBlocks.register(eventBus);
        BroglisPlantsBlockEntities.register(eventBus);
        BroglisPlantsParticles.register(eventBus);
        BroglisPlantsConfiguredFeatures.register(eventBus);
        BroglisPlantsPlacedFeatures.register(eventBus);

        eventBus.addListener(this::setup);

        FMLJavaModLoadingContext.get().getModEventBus();

        GeckoLibMod.DISABLE_IN_DEV = true;
        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BroglisPlantsBlocks.DEADLY_NIGHTSHADE_BLOCK.getId(), BroglisPlantsBlocks.POTTED_DEADLY_NIGHTSHADE);
        });
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}

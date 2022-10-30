package net.brogli.broglisplants.block;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.entity.EntityCorpseFlower;
import net.brogli.broglisplants.block.entity.EntityFlytrap;
import net.brogli.broglisplants.block.entity.PottedEntityCorpseFlower;
import net.brogli.broglisplants.block.entity.PottedEntityFlytrap;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BROGLIS_PLANTS_BLOCK_ENTITY_TYPES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, BroglisPlants.MOD_ID);

    public static final RegistryObject<BlockEntityType<EntityFlytrap>> ENTITY_FLYTRAP = BROGLIS_PLANTS_BLOCK_ENTITY_TYPES
            .register("entity_flytrap", () -> BlockEntityType.Builder
                    .of(EntityFlytrap::new, BroglisPlantsBlocks.FLYTRAP_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<PottedEntityFlytrap>> POTTED_ENTITY_FLYTRAP = BROGLIS_PLANTS_BLOCK_ENTITY_TYPES
            .register("potted_entity_flytrap", () -> BlockEntityType.Builder
                    .of(PottedEntityFlytrap::new, BroglisPlantsBlocks.POTTED_FLYTRAP_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<EntityCorpseFlower>> ENTITY_CORPSE_FLOWER = BROGLIS_PLANTS_BLOCK_ENTITY_TYPES
            .register("entity_corpse_flower", () -> BlockEntityType.Builder
                    .of(EntityCorpseFlower::new, BroglisPlantsBlocks.CORPSE_FLOWER_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<PottedEntityCorpseFlower>> POTTED_ENTITY_CORPSE_FLOWER = BROGLIS_PLANTS_BLOCK_ENTITY_TYPES
            .register("potted_entity_corpse_flower", () -> BlockEntityType.Builder
                    .of(PottedEntityCorpseFlower::new, BroglisPlantsBlocks.POTTED_CORPSE_FLOWER_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BROGLIS_PLANTS_BLOCK_ENTITY_TYPES.register(eventBus);
    }
}

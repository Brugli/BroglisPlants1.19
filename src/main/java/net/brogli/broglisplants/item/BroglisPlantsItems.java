package net.brogli.broglisplants.item;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.BroglisPlantsBlocks;
import net.brogli.broglisplants.item.custom.ItemCorpseFlowerSeeds;
import net.brogli.broglisplants.item.custom.ItemFlytrapSeeds;
import net.brogli.broglisplants.item.custom.ItemGiantLilySeeds;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BroglisPlants.MOD_ID);

    public static final RegistryObject<Item> ITEM_FLYTRAP_SEEDS = ITEMS.register("item_flytrap_seeds",
            () -> new ItemFlytrapSeeds(BroglisPlantsBlocks.FLYTRAP_BLOCK.get(), new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ITEM_CORPSE_FLOWER_SEEDS = ITEMS.register("item_corpse_flower_seeds",
            () -> new ItemCorpseFlowerSeeds(BroglisPlantsBlocks.CORPSE_FLOWER_BLOCK.get(), new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ITEM_GIANT_LILY_SEEDS = ITEMS.register("item_giant_lily_seeds",
            () -> new ItemGiantLilySeeds(BroglisPlantsBlocks.GIANT_LILY_BUD.get(), new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

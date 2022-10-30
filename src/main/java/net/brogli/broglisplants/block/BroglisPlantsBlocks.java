package net.brogli.broglisplants.block;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.custom.*;
import net.brogli.broglisplants.item.BroglisPlantsItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BroglisPlantsBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BroglisPlants.MOD_ID);

    public static final RegistryObject<FlytrapBlock> FLYTRAP_BLOCK = BLOCKS.register("flytrap_block",
            () -> new FlytrapBlock(FlytrapBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<PottedFlytrapBlock> POTTED_FLYTRAP_BLOCK = BLOCKS.register("potted_flytrap_block",
            () -> new PottedFlytrapBlock(PottedFlytrapBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<CorpseFlowerBlock> CORPSE_FLOWER_BLOCK = BLOCKS.register("corpse_flower_block",
            () -> new CorpseFlowerBlock(BlockBehaviour.Properties.copy(Blocks.WEEPING_VINES)));

    public static final RegistryObject<PottedCorpseFlowerBlock> POTTED_CORPSE_FLOWER_BLOCK = BLOCKS.register("potted_corpse_flower_block",
            () -> new PottedCorpseFlowerBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<DeadlyNightshadeBlock> DEADLY_NIGHTSHADE_BLOCK = registerBlock("deadly_nightshade_block",
            () ->  new DeadlyNightshadeBlock(MobEffects.POISON, 12, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> POTTED_DEADLY_NIGHTSHADE = BLOCKS.register("potted_deadly_nightshade",
            () ->  new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), BroglisPlantsBlocks.DEADLY_NIGHTSHADE_BLOCK, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return BroglisPlantsItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
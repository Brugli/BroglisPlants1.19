package net.brogli.broglisplants.block;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.custom.*;
import net.brogli.broglisplants.block.custom.lilypadparts.*;
import net.brogli.broglisplants.item.BroglisPlantsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
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

    public static final RegistryObject<GiantLilyBudBlock> GIANT_LILY_BUD = BLOCKS.register("giant_lily_bud",
            () ->  new GiantLilyBudBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilySmallBlock> GIANT_LILY_SMALL = BLOCKS.register("giant_lily_small",
            () ->  new GiantLilySmallBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilyCenterBlock> GIANT_LILY_PAD_CENTER = BLOCKS.register("giant_lily_pad_center",
            () ->  new GiantLilyCenterBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilyNorthBlock> GIANT_LILY_PAD_NORTH = BLOCKS.register("giant_lily_pad_north",
            () ->  new GiantLilyNorthBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilyNorthEastBlock> GIANT_LILY_PAD_NORTH_EAST = BLOCKS.register("giant_lily_pad_north_east",
            () ->  new GiantLilyNorthEastBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilyEastBlock> GIANT_LILY_PAD_EAST = BLOCKS.register("giant_lily_pad_east",
            () ->  new GiantLilyEastBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilySouthEastBlock> GIANT_LILY_PAD_SOUTH_EAST = BLOCKS.register("giant_lily_pad_south_east",
            () ->  new GiantLilySouthEastBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilySouthBlock> GIANT_LILY_PAD_SOUTH = BLOCKS.register("giant_lily_pad_south",
            () ->  new GiantLilySouthBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilySouthWestBlock> GIANT_LILY_PAD_SOUTH_WEST = BLOCKS.register("giant_lily_pad_south_west",
            () ->  new GiantLilySouthWestBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilyWestBlock> GIANT_LILY_PAD_WEST = BLOCKS.register("giant_lily_pad_west",
            () ->  new GiantLilyWestBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilyNorthWestBlock> GIANT_LILY_PAD_NORTH_WEST = BLOCKS.register("giant_lily_pad_north_west",
            () ->  new GiantLilyNorthWestBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<GiantLilyStemBlock> GIANT_LILY_STEM = BLOCKS.register("giant_lily_stem",
            () ->  new GiantLilyStemBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS)));

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
package net.brogli.broglisplants.event;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.BroglisPlantsBlocks;
import net.brogli.broglisplants.block.custom.GiantLilyBudBlock;
import net.brogli.broglisplants.particle.BroglisPlantsParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BroglisPlants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {

    @SubscribeEvent
    public static void blockBreakEvent(final BlockEvent.BreakEvent event) {
        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);
        BlockState belowState = event.getLevel().getBlockState(pos.below());
        Material material = belowState.getMaterial();

        if ((state.is(BroglisPlantsBlocks.GIANT_LILY_BUD.get()) || state.is(BroglisPlantsBlocks.GIANT_LILY_SMALL.get()) || state.is(BroglisPlantsBlocks.GIANT_LILY_PAD_CENTER.get()))  && !material.isSolid()) {
            event.getLevel().setBlock(pos.below(), BroglisPlantsBlocks.GIANT_LILY_BUD.get().defaultBlockState().setValue(GiantLilyBudBlock.WATERLOGGED, true), 2);
        }
        if (state.is(BroglisPlantsBlocks.GIANT_LILY_STEM.get())) {
            event.setCanceled(!material.isSolid());
            event.getLevel().setBlock(event.getPos(), BroglisPlantsBlocks.GIANT_LILY_BUD.get().defaultBlockState().setValue(GiantLilyBudBlock.WATERLOGGED, true), 2);
        }
    }

//    @SubscribeEvent
//    public static void SeedPlantEvent(final ItemExpireEvent event) {
//
//        Level level = event.getEntity().getLevel();
//        ItemStack stack = event.getEntity().getItem();
//        assert stack.getEntityRepresentation() != null;
//        Block blockBelow = level.getBlockState(stack.getEntityRepresentation().blockPosition().below()).getBlock();
//        Block blockAbove = level.getBlockState(stack.getEntityRepresentation().blockPosition().above()).getBlock();
//        Block block = level.getBlockState(stack.getEntityRepresentation().blockPosition()).getBlock();
//
//        if (!level.isClientSide) {
//            if (event.getEntity().getItem().is(BroglisPlantsItems.ITEM_FLYTRAP_SEEDS.get())) {
//
//                if (block == Blocks.MUD && blockAbove == Blocks.AIR) {
//                    level.setBlock(stack.getEntityRepresentation().blockPosition().above(), BroglisPlantsBlocks.FLYTRAP_BLOCK.get().defaultBlockState(), 2);
//                }
//            }
//            if (event.getEntity().getItem().is(BroglisPlantsItems.ITEM_CORPSE_FLOWER_SEEDS.get())) {
//
//                if (blockBelow == Blocks.PODZOL && block == Blocks.AIR) {
//                    level.setBlock(stack.getEntityRepresentation().blockPosition(), BroglisPlantsBlocks.CORPSE_FLOWER_BLOCK.get().defaultBlockState(), 2);
//                }
//            }
//        }
//    }
}

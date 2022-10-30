package net.brogli.broglisplants.event;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.BroglisPlantsBlocks;
import net.brogli.broglisplants.particle.BroglisPlantsParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BroglisPlants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {

//    @SubscribeEvent
//    public static void blockParticleEvent(final BlockEvent event) {
//
//        Block block = event.getState().getBlock();
//        BlockPos pos = event.getPos();
//        Level level = (Level) event.getLevel();
//        if (block == BroglisPlantsBlocks.POTTED_DEADLY_NIGHTSHADE.get()) {
//            block.tick(block.defaultBlockState(), (ServerLevel) event.getLevel(), pos, level.random);
//            if (level.random.nextFloat() > 0.85) {
//                double d1 = level.random.nextDouble() * (0.2 - 0.1) + 0.1;
//                level.addParticle(BroglisPlantsParticles.DEADLY_NIGHTSHADE_PARTICLES.get(), (pos.getX() + 0.35) + d1, pos.getY() + 0.9D, (pos.getZ() + 0.35) + d1, 0.0D, 0.02D, 0.0D);
//            }
//        }
//    }


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

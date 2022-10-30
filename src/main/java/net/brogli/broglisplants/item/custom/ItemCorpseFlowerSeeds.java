package net.brogli.broglisplants.item.custom;

import net.brogli.broglisplants.block.BroglisPlantsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

public class ItemCorpseFlowerSeeds extends BlockItem {
    public ItemCorpseFlowerSeeds(Block block, Properties properties) {
        super(block, properties);
    }

    protected boolean canPlace(BlockPlaceContext context, BlockState state) {
        Player player = context.getPlayer();
        CollisionContext collisioncontext = player == null ? CollisionContext.empty() : CollisionContext.of(player);
        return (!this.mustSurvive() || state.canSurvive(context.getLevel(), context.getClickedPos()))
                && context.getLevel().isUnobstructed(state, context.getClickedPos(), collisioncontext)
                && (context.getLevel().getBlockState(context.getClickedPos().below()).getBlock() == Blocks.PODZOL);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Block block = context.getLevel().getBlockState(pos).getBlock();
        if (block == Blocks.FLOWER_POT) {
            context.getLevel().setBlock(pos, BroglisPlantsBlocks.POTTED_CORPSE_FLOWER_BLOCK.get().defaultBlockState(), 2);
            if (!context.getPlayer().isCreative()) {
                stack.shrink(1);
            }
        }
        return super.onItemUseFirst(stack, context);
    }
}

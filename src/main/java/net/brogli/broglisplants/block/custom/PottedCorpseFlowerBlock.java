package net.brogli.broglisplants.block.custom;

import net.brogli.broglisplants.block.BroglisPlantsBlockEntities;
import net.brogli.broglisplants.item.BroglisPlantsItems;
import net.brogli.broglisplants.particle.BroglisPlantsParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class PottedCorpseFlowerBlock extends CorpseFlowerBlock {
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    public PottedCorpseFlowerBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        return true;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.isEmpty()) {
            level.setBlock(pos, Blocks.FLOWER_POT.defaultBlockState(), 2);
            player.setItemInHand(hand, BroglisPlantsItems.ITEM_CORPSE_FLOWER_SEEDS.get().getDefaultInstance());
            return InteractionResult.SUCCESS;
        }
        if (itemStack.is(Items.BONE_MEAL)) {
            level.setBlock(pos, Blocks.FLOWER_POT.defaultBlockState(), 2);
            player.addItem(BroglisPlantsItems.ITEM_CORPSE_FLOWER_SEEDS.get().getDefaultInstance());
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, hitResult);
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }


    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean bool) {
        return false;
    }

    public boolean isBonemealSuccess(Level level, RandomSource source, BlockPos pos, BlockState state) {
        return false;
    }

    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BroglisPlantsBlockEntities.POTTED_ENTITY_CORPSE_FLOWER.get().create(pos, state);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        float chance = 0.75F;
        if (chance < rand.nextFloat()) {
            double d1 = level.random.nextDouble() * (0.2 - 0.1) + 0.1;
            level.addParticle(BroglisPlantsParticles.CORPSE_FLOWER_PARTICLES.get(),(pos.getX() + 0.35) + d1,pos.getY() + 0.5D,(pos.getZ() + 0.35) + d1, 0.0D, 0.02D, 0.0D);
        }
    }
}

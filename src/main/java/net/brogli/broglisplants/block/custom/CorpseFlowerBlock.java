package net.brogli.broglisplants.block.custom;

import net.brogli.broglisplants.block.BroglisPlantsBlockEntities;
import net.brogli.broglisplants.block.entity.EntityCorpseFlower;
import net.brogli.broglisplants.block.entity.EntityFlytrap;
import net.brogli.broglisplants.item.BroglisPlantsItems;
import net.brogli.broglisplants.particle.BroglisPlantsParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CorpseFlowerBlock extends BaseEntityBlock implements BonemealableBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;

    protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 15.0D);


    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public CorpseFlowerBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), Integer.valueOf(0)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BroglisPlantsBlockEntities.ENTITY_CORPSE_FLOWER.get().create(pos, state);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        float chance = 0.65F;
        if (state.getValue(AGE) > 0 && chance < rand.nextFloat()) {
            double d1 = level.random.nextDouble() * (0.2 - 0.1) + 0.1;
            level.addParticle(BroglisPlantsParticles.CORPSE_FLOWER_PARTICLES.get(),(pos.getX() + 0.35) + d1,pos.getY() + 0.2D,(pos.getZ() + 0.35) + d1, 0.0D, 0.02D, 0.0D);
        }
    }

    public boolean isPossibleToRespawnInThis() {
        return true;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return (canSupportRigidBlock(levelReader, blockpos) || canSupportCenter(levelReader, blockpos, Direction.UP)) && levelReader.getBlockState(blockpos).getBlock() == Blocks.PODZOL;
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Player) {
            ((Player) entity).addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 4, true, false), entity);
        }
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 2;
    }

    protected int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    public BlockState getStateForAge(int i) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), Integer.valueOf(i));
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) != 2;
//        return !this.isMaxAge(state);
    }

    protected int getBonemealAgeIncrease() {
        return 1;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (level.getRawBrightness(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < 1) {
                float f = getGrowthSpeed(level, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0)) {
                    level.setBlock(pos, this.getStateForAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
                }
            }
        }
        float chance = 0.9F;
        EntityCorpseFlower corpseFlower = (EntityCorpseFlower) level.getBlockEntity(pos);
        assert corpseFlower != null;
        if (state.getValue(AGE) == 1 && corpseFlower.seedsDropped < 2 && chance < rand.nextFloat()) {
            Direction direction = Direction.UP;
            double d0 = (pos.getX() + 0.35) + 0.7D * (double)direction.getStepX();
            double d1 = (pos.getY() + 0.35) + 0.7D * (double)direction.getStepY();
            double d2 = (pos.getZ() + 0.35) + 0.7D * (double)direction.getStepZ();
            ItemEntity item = new ItemEntity(level, d0, d1, d2, new ItemStack(BroglisPlantsItems.ITEM_CORPSE_FLOWER_SEEDS.get()));
            level.addFreshEntity(item);
            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.7f, 1.0f);
            corpseFlower.dropSeed();
            if (state.getValue(AGE) == 1 && corpseFlower.seedsDropped == 2) {
                level.setBlock(pos, this.getStateForAge(2), 2);

            }
        }
    }

    public void growCrops(Level level, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease();
        int j = this.getMaxAge() ;
        if (i > j) {
            i = j;
        }
        level.setBlock(pos, this.getStateForAge(i), 2);
    }

    protected static float getGrowthSpeed(BlockGetter getter, BlockPos pos) {
        return 0.5F;
    }

    public boolean isMaxAge(BlockState state) {
        return state.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean bool) {
        return !this.isMaxAge(state);
    }

    public boolean isBonemealSuccess(Level level, RandomSource source, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        this.growCrops(level, pos, state);
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        return direction == Direction.DOWN && !state.canSurvive(levelAccessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(AGE);
    }
}

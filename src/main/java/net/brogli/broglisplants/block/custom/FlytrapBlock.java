package net.brogli.broglisplants.block.custom;

import net.brogli.broglisplants.block.BroglisPlantsBlockEntities;
import net.brogli.broglisplants.block.entity.EntityFlytrap;
import net.brogli.broglisplants.item.BroglisPlantsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;

public class FlytrapBlock extends BaseEntityBlock implements BonemealableBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D);

    protected static final AABB TOUCH_AABB = new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

    private final FlytrapBlock.Sensitivity sensitivity;

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;


    public FlytrapBlock(FlytrapBlock.Sensitivity sensitivity, BlockBehaviour.Properties properties) {
        super(properties);
        this.sensitivity = sensitivity;
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), Integer.valueOf(0)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
        return direction == Direction.DOWN && !state.canSurvive(levelAccessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, state1, levelAccessor, pos, pos1);
    }

    protected int getPressedTime() {
        return 20;
    }

    public boolean isPossibleToRespawnInThis() {
        return true;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return (canSupportRigidBlock(levelReader, blockpos) || canSupportCenter(levelReader, blockpos, Direction.UP)) && levelReader.getBlockState(blockpos).getBlock() instanceof MudBlock;
    }

    public void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource source) {
        int i = this.getSignalStrength(serverLevel, pos);
        if (i > 0) {
            this.checkPressed((Entity)null, serverLevel, pos, i);
        }
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if ((entity instanceof ItemEntity && ((ItemEntity) entity).getItem().getItem() == BroglisPlantsItems.ITEM_FLYTRAP_SEEDS.get()) || entity instanceof Frog) {
            entity.hurt(DamageSource.CACTUS, 0.0F);
        } else {
            entity.hurt(DamageSource.CACTUS, 0.5F);
//            entity.makeStuckInBlock(state, new Vec3(0.9D, (double)0.9F, 0.9D));
        }
        if (!level.isClientSide) {
            int i = this.getSignalStrength(level, pos);
            if (i == 0) {
                this.checkPressed(entity, level, pos, i);
            }
        }
    }

    protected void checkPressed(@Nullable Entity entity, Level level, BlockPos pos, int j) {
        int i = this.getSignalStrength(level, pos);
        boolean flag = j > 0;
        boolean flag1 = i > 0;
        if (!flag1 && flag) {
            level.gameEvent(entity, GameEvent.BLOCK_DEACTIVATE, pos);
        } else if (flag1 && !flag) {
            level.gameEvent(entity, GameEvent.BLOCK_ACTIVATE, pos);
        }
        if (flag1) {
            level.scheduleTick(new BlockPos(pos), this, this.getPressedTime());
        }
    }

    public int getSignalStrength(Level level, BlockPos pos) {
        AABB aabb = TOUCH_AABB.move(pos);
        List<? extends Entity> list;
        switch (this.sensitivity) {
            case EVERYTHING:
                list = level.getEntities((Entity)null, aabb);
                break;
            case MOBS:
                list = level.getEntitiesOfClass(LivingEntity.class, aabb);
                break;
            default:
                return 0;
        }
        if (!list.isEmpty()) {
            for(Entity entity : list) {
                if (!entity.isIgnoringBlockTriggers()) {
                    level.playSound(null, pos, SoundEvents.FROG_EAT, SoundSource.BLOCKS, 0.4f, 0.75f);
                    if (level.random.nextFloat() > 0.75F) {
                        level.addParticle(ParticleTypes.SPORE_BLOSSOM_AIR, pos.getX() + (level.random.nextFloat()), pos.getY() + 0.3D, pos.getZ() + (level.random.nextFloat()), 0.0D, 0.0D, 0.0D);
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 3;
    }

    protected int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    public BlockState getStateForAge(int i) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), Integer.valueOf(i));
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) != 3;
    }

    protected int getBonemealAgeIncrease() {
        return 1;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (level.getRawBrightness(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < 2) {
                float f = getGrowthSpeed();
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0)) {
                    level.setBlock(pos, this.getStateForAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
                }
            }
        }
        float chance = 0.9F;
        EntityFlytrap flytrap = (EntityFlytrap) level.getBlockEntity(pos);
        assert flytrap != null;
        if (state.getValue(AGE) == 2 && flytrap.seedsDropped < 3 && chance < rand.nextFloat()) {
            Direction direction = Direction.UP;
            double d0 = (pos.getX() + 0.35) + 0.7D * (double)direction.getStepX();
            double d1 = (pos.getY() + 0.35) + 0.7D * (double)direction.getStepY();
            double d2 = (pos.getZ() + 0.35) + 0.7D * (double)direction.getStepZ();
            ItemEntity item = new ItemEntity(level, d0, d1, d2, new ItemStack(BroglisPlantsItems.ITEM_FLYTRAP_SEEDS.get()));
            level.addFreshEntity(item);
            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.7f, 1.0f);
            flytrap.dropSeed();
        }
        if (state.getValue(AGE) == 2 && flytrap.seedsDropped == 3) {
            level.setBlock(pos, this.getStateForAge(3), 2);
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

    protected static float getGrowthSpeed() {
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

    public enum Sensitivity {
        EVERYTHING,
        MOBS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BroglisPlantsBlockEntities.ENTITY_FLYTRAP.get().create(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(AGE);
    }
}


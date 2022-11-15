package net.brogli.broglisplants.block.custom;

import net.brogli.broglisplants.particle.BroglisPlantsParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.MyceliumBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DeadlyNightshadeBlock extends FlowerBlock {

    public DeadlyNightshadeBlock(MobEffect effect, int i, Properties properties) {
        super(effect, i, properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        float chance = 0.75F;
        if (chance < rand.nextFloat()) {
            double d1 = level.random.nextDouble() * (0.5 - 0.0) + 0.0;
            level.addParticle(BroglisPlantsParticles.DEADLY_NIGHTSHADE_PARTICLES.get(),(pos.getX() + 0.25) + d1,pos.getY() + 0.9D,(pos.getZ() + 0.25) + d1, 0.0D, 0.02D, 0.0D);
        }
    }

    @Override
    public boolean canSurvive(BlockState p_51028_, LevelReader p_51029_, BlockPos p_51030_) {
        BlockPos blockpos = p_51030_.below();
        if (p_51028_.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return p_51029_.getBlockState(blockpos).canSustainPlant(p_51029_, blockpos, Direction.UP, this);
        return this.mayPlaceOn(p_51029_.getBlockState(blockpos), p_51029_, blockpos);
    }

    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return p_51042_.is(BlockTags.DIRT);
    }
}

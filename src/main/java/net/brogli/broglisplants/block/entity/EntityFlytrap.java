package net.brogli.broglisplants.block.entity;

import net.brogli.broglisplants.block.BroglisPlantsBlockEntities;
import net.brogli.broglisplants.block.custom.FlytrapBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityFlytrap extends BlockEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public int seedsDropped = 0;

    public EntityFlytrap(BlockPos pos, BlockState state) {
        super(BroglisPlantsBlockEntities.ENTITY_FLYTRAP.get(), pos, state);
    }

    public void dropSeed() {
        this.seedsDropped++;
    }

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        FlytrapBlock flytrapBlock = (FlytrapBlock) this.getBlockState().getBlock();
        assert level != null;
        if (level.isClientSide) {
            if (flytrapBlock.getSignalStrength(level, getBlockPos()) == 1) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.flytrap.snapping", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.flytrap.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}

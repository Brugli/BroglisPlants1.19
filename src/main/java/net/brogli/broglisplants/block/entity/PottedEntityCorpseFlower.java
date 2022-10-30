package net.brogli.broglisplants.block.entity;

import net.brogli.broglisplants.block.BroglisPlantsBlockEntities;
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

public class PottedEntityCorpseFlower extends BlockEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public PottedEntityCorpseFlower(BlockPos pos, BlockState state) {
        super(BroglisPlantsBlockEntities.POTTED_ENTITY_CORPSE_FLOWER.get(), pos, state);
    }

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        assert level != null;
        if (level.isClientSide) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corpse_flower.idle", true));
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

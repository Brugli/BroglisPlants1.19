package net.brogli.broglisplants.block.client;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.custom.CorpseFlowerBlock;
import net.brogli.broglisplants.block.custom.FlytrapBlock;
import net.brogli.broglisplants.block.entity.EntityCorpseFlower;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CorpseFlowerModel extends AnimatedGeoModel<EntityCorpseFlower> {

    public static final ResourceLocation AGE_0 = new ResourceLocation(BroglisPlants.MOD_ID, "geo/entity_corpse_flower_bud.geo.json");

    public static final ResourceLocation AGE_1 = new ResourceLocation(BroglisPlants.MOD_ID, "geo/entity_corpse_flower.geo.json");

    public static final ResourceLocation BUD_ANIM = new ResourceLocation(BroglisPlants.MOD_ID, "animations/entity_corpse_flower_bud.animation.json");

    public static final ResourceLocation FLOWER_ANIM = new ResourceLocation(BroglisPlants.MOD_ID, "animations/entity_corpse_flower.animation.json");

    @Override
    public ResourceLocation getModelResource(EntityCorpseFlower object) {

        if (object.getBlockState().getValue(CorpseFlowerBlock.AGE) == 0) {
            return AGE_0;
        }
        else return AGE_1;
    }

    @Override
    public ResourceLocation getTextureResource(EntityCorpseFlower object) {
        return new ResourceLocation(BroglisPlants.MOD_ID, "textures/block/entity_corpse_flower.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityCorpseFlower animatable) {

        if (animatable.getBlockState().getValue(CorpseFlowerBlock.AGE) == 0) {
            return BUD_ANIM;
        }
        else return FLOWER_ANIM;
    }
}

package net.brogli.broglisplants.block.client;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.custom.FlytrapBlock;
import net.brogli.broglisplants.block.entity.EntityFlytrap;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FlytrapModel extends AnimatedGeoModel<EntityFlytrap> {
    public static final ResourceLocation AGE_0 = new ResourceLocation(BroglisPlants.MOD_ID, "geo/entity_flytrap_small.geo.json");
    public static final ResourceLocation AGE_1 = new ResourceLocation(BroglisPlants.MOD_ID, "geo/entity_flytrap.geo.json");
    public static final ResourceLocation AGE_2 = new ResourceLocation(BroglisPlants.MOD_ID, "geo/entity_flytrap_flowering.geo.json");
    public static final ResourceLocation SMALL_ANIM = new ResourceLocation(BroglisPlants.MOD_ID, "animations/entity_flytrap_small.animation.json");
    public static final ResourceLocation ANIM = new ResourceLocation(BroglisPlants.MOD_ID, "animations/entity_flytrap.animation.json");
    public static final ResourceLocation FLOWERING_ANIM = new ResourceLocation(BroglisPlants.MOD_ID, "animations/entity_flytrap_flowering.animation.json");

    @Override
    public ResourceLocation getModelResource(EntityFlytrap object) {

        if (object.getBlockState().getValue(FlytrapBlock.AGE) == 0) {
            return AGE_0;
        }
        else if (object.getBlockState().getValue(FlytrapBlock.AGE) == 1) {
            return AGE_1;
        }
        else if (object.getBlockState().getValue(FlytrapBlock.AGE) == 2) {
            return AGE_2;
        }
        else return AGE_1;
    }

    @Override
    public ResourceLocation getTextureResource(EntityFlytrap object) {
        return new ResourceLocation(BroglisPlants.MOD_ID, "textures/block/entity_flytrap.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityFlytrap animatable) {

        if (animatable.getBlockState().getValue(FlytrapBlock.AGE) == 0) {
            return SMALL_ANIM;
        }
        else if (animatable.getBlockState().getValue(FlytrapBlock.AGE) == 1) {
            return ANIM;
        }
        else if (animatable.getBlockState().getValue(FlytrapBlock.AGE) == 2) {
            return FLOWERING_ANIM;
        }else
            return ANIM;
    }
}

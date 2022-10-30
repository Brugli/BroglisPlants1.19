package net.brogli.broglisplants.block.client;

import net.brogli.broglisplants.BroglisPlants;
import net.brogli.broglisplants.block.entity.PottedEntityFlytrap;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PottedFlytrapModel extends AnimatedGeoModel<PottedEntityFlytrap> {
    @Override
    public ResourceLocation getModelResource(PottedEntityFlytrap object) {
        return new ResourceLocation(BroglisPlants.MOD_ID, "geo/entity_flytrap_potted.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PottedEntityFlytrap object) {
        return new ResourceLocation(BroglisPlants.MOD_ID, "textures/block/entity_flytrap.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PottedEntityFlytrap animatable) {
        return new ResourceLocation(BroglisPlants.MOD_ID, "animations/entity_flytrap_small.animation.json");
    }
}

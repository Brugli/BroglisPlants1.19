package net.brogli.broglisplants.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CorpseFlowerParticles extends TextureSheetParticle {
    private final SpriteSet sprites;

    CorpseFlowerParticles(ClientLevel level, double a, double b, double c, double d, double e, double f, SpriteSet spriteSet) {
        super(level, a, b, c, d, e, f);

        this.friction = 0.96F;
        this.sprites = spriteSet;
        this.scale(1.0F);
        this.hasPhysics = false;
        this.setSpriteFromAge(spriteSet);

    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    @OnlyIn(Dist.CLIENT)
    public static record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double a, double b, double c, double d, double e, double f) {
            CorpseFlowerParticles corpseFlowerParticles = new CorpseFlowerParticles(level, a, b, c, d, e, f, this.sprite);
            corpseFlowerParticles.setAlpha(1.0F);
            corpseFlowerParticles.setParticleSpeed(d, e, f);
            corpseFlowerParticles.setLifetime(level.random.nextInt(34) + 6);
            return corpseFlowerParticles;
        }
    }
}

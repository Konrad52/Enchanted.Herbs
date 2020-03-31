package mod.encherbs.content.particles;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@SuppressWarnings("NullableProblems")
public class PlantGrowParticle extends SpriteTexturedParticle {
    protected final IAnimatedSprite animatedSprite;

    public PlantGrowParticle(IAnimatedSprite animatedSprite, World worldIn, double x, double y, double z, float r, float g, float b) {
        super(worldIn, x, y, z);
        this.particleScale = ((float)(Math.random() * 0.3f) + 0.3f) * 1.5f;
        this.particleRed = r / 255f;
        this.particleGreen = g / 255f;
        this.particleBlue = b / 255f;
        this.maxAge = Math.max((int)(6.0D / (Math.random() * 0.8D + 0.6D)), 1);
        this.canCollide = false;
        this.animatedSprite = animatedSprite;
        this.selectSpriteWithAge(this.animatedSprite);
    }

    @Override
    public void tick() {
        super.tick();
        this.prevParticleAngle = this.particleAngle;

        if(this.isAlive()) {
            this.selectSpriteWithAge(this.animatedSprite);
        }
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {

        private IAnimatedSprite animatedSprite;

        public Factory(IAnimatedSprite animatedSprite) {
            this.animatedSprite = animatedSprite;
        }

        @Nullable
        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new PlantGrowParticle(this.animatedSprite, worldIn, x, y, z, (float)xSpeed, (float)ySpeed, (float)zSpeed);
        }

    }

}

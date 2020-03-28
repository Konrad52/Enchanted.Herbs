package mod.encherbs.classes;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings("ALL")
public class CustomParticleFactory implements IParticleFactory<BasicParticleType> {

    @Nullable
    @Override
    public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        return new SpriteTexturedParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed) {
            @Override
            public IParticleRenderType getRenderType() {
                return IParticleRenderType.PARTICLE_SHEET_LIT;
            }
        };
    }

}

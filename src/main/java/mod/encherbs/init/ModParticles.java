package mod.encherbs.init;

import mod.encherbs.Main;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Main.MODID);

    public static final RegistryObject<BasicParticleType> MAGICAL_FERTILIZER_PARTICLE = PARTICLES.register("magical_fertilizer_particle", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> PLANT_GROW_PARTICLE         = PARTICLES.register("plant_grow_particle",         () -> new BasicParticleType(false));

}
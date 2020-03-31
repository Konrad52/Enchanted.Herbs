package mod.encherbs;

import mod.encherbs.content.blocks.BlockPlant;
import mod.encherbs.content.particles.MagicalFertilizerParticle;
import mod.encherbs.content.particles.PlantGrowParticle;
import mod.encherbs.content.util.Util;
import mod.encherbs.init.ModBlocks;
import mod.encherbs.init.ModItems;
import mod.encherbs.init.ModParticles;
import mod.encherbs.init.ModTiles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID  = "encherbs";
    public static final Logger LOGGER = LogManager.getLogger(Main.MODID);

    @SuppressWarnings("unused")
    public Main() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onParticleFactoryRegistryEvent);

        MinecraftForge.EVENT_BUS.register(this);

        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTiles.TILE_ENTITY_TYPES.register(modEventBus);
        ModParticles.PARTICLES.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Main pre-initialization.");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("Client initialization.");

        ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .forEach(block -> {
                    if (ModEvents.isItemSeed(block)) {
                        RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
                        Minecraft.getInstance().getBlockColors().register((state, light, pos, tintIndex) -> {
                            BlockPlant blockPlant = (BlockPlant) state.getBlock();
                            int[] plantColor = blockPlant.getPlantColor();
                            int[] cropColor = blockPlant.getCropColor();

                            if (tintIndex == 0)
                                return Util.getIntFromColor(plantColor[0], plantColor[1], plantColor[2]);
                            else
                                return Util.getIntFromColor(cropColor[0], cropColor[1], cropColor[2]);
                        }, block);
                    }
                });

        ModEvents.getSeeds()
                .forEach(item -> Minecraft.getInstance().getItemColors().register((itemStack, tintColor) -> {
                    int[] cropColor = ((BlockPlant) item.getBlock()).getCropColor();
                    return Util.getIntFromColor(cropColor[0], cropColor[1], cropColor[2]);
                }, item));
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("Server initialization.");
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onParticleFactoryRegistryEvent(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ModParticles.MAGICAL_FERTILIZER_PARTICLE.get(), MagicalFertilizerParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(ModParticles.PLANT_GROW_PARTICLE.get(),         PlantGrowParticle.Factory::new        );
    }

}

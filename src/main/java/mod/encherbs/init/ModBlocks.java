package mod.encherbs.init;

import mod.encherbs.Main;
import mod.encherbs.classes.BlockPlant;
import mod.encherbs.classes.unique.BlockMagicalDirt;
import mod.encherbs.classes.unique.BlockMagicalFarmland;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Main.MODID);

    public static final RegistryObject<BlockMagicalDirt> MAGICAL_DIRT = BLOCKS.register("magical_dirt", BlockMagicalDirt::new);
    public static final RegistryObject<BlockMagicalFarmland> MAGICAL_FARMLAND = BLOCKS.register("magical_farmland", BlockMagicalFarmland::new);

    public static final RegistryObject<BlockPlant> GOLD_PLANT = BLOCKS.register("gold_plant", () -> new BlockPlant( Items.GOLD_INGOT, new int[]{128, 255, 0}, new int[]{255, 217, 0}));
    public static final RegistryObject<BlockPlant> QUARTZ_PLANT = BLOCKS.register("quartz_plant", () -> new BlockPlant(Items.QUARTZ, new int[]{128, 32, 32}, new int[]{255, 255, 255}));
    public static final RegistryObject<BlockPlant> PEARL_PLANT = BLOCKS.register("pearl_plant", () -> new BlockPlant(Items.ENDER_PEARL, new int[]{255, 230, 170}, new int[]{19, 94, 87}));
    public static final RegistryObject<BlockPlant> OBSIDIAN_PLANT = BLOCKS.register("obsidian_plant", () -> new BlockPlant(Items.OBSIDIAN, new int[]{128, 255, 0}, new int[]{54, 54, 54}));
    public static final RegistryObject<BlockPlant> DIAMOND_PLANT = BLOCKS.register("diamond_plant", () -> new BlockPlant(Items.DIAMOND, new int[]{128, 255, 0}, new int[]{0, 255, 255}));
}

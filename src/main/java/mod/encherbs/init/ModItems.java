package mod.encherbs.init;

import mod.encherbs.Main;
import mod.encherbs.classes.unique.ItemMortar;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<ItemMortar> MORTAR = ITEMS.register("mortar", ItemMortar::new);
    public static final RegistryObject<Item> NETHERRACK_DUST = ITEMS.register("netherrack_dust", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
}

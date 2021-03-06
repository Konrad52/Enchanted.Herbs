package mod.encherbs.init;

import mod.encherbs.Main;
import mod.encherbs.content.items.ItemGuideBook;
import mod.encherbs.content.items.ItemMortar;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<ItemGuideBook> GUIDE_BOOK = ITEMS.register("guide_book", ItemGuideBook::new);
    public static final RegistryObject<ItemMortar> MORTAR = ITEMS.register("mortar", ItemMortar::new);
    public static final RegistryObject<Item> NETHERRACK_DUST = ITEMS.register("netherrack_dust", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_MAIN_ITEM_GROUP)));
    public static final RegistryObject<Item> MAGICAL_FERTILIZER = ITEMS.register("magical_fertilizer", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_MAIN_ITEM_GROUP)));
}

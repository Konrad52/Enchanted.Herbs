package mod.encherbs.init;

import mod.encherbs.Main;
import mod.encherbs.ModEvents;
import mod.encherbs.content.util.ModItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
    public static final ItemGroup MOD_MAIN_ITEM_GROUP  = new ModItemGroup(Main.MODID + ".main", () -> new ItemStack(ModItems.MAGICAL_FERTILIZER.get()));
    public static final ItemGroup MOD_SEEDS_ITEM_GROUP = new ModItemGroup(Main.MODID + ".seeds", () -> new ItemStack(ModEvents.getSeeds().get(0)));
}

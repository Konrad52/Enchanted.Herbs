package mod.encherbs.init;

import mod.encherbs.Main;
import mod.encherbs.classes.ModItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModItemGroups {

    public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(Main.MODID, () -> new ItemStack(Items.GREEN_BANNER));

}

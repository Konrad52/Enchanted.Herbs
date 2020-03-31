package mod.encherbs.content.items;

import mod.encherbs.init.ModItemGroups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemGuideBook extends Item {

    public ItemGuideBook() {
        super(new Item.Properties().maxStackSize(1).group(ModItemGroups.MOD_MAIN_ITEM_GROUP));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerEntity, Hand handIn) {
//        if (worldIn.isRemote)
//            Minecraft.getInstance().displayGuiScreen(new CraftingScreen());
        return ActionResult.resultSuccess(getDefaultInstance());
    }

}

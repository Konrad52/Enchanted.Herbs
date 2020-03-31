package mod.encherbs.content.items;

import mod.encherbs.init.ModItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMortar extends Item {

    private static final int max_damage = 228;

    public ItemMortar() {
        super(new Item.Properties().group(ModItemGroups.MOD_MAIN_ITEM_GROUP).defaultMaxDamage(max_damage).maxDamage(max_damage));
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return max_damage;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (double) stack.getDamage() / (double) stack.getMaxDamage();
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1)
            return ItemStack.EMPTY;
        else {
            ItemStack container = itemStack.copy();
            container.setDamage(container.getDamage() + 1);
            return container;
        }
    }

}
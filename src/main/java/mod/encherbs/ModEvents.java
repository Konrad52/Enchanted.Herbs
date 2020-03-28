package mod.encherbs;

import mod.encherbs.classes.BlockItemSeed;
import mod.encherbs.init.ModBlocks;
import mod.encherbs.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Main.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        final IForgeRegistry<Item> registry = itemRegistryEvent.getRegistry();

        ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .forEach(block -> {
                    if (needsItemBlock(block)) {
                        final Item.Properties properties = new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP);
                        final BlockItem blockItem;

                        if (isItemSeed(block))
                            blockItem = new BlockItemSeed(block, properties);
                        else
                            blockItem = new BlockItem(block, properties);

                        blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                        registry.register(blockItem);
                    }
                });
    }

    public static boolean needsItemBlock(Block block) {
        //if (ModBlocks.MAGICAL_FARMLAND.get().equals(block))
        //    return false;
        //else
        return true;
    }

    public static boolean isItemSeed(Block block) {
        return !ModBlocks.MAGICAL_FARMLAND.get() .equals(block)
            && !ModBlocks.MAGICAL_DIRT.get()     .equals(block);
    }

}
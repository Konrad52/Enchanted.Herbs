package mod.encherbs;

import mod.encherbs.init.ModBlocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Main.MODID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent itemTooltipEvent) {
        if (Objects.requireNonNull(itemTooltipEvent.getItemStack().getItem().getRegistryName()).equals(ModBlocks.MAGICAL_DIRT.get().getRegistryName())) {
            String formatting = TextFormatting.DARK_GRAY.toString() + TextFormatting.ITALIC.toString();
            List<ITextComponent> toolTip = itemTooltipEvent.getToolTip();
            toolTip.add(new StringTextComponent(formatting + I18n.format("block.encherbs.magical_dirt.desc1")));
            toolTip.add(new StringTextComponent(formatting + I18n.format("block.encherbs.magical_dirt.desc2")));
            toolTip.add(new StringTextComponent(""));
            toolTip.add(new StringTextComponent(formatting + I18n.format("block.encherbs.magical_dirt.desc3")));
            toolTip.add(new StringTextComponent(formatting + I18n.format("block.encherbs.magical_dirt.desc4")));
        }
    }

}

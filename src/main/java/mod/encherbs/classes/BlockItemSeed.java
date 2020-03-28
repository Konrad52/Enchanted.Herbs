package mod.encherbs.classes;

import mod.encherbs.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockItemSeed extends BlockItem {

    public BlockItemSeed(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getPos();
        BlockPos placePos = blockPos.up();

        if (world.getBlockState(blockPos).getBlock().equals(ModBlocks.MAGICAL_FARMLAND.get()) && world.isAirBlock(placePos)) {
            world.setBlockState(placePos, getBlock().getDefaultState());
            world.playSound(context.getPlayer(), placePos, getPlaceSound(world.getBlockState(placePos)), SoundCategory.BLOCKS, 1.0f, world.rand.nextFloat() * 0.1F + 0.9F);
            context.getItem().shrink(1);
            return ActionResultType.SUCCESS;
        } else
            return ActionResultType.FAIL;
    }

}

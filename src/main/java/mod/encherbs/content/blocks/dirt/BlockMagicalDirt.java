package mod.encherbs.content.blocks.dirt;

import mod.encherbs.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.Objects;

public class BlockMagicalDirt extends Block {

    public BlockMagicalDirt() {
        super(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5f, 0.5f).harvestTool(ToolType.SHOVEL).sound(SoundType.GROUND));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult rayTraceResult) {
        ItemStack heldItem = player.getHeldItem(handIn);
        if (Objects.requireNonNull(heldItem.getItem().getRegistryName()).toString().contains("hoe")) {
            worldIn.setBlockState(pos, ModBlocks.MAGICAL_FARMLAND.get().getDefaultState());
            worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            heldItem.damageItem(1, player, (playerEntity) -> {
                playerEntity.sendBreakAnimation(handIn);
            });
            return ActionResultType.SUCCESS;
        } else
            return ActionResultType.FAIL;
    }
}

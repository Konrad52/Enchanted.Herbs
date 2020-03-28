package mod.encherbs.classes.unique;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class BlockMagicalFarmland extends Block {

    public BlockMagicalFarmland() {
        super(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5f, 0.5f).harvestTool(ToolType.SHOVEL).sound(SoundType.GROUND));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0, 0, 0, 16, 15, 16);
    }

}

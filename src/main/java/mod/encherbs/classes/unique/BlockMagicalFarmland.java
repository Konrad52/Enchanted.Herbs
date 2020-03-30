package mod.encherbs.classes.unique;

import mod.encherbs.classes.BlockPlant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

@SuppressWarnings({"NullableProblems", "deprecation", "ConstantConditions"})
public class BlockMagicalFarmland extends Block {

    public BlockMagicalFarmland() {
        super(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5f, 0.5f).harvestTool(ToolType.SHOVEL).sound(SoundType.GROUND));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0, 0, 0, 16, 15, 16);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote && worldIn.getBlockState(pos.up()).getBlock() instanceof BlockPlant) {
            worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() + 1.5f, pos.getZ() + 0.5f, new ItemStack(worldIn.getBlockState(pos.up()).getBlock(), 1)));
            worldIn.setBlockState(pos.up(), new BlockState(Blocks.AIR, null));
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public void onBlockExploded(BlockState state, World worldIn, BlockPos pos, Explosion explosion) {
        if (!worldIn.isRemote && worldIn.getBlockState(pos.up()).getBlock() instanceof BlockPlant) {
            worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() + 1.5f, pos.getZ() + 0.5f, new ItemStack(worldIn.getBlockState(pos.up()).getBlock(), 1)));
            worldIn.setBlockState(pos.up(), new BlockState(Blocks.AIR, null));
        }
        super.onBlockExploded(state, worldIn, pos, explosion);
    }

    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }
}

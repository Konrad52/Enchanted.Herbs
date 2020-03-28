package mod.encherbs.classes;

import mod.encherbs.classes.util.GrowthStages;
import mod.encherbs.init.ModItems;
import mod.encherbs.init.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

@SuppressWarnings("ALL")
public class BlockPlant extends Block {

    public static final EnumProperty<GrowthStages> GROWTH_PROPERTY = EnumProperty.create("stage" , GrowthStages.class);

    private int[] plantColor;
    private int[] cropColor;
    private RegistryObject<Item> drop       = null;
    private Item                 dropItem   = null;

    private BlockPlant(int[] plantColor, int[] cropColor) {
        super(Block.Properties.create(Material.PLANTS)
                              .hardnessAndResistance(0.01F, 0.01F)
                              .sound(SoundType.CROP)
        );
        setDefaultState(getDefaultState().with(GROWTH_PROPERTY, GrowthStages.stage0));
        if (plantColor.length != 3 || cropColor.length != 3)
            throw new IllegalArgumentException("A color array's size must be 3. (R/G/B)");
        this.plantColor = plantColor;
        this.cropColor  = cropColor;
    }

    public BlockPlant(RegistryObject<Item> drop, int[] plantColor, int[] cropColor) {
        this(plantColor, cropColor);
        this.drop = drop;
    }

    public BlockPlant(Item dropItem, int[] plantColor, int[] cropColor) {
        this(plantColor, cropColor);
        this.dropItem = dropItem;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(GROWTH_PROPERTY).equals(GrowthStages.produce2))
            if (drop == null)
                worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, dropItem.getDefaultInstance()));
            else
                worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, drop.get().getDefaultInstance()));

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
        if (!state.get(GROWTH_PROPERTY).equals(GrowthStages.produce2) && player.getHeldItem(handIn).getItem().equals(ModItems.MAGICAL_FERTILIZER.get())) {
            if (!player.isCreative())
                player.getHeldItem(handIn).shrink(1);

            if (worldIn.isRemote)
                for (int i = 0; i < 20; i++) {
                    worldIn.addParticle(ModParticles.MAGICAL_FERTILIZER_PARTICLE.get(),
                            pos.getX() + (worldIn.rand.nextDouble() * 0.70f) + 0.15f,
                            pos.getY() + (worldIn.rand.nextDouble() * 0.70f) + 0.05f,
                            pos.getZ() + (worldIn.rand.nextDouble() * 0.70f) + 0.15f,
                            0,
                            0,
                            0);
                }

            worldIn.playSound(player, pos, SoundType.PLANT.getPlaceSound(), SoundCategory.BLOCKS, 0.6f, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            worldIn.setBlockState(pos, state.with(GROWTH_PROPERTY, state.get(GROWTH_PROPERTY).grow()));
            return ActionResultType.SUCCESS;
        }

        if (state.get(GROWTH_PROPERTY).equals(GrowthStages.produce2)) {
            if (drop == null)
                worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, dropItem.getDefaultInstance()));
            else
                worldIn.addEntity(new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, drop.get().getDefaultInstance()));

            worldIn.playSound(player, pos, SoundType.CROP.getPlaceSound(), SoundCategory.BLOCKS, 1.0f, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            worldIn.setBlockState(pos, state.with(GROWTH_PROPERTY, GrowthStages.stage3));
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.FAIL;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(GROWTH_PROPERTY);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(2, 0, 2, 14, 12, 14);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }

    public int[] getPlantColor() {
        return plantColor;
    }

    public int[] getCropColor() {
        return cropColor;
    }

}

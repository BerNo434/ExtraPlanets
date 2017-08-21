package com.mjr.extraplanets.blocks.machines;

import java.util.List;

import micdoodle8.mods.galacticraft.core.blocks.BlockAdvancedTile;
import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import micdoodle8.mods.galacticraft.core.items.IShiftDescription;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.mjr.extraplanets.ExtraPlanets;
import com.mjr.extraplanets.tile.machines.TileEntityAdvancedOxygenCompressor;
import com.mjr.extraplanets.tile.machines.TileEntityAdvancedOxygenDecompressor;

public class AdvancedOxygenCompressor extends BlockAdvancedTile implements IShiftDescription, ISortableBlock {
	public static final int OXYGEN_COMPRESSOR_METADATA = 0;
	public static final int OXYGEN_DECOMPRESSOR_METADATA = 4;

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumCompressorType.class);

	public enum EnumCompressorType implements IStringSerializable {
		COMPRESSOR(0, "compressor"), DECOMPRESSOR(1, "decompressor");

		private final int meta;
		private final String name;

		EnumCompressorType(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		public int getMeta() {
			return this.meta;
		}

		public static EnumCompressorType byMetadata(int meta) {
			return values()[meta];
		}

		@Override
		public String getName() {
			return this.name;
		}
	}

	public AdvancedOxygenCompressor(boolean isActive, String assetName) {
		super(Material.rock);
		this.setHardness(1.0F);
		this.setUnlocalizedName(assetName);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return ExtraPlanets.BlocksTab;
	}

	@Override
	public boolean onUseWrench(World world, BlockPos pos, EntityPlayer entityPlayer, EnumFacing side, float hitX, float hitY, float hitZ) {
		int metadata = getMetaFromState(world.getBlockState(pos));
		int change = world.getBlockState(pos).getValue(FACING).rotateY().getHorizontalIndex();

		world.setBlockState(pos, this.getStateFromMeta(metadata - (metadata % 4) + change), 3);

		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileBaseUniversalElectrical) {
			((TileBaseUniversalElectrical) te).updateFacing();
		}

		return true;
	}

	@Override
	public boolean onMachineActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityPlayer, EnumFacing side, float hitX, float hitY, float hitZ) {
		entityPlayer.openGui(ExtraPlanets.instance, -1, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		int metadata = getMetaFromState(state);
		if (metadata >= AdvancedOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA) {
			return new TileEntityAdvancedOxygenDecompressor();
		} else if (metadata >= AdvancedOxygenCompressor.OXYGEN_COMPRESSOR_METADATA) {
			return new TileEntityAdvancedOxygenCompressor();
		} else {
			return null;
		}
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		final int angle = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int change = EnumFacing.getHorizontal(angle).getOpposite().getHorizontalIndex();

		if (stack.getItemDamage() >= AdvancedOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA) {
			change += AdvancedOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA;
		} else if (stack.getItemDamage() >= AdvancedOxygenCompressor.OXYGEN_COMPRESSOR_METADATA) {
			change += AdvancedOxygenCompressor.OXYGEN_COMPRESSOR_METADATA;
		}

		worldIn.setBlockState(pos, getStateFromMeta(change), 3);
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
		par3List.add(new ItemStack(this, 1, AdvancedOxygenCompressor.OXYGEN_COMPRESSOR_METADATA));
		par3List.add(new ItemStack(this, 1, AdvancedOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA));
	}

	@Override
	public int damageDropped(IBlockState state) {
		int metadata = getMetaFromState(state);
		if (metadata >= AdvancedOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA) {
			return AdvancedOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA;
		} else if (metadata >= AdvancedOxygenCompressor.OXYGEN_COMPRESSOR_METADATA) {
			return AdvancedOxygenCompressor.OXYGEN_COMPRESSOR_METADATA;
		} else {
			return 0;
		}
	}

	@Override
	public String getShiftDescription(int meta) {
		switch (meta) {
		case OXYGEN_COMPRESSOR_METADATA:
			return GCCoreUtil.translate("tile.oxygen_compressor.description");
		case OXYGEN_DECOMPRESSOR_METADATA:
			return GCCoreUtil.translate("tile.oxygen_decompressor.description");
		}
		return "";
	}

	@Override
	public boolean showDescription(int meta) {
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getHorizontal(meta % 4);
		EnumCompressorType type = EnumCompressorType.byMetadata((int) Math.floor(meta / 4.0));
		return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(TYPE, type);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex() + ((EnumCompressorType) state.getValue(TYPE)).getMeta() * 4;
	}

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, FACING, TYPE);
    }

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.MACHINE;
	}
}

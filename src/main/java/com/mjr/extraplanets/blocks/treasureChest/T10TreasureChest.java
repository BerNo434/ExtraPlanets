package com.mjr.extraplanets.blocks.treasureChest;

import micdoodle8.mods.galacticraft.core.blocks.BlockTier1TreasureChest;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mjr.extraplanets.ExtraPlanets;
import com.mjr.extraplanets.tile.treasureChests.TileEntityT10TreasureChest;

public class T10TreasureChest extends BlockTier1TreasureChest {
	public T10TreasureChest(String assetName) {
		super(assetName);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityT10TreasureChest();
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return ExtraPlanets.BlocksTab;
	}
}
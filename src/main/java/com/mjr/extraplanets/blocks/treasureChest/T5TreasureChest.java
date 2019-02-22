package com.mjr.extraplanets.blocks.treasureChest;

import com.mjr.extraplanets.ExtraPlanets;
import com.mjr.extraplanets.tileEntities.treasureChests.TileEntityT5TreasureChest;

import micdoodle8.mods.galacticraft.core.blocks.BlockTier1TreasureChest;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class T5TreasureChest extends BlockTier1TreasureChest {
	public T5TreasureChest(String name) {
		super(name);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityT5TreasureChest();
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn() {
		return ExtraPlanets.BlocksTab;
	}
}
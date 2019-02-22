package com.mjr.extraplanets.blocks;

import java.util.Random;

import com.mjr.mjrlegendslib.block.BlockBasic;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockFossil extends BlockBasic {

	int randItem = 0;

	protected BlockFossil(Material material) {
		super(material);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.bone;
	}
}

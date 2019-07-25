package com.mjr.extraplanets.itemBlocks.machines;

import com.mjr.extraplanets.blocks.machines.BlockSolar;
import com.mjr.extraplanets.itemBlocks.blocks.ItemBlockDescDefaults;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockSolar extends ItemBlockDescDefaults {
	public ItemBlockSolar(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int index = Math.min(Math.max(par1ItemStack.getItemDamage() / 4, 0), BlockSolar.names.length);

		String name = BlockSolar.names[index];

		return this.field_150939_a.getUnlocalizedName() + "." + name;
	}
}

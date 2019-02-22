package com.mjr.extraplanets.itemBlocks.planetAndMoons.Kepler22b;

import com.mjr.extraplanets.itemBlocks.blocks.ItemBlockDefaults;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockKepler22bMapleLog extends ItemBlockDefaults {
	public ItemBlockKepler22bMapleLog(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";

		switch (itemstack.getItemDamage()) {
		case 0:
		case 4:
		case 8:
			name = "maple_blue_log";
			break;
		case 1:
		case 5:
		case 9:
			name = "maple_red_log";
			break;
		case 2:
		case 6:
		case 10:
			name = "maple_purple_log";
			break;
		case 3:
		case 7:
		case 11:
			name = "maple_yellow_log";
			break;
		default:
			name = "null";
		}

		return this.field_150939_a.getUnlocalizedName() + "." + name;
	}
}
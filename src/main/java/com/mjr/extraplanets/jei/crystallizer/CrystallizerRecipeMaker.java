package com.mjr.extraplanets.jei.crystallizer;

import java.util.ArrayList;
import java.util.List;

import com.mjr.extraplanets.blocks.fluid.ExtraPlanets_Fluids;
import com.mjr.extraplanets.items.ExtraPlanets_Items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class CrystallizerRecipeMaker {
	public static List<CrystallizerRecipeWrapper> getRecipesList() {
		List<CrystallizerRecipeWrapper> recipes = new ArrayList<>();
		ItemStack[] inputs = new ItemStack[1];
		inputs[0] = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ExtraPlanets_Fluids.SALT_FLUID);
		CrystallizerRecipeWrapper wrapper = new CrystallizerRecipeWrapper(inputs, new ItemStack(ExtraPlanets_Items.IODIDE_SALT, 6, 0));
		recipes.add(wrapper);
		return recipes;
	}
}

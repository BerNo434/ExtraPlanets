package com.mjr.extraplanets.jei.blocksmasher;

import javax.annotation.Nonnull;

import com.mjr.extraplanets.jei.RecipeCategories;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.util.GCLog;

public class BlockSmasherRecipeHandler implements IRecipeHandler<BlockSmasherRecipeWrapper> {
	@Nonnull
	@Override
	public Class<BlockSmasherRecipeWrapper> getRecipeClass() {
		return BlockSmasherRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return RecipeCategories.BLOCK_SMASHER_ID;
	}

	@Override
	public String getRecipeCategoryUid(BlockSmasherRecipeWrapper recipe) {
		return this.getRecipeCategoryUid();
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull BlockSmasherRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull BlockSmasherRecipeWrapper recipe) {
		if (recipe.getInputs().size() != 1) {
			GCLog.severe(this.getClass().getSimpleName() + " JEI recipe has wrong number of inputs!");
		}
		if (recipe.getOutputs().size() != 1) {
			GCLog.severe(this.getClass().getSimpleName() + " JEI recipe has wrong number of outputs!");
		}
		return true;
	}
}

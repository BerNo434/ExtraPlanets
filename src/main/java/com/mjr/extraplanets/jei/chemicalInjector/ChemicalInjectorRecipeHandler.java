package com.mjr.extraplanets.jei.chemicalInjector;

import javax.annotation.Nonnull;

import com.mjr.extraplanets.jei.RecipeCategories;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.util.GCLog;

public class ChemicalInjectorRecipeHandler implements IRecipeHandler<ChemicalInjectorRecipeWrapper> {
	@Nonnull
	@Override
	public Class<ChemicalInjectorRecipeWrapper> getRecipeClass() {
		return ChemicalInjectorRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return RecipeCategories.CHEMAICAL_INJECTOR_ID;
	}

	@Override
	public String getRecipeCategoryUid(ChemicalInjectorRecipeWrapper recipe) {
		return this.getRecipeCategoryUid();
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull ChemicalInjectorRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull ChemicalInjectorRecipeWrapper recipe) {
		if (recipe.getInputs().size() != 2) {
			GCLog.severe(this.getClass().getSimpleName() + " JEI recipe has wrong number of inputs!");
		}
		if (recipe.getOutputs().size() != 1) {
			GCLog.severe(this.getClass().getSimpleName() + " JEI recipe has wrong number of outputs!");
		}
		return true;
	}
}

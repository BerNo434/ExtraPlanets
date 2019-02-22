package com.mjr.extraplanets.recipes;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;
import com.mjr.extraplanets.Config;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class RecipeEnabledCondition implements IConditionFactory {
	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) {
		if (JsonUtils.getString(json, "value").equals("thermalpadding"))
			return () -> Config.THERMAL_PADDINGS;
		if (JsonUtils.getString(json, "value").equals("mercury"))
			return () -> Config.MERCURY;
		if (JsonUtils.getString(json, "value").equals("mercury/batteries")) {
			if (Config.MERCURY && Config.BATTERIES)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("mercurytools"))
			return () -> Config.ITEMS_MERCURY;
		if (JsonUtils.getString(json, "value").equals("carbontools"))
			return () -> Config.ITEMS_CARBON;
		if (JsonUtils.getString(json, "value").equals("mercuryslabsandstairs")) {
			if (Config.MERCURY && Config.SLABS_AND_STAIRS)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("ceres"))
			return () -> Config.CERES;
		if (JsonUtils.getString(json, "value").equals("uraniumtools"))
			return () -> Config.ITEMS_URANIUM;
		if (JsonUtils.getString(json, "value").equals("ceresslabsandstairs")) {
			if (Config.CERES && Config.SLABS_AND_STAIRS)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("jupiter"))
			return () -> Config.JUPITER;
		if (JsonUtils.getString(json, "value").equals("palladiumtools"))
			return () -> Config.ITEMS_PALLADIUM;
		if (JsonUtils.getString(json, "value").equals("redgemtools"))
			return () -> Config.ITEMS_GEM_RED;
		if (JsonUtils.getString(json, "value").equals("jupiter/batteries")) {
			if (Config.JUPITER && Config.BATTERIES)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("jupiterslabsandstairs")) {
			if (Config.JUPITER && Config.SLABS_AND_STAIRS)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("saturn"))
			return () -> Config.SATURN;
		if (JsonUtils.getString(json, "value").equals("magnesiumtools"))
			return () -> Config.ITEMS_MAGNESIUM;
		if (JsonUtils.getString(json, "value").equals("saturnslabsandstairs")) {
			if (Config.SATURN && Config.SLABS_AND_STAIRS)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("uranus"))
			return () -> Config.URANUS;
		if (JsonUtils.getString(json, "value").equals("crystaltools"))
			return () -> Config.ITEMS_CRYSTAL;
		if (JsonUtils.getString(json, "value").equals("whitegemtools"))
			return () -> Config.ITEMS_GEM_WHITE;
		if (JsonUtils.getString(json, "value").equals("uranusslabsandstairs")) {
			if (Config.URANUS && Config.SLABS_AND_STAIRS)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("neptune"))
			return () -> Config.NEPTUNE;
		if (JsonUtils.getString(json, "value").equals("bluegemtools"))
			return () -> Config.ITEMS_GEM_BLUE;
		if (JsonUtils.getString(json, "value").equals("zinctools"))
			return () -> Config.ITEMS_ZINC;
		if (JsonUtils.getString(json, "value").equals("neptuneslabsandstairs")) {
			if (Config.NEPTUNE && Config.SLABS_AND_STAIRS)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("neptune/batteries")) {
			if (Config.NEPTUNE && Config.BATTERIES)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("pluto"))
			return () -> Config.PLUTO;
		if (JsonUtils.getString(json, "value").equals("tungstentools"))
			return () -> Config.ITEMS_TUNGSTEN;
		if (JsonUtils.getString(json, "value").equals("plutoslabsandstairs")) {
			if (Config.PLUTO && Config.SLABS_AND_STAIRS)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("eris"))
			return () -> Config.ERIS;
		if (JsonUtils.getString(json, "value").equals("erisslabsandstairs")) {
			if (Config.ERIS && Config.SLABS_AND_STAIRS)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("kepler22b"))
			return () -> Config.KEPLER22B;
		if (JsonUtils.getString(json, "value").equals("bluediamondtools"))
			return () -> Config.KEPLER22B;
		if (JsonUtils.getString(json, "value").equals("reddiamondtools"))
			return () -> Config.KEPLER22B;
		if (JsonUtils.getString(json, "value").equals("purplediamondtools"))
			return () -> Config.KEPLER22B;
		if (JsonUtils.getString(json, "value").equals("yellowdiamondtools"))
			return () -> Config.KEPLER22B;
		if (JsonUtils.getString(json, "value").equals("greendiamondtools"))
			return () -> Config.KEPLER22B;
		if (JsonUtils.getString(json, "value").equals("customapples"))
			return () -> Config.CUSTOM_APPLES;
		if (JsonUtils.getString(json, "value").equals("refinery_advanced"))
			return () -> Config.REFINERY_ADVANCED;
		if (JsonUtils.getString(json, "value").equals("refinery_ultimate")) {
			if (Config.REFINERY_ULTIMATE && Config.REFINERY_ADVANCED)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("refinery_ultimate_not_advanced")) {
			if (Config.REFINERY_ULTIMATE && Config.REFINERY_ADVANCED == false)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("solarpanels"))
			return () -> Config.SOLAR_PANELS;
		if (JsonUtils.getString(json, "value").equals("vehiclecharger"))
			return () -> Config.VEHICLE_CHARGER;
		if (JsonUtils.getString(json, "value").equals("oxygencompressoradvanced"))
			return () -> Config.OXYGEN_COMPRESSOR_ADVANCED;
		if (JsonUtils.getString(json, "value").equals("oxygencompressorultimate")) {
			if (Config.OXYGEN_COMPRESSOR_ULTIMATE && Config.OXYGEN_COMPRESSOR_ADVANCED)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("oxygencompressorultimate_not_advanced")) {
			if (Config.OXYGEN_COMPRESSOR_ULTIMATE && Config.OXYGEN_COMPRESSOR_ADVANCED == false)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("fuelloaderadvanced"))
			return () -> Config.FUEL_LOADER_ADVANCED;
		if (JsonUtils.getString(json, "value").equals("fuelloaderultimate")) {
			if (Config.FUEL_LOADER_ULTIMATE && Config.FUEL_LOADER_ADVANCED)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("fuelloaderultimate_not_advanced")) {
			if (Config.FUEL_LOADER_ULTIMATE && Config.FUEL_LOADER_ADVANCED == false)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("rovers")) {
			if (Config.MARS_ROVER || Config.VENUS_ROVER || Config.ELECTRIC_ROCKET)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("batteries"))
			return () -> Config.BATTERIES;
		if (JsonUtils.getString(json, "value").equals("pressureandradiation")) {
			if (Config.PRESSURE || Config.RADIATION)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("radiation"))
			return () -> Config.RADIATION;
		if (JsonUtils.getString(json, "value").equals("oxygentanks"))
			return () -> Config.OXYGEN_TANKS;
		if (JsonUtils.getString(json, "value").equals("saturn_not_jupiter")) {
			if (Config.JUPITER == false && Config.SATURN)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("radiation_ceres")) {
			if (Config.RADIATION && Config.CERES)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("radiation_not_ceres")) {
			if (Config.CERES == false && Config.RADIATION)
				return () -> true;
			else
				return () -> false;
		}
		if (JsonUtils.getString(json, "value").equals("canned_food")) {
			if (Config.CANNED_FOOD)
				return () -> true;
			else
				return () -> false;
		}

		throw new IllegalStateException("Config defined with recipe_enabled condition without a valid field defined! field was: " + JsonUtils.getString(json, "value") + " - PLEASE REPORT THIS ON THE EXTRAPLANETS ISSUE TRACKER!");
	}
}
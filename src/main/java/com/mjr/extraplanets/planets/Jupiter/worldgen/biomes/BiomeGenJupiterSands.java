package com.mjr.extraplanets.planets.Jupiter.worldgen.biomes;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.planets.Jupiter.worldgen.JupiterBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenJupiterSands extends JupiterBiomes {

	public BiomeGenJupiterSands(BiomeProperties properties) {
		super(properties);
		this.topBlock = ExtraPlanets_Blocks.ORANGE_SAND.getDefaultState();
		this.fillerBlock = ExtraPlanets_Blocks.ORANGE_SANDSTONE.getDefaultState();
	}

	@Override
	public void registerTypes(Biome b) {
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.addTypes(b, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SANDY);
	}
}

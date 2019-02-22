package com.mjr.extraplanets.planets.Jupiter.worldgen.biomes;

import java.util.List;

import com.google.common.collect.Lists;
import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.blocks.planetAndMoonBlocks.BlockBasicJupiter;
import com.mjr.extraplanets.planets.Jupiter.worldgen.JupiterBiomes;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenJupiterMagmaSea extends JupiterBiomes {

	public BiomeGenJupiterMagmaSea(BiomeProperties properties) {
		super(properties);
		this.topBlock = ExtraPlanets_Blocks.JUPITER_BLOCKS.getDefaultState();
		this.fillerBlock = ExtraPlanets_Blocks.JUPITER_BLOCKS.getDefaultState().withProperty(BlockBasicJupiter.BASIC_TYPE, BlockBasicJupiter.EnumBlockBasic.SUB_SURFACE);
	}

	@Override
	public List<Biome.SpawnListEntry> getSpawnableList(EnumCreatureType creatureType) {
		return Lists.<Biome.SpawnListEntry> newArrayList();
	}

	@Override
	public void registerTypes(Biome b) {
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.addTypes(b, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.OCEAN);
	}
}

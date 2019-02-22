package com.mjr.extraplanets.moons.Callisto.worldgen.biomes;

import java.util.List;

import com.google.common.collect.Lists;
import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.blocks.planetAndMoonBlocks.BlockBasicCallisto;
import com.mjr.extraplanets.moons.Callisto.worldgen.CallistoBiomes;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenCallistoSaltSea extends CallistoBiomes {
	public BiomeGenCallistoSaltSea(BiomeProperties properties) {
		super(properties);
		this.spawnableCreatureList.clear();
		this.topBlock = ExtraPlanets_Blocks.CALLISTO_BLOCKS.getDefaultState();
		this.fillerBlock = ExtraPlanets_Blocks.CALLISTO_BLOCKS.getDefaultState().withProperty(BlockBasicCallisto.BASIC_TYPE, BlockBasicCallisto.EnumBlockBasic.SUB_SURFACE);
	}

	@Override
	public List<Biome.SpawnListEntry> getSpawnableList(EnumCreatureType creatureType) {
		return Lists.<Biome.SpawnListEntry> newArrayList();
	}

	@Override
	public void registerTypes(Biome b) {
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.OCEAN);
	}
}
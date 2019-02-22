package com.mjr.extraplanets.planets.Kepler22b.worldgen.biome;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.planets.Kepler22b.worldgen.Kepler22bBiomes;

import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenKepler22bWasteLands extends Kepler22bBiomes {
	public BiomeGenKepler22bWasteLands(BiomeProperties properties) {
		super(properties);
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.registerBiomeType(this, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.WASTELAND);
		this.topBlock = ExtraPlanets_Blocks.KEPLER22B_GRASS_INFECTED.getDefaultState();
		this.fillerBlock = ExtraPlanets_Blocks.KEPLER22B_BLOCKS.getDefaultState();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 100, 4, 4));
		this.getBiomeDecorator().greenShortGrassPerChunk = 50;
		this.getBiomeDecorator().generateHuts = false;
		this.getBiomeDecorator().treeWithNoLeafsPerChunk = 2;
		this.getBiomeDecorator().bigTreeWithNoLeafsPerChunk = 4;
		this.getBiomeDecorator().InfectedLakesPerChunk = 2;
	}
}
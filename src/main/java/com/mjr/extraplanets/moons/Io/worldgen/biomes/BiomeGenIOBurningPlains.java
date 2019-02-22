package com.mjr.extraplanets.moons.Io.worldgen.biomes;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.moons.Io.worldgen.IoBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenIOBurningPlains extends IoBiomes {

	public BiomeGenIOBurningPlains(BiomeProperties properties) {
		super(properties);
		Biome.registerBiome(Config.IO_BURNING_PLAINS_BIOME_ID, Constants.TEXTURE_PREFIX + this.getBiomeName(), this);
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.addTypes(this, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
		this.topBlock = ExtraPlanets_Blocks.VOLCANIC_ROCK.getDefaultState();
		this.fillerBlock = ExtraPlanets_Blocks.ASH_ROCK.getDefaultState();
	}
}

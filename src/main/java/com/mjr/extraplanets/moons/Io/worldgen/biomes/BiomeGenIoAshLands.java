package com.mjr.extraplanets.moons.Io.worldgen.biomes;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.blocks.planetAndMoonBlocks.BlockBasicIo;
import com.mjr.extraplanets.moons.Io.worldgen.IoBiomes;

import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenIoAshLands extends IoBiomes {

	public BiomeGenIoAshLands(BiomeProperties properties) {
		super(properties);
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.registerBiomeType(this, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
		this.topBlock = ExtraPlanets_Blocks.ASH_ROCK.getDefaultState();
		this.fillerBlock = ExtraPlanets_Blocks.IO_BLOCKS.getDefaultState().withProperty(BlockBasicIo.BASIC_TYPE, BlockBasicIo.EnumBlockBasic.SUB_SURFACE);
	}
}

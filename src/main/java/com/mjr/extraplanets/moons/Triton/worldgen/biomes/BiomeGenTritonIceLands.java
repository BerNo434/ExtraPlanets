package com.mjr.extraplanets.moons.Triton.worldgen.biomes;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.blocks.planetAndMoonBlocks.BlockBasicTriton;
import com.mjr.extraplanets.moons.Triton.worldgen.TritonBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenTritonIceLands extends TritonBiomes {

	public BiomeGenTritonIceLands(BiomeProperties properties) {
		super(properties);
		this.topBlock = ExtraPlanets_Blocks.FROZEN_NITROGEN.getDefaultState();
		this.fillerBlock = ExtraPlanets_Blocks.TRITON_BLOCKS.getDefaultState().withProperty(BlockBasicTriton.BASIC_TYPE, BlockBasicTriton.EnumBlockBasic.SUB_SURFACE);
	}

	@Override
	public void registerTypes(Biome b) {
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY);
	}
}

package com.mjr.extraplanets.planets.Neptune.worldgen.biomes;

import java.util.Random;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.BlockDecorativeBlocks2;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.blocks.planetAndMoonBlocks.BlockBasicNeptune;
import com.mjr.extraplanets.planets.Neptune.worldgen.NeptuneBiomes;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenNeptuneLayeredHills extends NeptuneBiomes {

	public IBlockState secondtopBlock;
	public IBlockState thridtopBlocks;

	public BiomeGenNeptuneLayeredHills(BiomeProperties properties) {
		super(properties);
		this.topBlock = ExtraPlanets_Blocks.DECORATIVE_BLOCKS2.getDefaultState().withProperty(BlockDecorativeBlocks2.BASIC_TYPE, BlockDecorativeBlocks2.EnumBlockBasic.BLUE_BLOCK);
		this.thridtopBlocks = ExtraPlanets_Blocks.DECORATIVE_BLOCKS2.getDefaultState().withProperty(BlockDecorativeBlocks2.BASIC_TYPE, BlockDecorativeBlocks2.EnumBlockBasic.LIGHT_BLUE_BLOCK);
		this.secondtopBlock = ExtraPlanets_Blocks.DECORATIVE_BLOCKS2.getDefaultState().withProperty(BlockDecorativeBlocks2.BASIC_TYPE, BlockDecorativeBlocks2.EnumBlockBasic.GREEN_BLOCK);
		this.fillerBlock = ExtraPlanets_Blocks.DECORATIVE_BLOCKS2.getDefaultState().withProperty(BlockDecorativeBlocks2.BASIC_TYPE, BlockDecorativeBlocks2.EnumBlockBasic.YELLOW_BLOCK);
	}

	@Override
	public void registerTypes(Biome b) {
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.HILLS);
	}

	@Override
	public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunk, int x, int z, double stoneNoise) {
		this.generateNeptuneBiomeTerrain(rand, chunk, x, z, stoneNoise);
	}

	@Override
	public void generateNeptuneBiomeTerrain(Random rand, ChunkPrimer chunk, int x, int z, double stoneNoise) {
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate3 = this.secondtopBlock;
		IBlockState iblockstate4 = this.thridtopBlocks;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) (stoneNoise / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunk.setBlockState(i1, j1, l, Blocks.BEDROCK.getDefaultState());
			} else {
				IBlockState iblockstate2 = chunk.getBlockState(i1, j1, l);
				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == ExtraPlanets_Blocks.NEPTUNE_BLOCKS.getDefaultState().withProperty(BlockBasicNeptune.BASIC_TYPE, BlockBasicNeptune.EnumBlockBasic.STONE).getBlock()) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = null;
							iblockstate1 = ExtraPlanets_Blocks.NEPTUNE_BLOCKS.getDefaultState().withProperty(BlockBasicNeptune.BASIC_TYPE, BlockBasicNeptune.EnumBlockBasic.STONE);
							iblockstate3 = null;
							iblockstate4 = null;
						} else if (j1 >= 63 - 4 && j1 <= 63 + 1) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
							iblockstate3 = this.secondtopBlock;
							iblockstate4 = this.thridtopBlocks;
						}

						if (j1 < 63 && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
							if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
								iblockstate = Blocks.ICE.getDefaultState();
							} else {
								iblockstate = Blocks.WATER.getDefaultState();
							}
						}

						j = k;

						if (j1 >= 65 + 18) {
							chunk.setBlockState(i1, j1, l, iblockstate);
							iblockstate1 = iblockstate;
							iblockstate3 = iblockstate;
							iblockstate4 = iblockstate;
						} else if (j1 >= 65 + 12) {
							chunk.setBlockState(i1, j1, l, iblockstate3);
							iblockstate1 = iblockstate3;
							iblockstate = iblockstate3;
							iblockstate4 = iblockstate3;
						} else if (j1 >= 65 + 4) {
							chunk.setBlockState(i1, j1, l, iblockstate4);
							iblockstate1 = iblockstate4;
							iblockstate = iblockstate4;
							iblockstate3 = iblockstate4;
						} else if (j1 < 63 - 7 - k) {
							iblockstate = null;
							iblockstate3 = null;
							iblockstate1 = ExtraPlanets_Blocks.NEPTUNE_BLOCKS.getDefaultState().withProperty(BlockBasicNeptune.BASIC_TYPE, BlockBasicNeptune.EnumBlockBasic.STONE);
							chunk.setBlockState(i1, j1, l, Blocks.GRAVEL.getDefaultState());
						} else {
							chunk.setBlockState(i1, j1, l, iblockstate1);
						}
					} else if (j > 0) {
						--j;
						chunk.setBlockState(i1, j1, l, iblockstate1);
					}
				}
			}
		}
	}
}

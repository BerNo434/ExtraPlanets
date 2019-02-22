package com.mjr.extraplanets.planets.Kepler22b.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mjr.extraplanets.planets.Kepler22b.worldgen.biome.GenLayerKepler22b;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerKepler22b extends WorldChunkManager {
	private GenLayer unzoomedBiomes;
	private GenLayer zoomedBiomes;
	private BiomeCache biomeCache;
	private List<BiomeGenBase> biomesToSpawn;

	protected WorldChunkManagerKepler22b() {
		this.biomeCache = new BiomeCache(this);
		this.biomesToSpawn = new ArrayList<BiomeGenBase>();
		this.biomesToSpawn.add(Kepler22bBiomes.kepler22bPlains);
		this.biomesToSpawn.add(Kepler22bBiomes.kepler22bBlueForest);
		this.biomesToSpawn.add(Kepler22bBiomes.kepler22bPurpleForest);
		this.biomesToSpawn.add(Kepler22bBiomes.kepler22bRedForest);
		this.biomesToSpawn.add(Kepler22bBiomes.kepler22bYellowForest);
		this.biomesToSpawn.add(Kepler22bBiomes.kepler22bRedDesert);
	}

	public WorldChunkManagerKepler22b(long seed) {
		this();
		GenLayer[] agenlayer;
		agenlayer = GenLayerKepler22b.makeTheWorld(seed);
		this.unzoomedBiomes = agenlayer[0];
		this.zoomedBiomes = agenlayer[1];
	}

	public WorldChunkManagerKepler22b(World world) {
		this(world.getSeed());
	}

	public BiomeGenBase getBiome() {
		return Kepler22bBiomes.kepler22bPlains;
	}

	@Override
	public List<BiomeGenBase> getBiomesToSpawnIn() {
		return this.biomesToSpawn;
	}

	@Override
	public float[] getRainfall(float[] par1, int x, int z, int width, int depth) {
		IntCache.resetIntCache();
		int[] aint = this.zoomedBiomes.getInts(x, z, width, depth);

		if (par1 == null || par1.length < width * depth) {
			par1 = new float[width * depth];
		}
		for (int i1 = 0; i1 < width * depth; ++i1) {
			float f = BiomeGenBase.getBiome(aint[i1]).getIntRainfall() / 65536.0F;

			if (f > 1.0F) {
				f = 1.0F;
			}
			par1[i1] = f;
		}
		return par1;
	}

	@Override
	public float getTemperatureAtHeight(float par1, int par2) {
		return par1;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiome, int x, int z, int length, int width) {
		int[] arrayOfInts = this.unzoomedBiomes.getInts(x, z, length, width);

		if (par1ArrayOfBiome == null || par1ArrayOfBiome.length < length * width) {
			par1ArrayOfBiome = new BiomeGenBase[length * width];
		}
		for (int i = 0; i < length * width; i++) {
			if (arrayOfInts[i] >= 0) {
				par1ArrayOfBiome[i] = BiomeGenBase.getBiome(arrayOfInts[i]);
			} else {
				par1ArrayOfBiome[i] = Kepler22bBiomes.kepler22bPlains;
			}
		}
		return par1ArrayOfBiome;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] oldBiomeList, int x, int z, int length, int width) {
		return getBiomeGenAt(oldBiomeList, x, z, length, width, true);
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int z, int length, int width, boolean cacheFlag) {
		IntCache.resetIntCache();

		if (listToReuse == null || listToReuse.length < length * width) {
			listToReuse = new BiomeGenBase[width * length];
		}

		if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0) {
			BiomeGenBase[] cached = this.biomeCache.getCachedBiomes(x, z);
			System.arraycopy(cached, 0, listToReuse, 0, width * length);
			return listToReuse;
		}

		int[] zoomed = zoomedBiomes.getInts(x, z, width, length);

		for (int i = 0; i < width * length; ++i) {
			if (zoomed[i] >= 0) {
				listToReuse[i] = BiomeGenBase.getBiome(zoomed[i]);
			} else {
				listToReuse[i] = this.getBiome();
			}
		}

		return listToReuse;
	}

	@Override
	public boolean areBiomesViable(int par1, int par2, int par3, List<BiomeGenBase> par4List) {
		int i = par1 - par3 >> 2;
		int j = par2 - par3 >> 2;
		int k = par1 + par3 >> 2;
		int l = par2 + par3 >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		int[] ai = this.unzoomedBiomes.getInts(i, j, i1, j1);

		for (int k1 = 0; k1 < i1 * j1; k1++) {
			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(ai[k1]);

			if (!par4List.contains(biomegenbase)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public BlockPos findBiomePosition(int par1, int par2, int par3, List<BiomeGenBase> par4List, Random par5Random) {
		int i = par1 - par3 >> 2;
		int j = par2 - par3 >> 2;
		int k = par1 + par3 >> 2;
		int l = par2 + par3 >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		int[] ai = this.unzoomedBiomes.getInts(i, j, i1, j1);
		BlockPos chunkposition = null;
		int k1 = 0;

		for (int l1 = 0; l1 < ai.length; l1++) {
			int i2 = i + l1 % i1 << 2;
			int j2 = j + l1 / i1 << 2;
			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(ai[l1]);

			if (par4List.contains(biomegenbase) && (chunkposition == null || par5Random.nextInt(k1 + 1) == 0)) {
				chunkposition = new BlockPos(i2, 0, j2);
				k1++;
			}
		}
		return chunkposition;
	}

	@Override
	public void cleanupCache() {
		this.biomeCache.cleanupCache();
	}
}

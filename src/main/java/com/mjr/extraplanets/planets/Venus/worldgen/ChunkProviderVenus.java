package com.mjr.extraplanets.planets.Venus.worldgen;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.prefab.core.BlockMetaPair;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.ChunkProviderSpace;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

import com.google.common.collect.Lists;

public class ChunkProviderVenus extends ChunkProviderSpace {
	private final BiomeDecoratorVenus marsBiomeDecorator = new BiomeDecoratorVenus();

	// private final MapGenCavernMars caveGenerator = new MapGenCavernMars();
	// private final MapGenCaveMars cavernGenerator = new MapGenCaveMars();
	//
	// private final MapGenDungeon dungeonGenerator = new MapGenDungeonMars(new
	// DungeonConfiguration(MarsBlocks.marsBlock.getDefaultState().withProperty(BlockBasicMars.BASIC_TYPE,
	// BlockBasicMars.EnumBlockBasic.DUNGEON_BRICK), 30, 8, 16, 7, 7,
	// RoomBossMars.class, RoomTreasureMars.class));

	public ChunkProviderVenus(World par1World, long seed, boolean mapFeaturesEnabled) {
		super(par1World, seed, mapFeaturesEnabled);
	}

	@Override
	protected BiomeDecoratorSpace getBiomeGenerator() {
		return this.marsBiomeDecorator;
	}

	@Override
	protected BiomeGenBase[] getBiomesForGeneration() {
		return new BiomeGenBase[] { VenusBiomes.venus };
	}

	@Override
	protected int getSeaLevel() {
		return 93;
	}

	@Override
	protected List<MapGenBaseMeta> getWorldGenerators() {
		List<MapGenBaseMeta> generators = Lists.newArrayList();
		// generators.add(this.caveGenerator);
		// generators.add(this.cavernGenerator);
		return generators;
	}

	@Override
	protected BiomeGenBase.SpawnListEntry[] getMonsters() {
		List<BiomeGenBase.SpawnListEntry> monsters = new ArrayList<BiomeGenBase.SpawnListEntry>();
		monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
		monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
		monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
		monsters.add(new BiomeGenBase.SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
		return monsters.toArray(new BiomeGenBase.SpawnListEntry[monsters.size()]);
	}

	@Override
	protected BiomeGenBase.SpawnListEntry[] getCreatures() {
		return new BiomeGenBase.SpawnListEntry[0];
	}

	@Override
	protected BlockMetaPair getGrassBlock() {
		return new BlockMetaPair(MarsBlocks.marsBlock, (byte) 5);
	}

	@Override
	protected BlockMetaPair getDirtBlock() {
		return new BlockMetaPair(MarsBlocks.marsBlock, (byte) 6);
	}

	@Override
	protected BlockMetaPair getStoneBlock() {
		return new BlockMetaPair(MarsBlocks.marsBlock, (byte) 9);
	}

	@Override
	public double getHeightModifier() {
		return 12;
	}

	@Override
	public double getSmallFeatureHeightModifier() {
		return 26;
	}

	@Override
	public double getMountainHeightModifier() {
		return 95;
	}

	@Override
	public double getValleyHeightModifier() {
		return 50;
	}

	@Override
	public int getCraterProbability() {
		return 2000;
	}

	@Override
	public void onChunkProvide(int cX, int cZ, ChunkPrimer primer) {
		// this.dungeonGenerator.generate(this, this.worldObj, cX, cZ, primer);
	}

	@Override
	public void onPopulate(IChunkProvider provider, int cX, int cZ) {
		// this.dungeonGenerator.generateStructure(this.worldObj, this.rand, new
		// ChunkCoordIntPair(cX, cZ));
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {
		// this.dungeonGenerator.generate(this, this.worldObj, x, z, null);
	}
}

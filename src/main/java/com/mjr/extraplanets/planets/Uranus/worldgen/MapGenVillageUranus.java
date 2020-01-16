package com.mjr.extraplanets.planets.Uranus.worldgen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.planets.Uranus.worldgen.biomes.BiomeGenUranus;
import com.mjr.extraplanets.planets.Uranus.worldgen.village.*;
import com.mjr.mjrlegendslib.util.MessageUtilities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenVillageUranus extends MapGenStructure {
	public static List<Biome> villageSpawnBiomes = Arrays.asList(new Biome[] { BiomeGenUranus.uranus });
	private final int terrainType;
	private static boolean initialized;

	static {
		try {
			MapGenVillageUranus.initiateStructures();
		} catch (Throwable e) {

		}
	}

	public static void initiateStructures() throws Throwable {
		if (!MapGenVillageUranus.initialized) {
			MapGenStructureIO.registerStructure(StructureVillageStartUranus.class, "UranusVillage");
			MapGenStructureIO.registerStructureComponent(StructureComponentVillageField.class, "UranusField1");
			MapGenStructureIO.registerStructureComponent(StructureComponentVillageField2.class, "UranusField2");
			MapGenStructureIO.registerStructureComponent(StructureComponentVillageHouse.class, "UranusHouse");
			MapGenStructureIO.registerStructureComponent(StructureComponentVillageRoadPiece.class, "UranusRoadPiece");
			MapGenStructureIO.registerStructureComponent(StructureComponentVillagePathGen.class, "UranusPath");
			MapGenStructureIO.registerStructureComponent(StructureComponentVillageTorch.class, "UranusTorch");
			MapGenStructureIO.registerStructureComponent(StructureComponentVillageStartPiece.class, "UranusWell");
			MapGenStructureIO.registerStructureComponent(StructureComponentVillageWoodHut.class, "UranusWoodHut");
		}

		MapGenVillageUranus.initialized = true;
	}

	public MapGenVillageUranus() {
		this.terrainType = 0;
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int i, int j) {
		final byte numChunks = 32;
		final byte offsetChunks = 8;
		final int oldi = i;
		final int oldj = j;

		if (i < 0) {
			i -= numChunks - 1;
		}

		if (j < 0) {
			j -= numChunks - 1;
		}

		int randX = i / numChunks;
		int randZ = j / numChunks;
		final Random var7 = this.world.setRandomSeed(i, j, 10387312);
		randX *= numChunks;
		randZ *= numChunks;
		randX += var7.nextInt(numChunks - offsetChunks);
		randZ += var7.nextInt(numChunks - offsetChunks);

		return oldi == randX && oldj == randZ;

	}

	@Override
	public BlockPos getClosestStrongholdPos(World world, BlockPos pos, boolean p_180706_3_) {
		this.world = world;
		return findNearestStructurePosBySpacing(world, this, pos, 32, 8, 10387312, false, 100, p_180706_3_);
	}

	@Override
	protected StructureStart getStructureStart(int par1, int par2) {
		if (Config.DEBUG_MODE)
			MessageUtilities.debugMessageToLog(Constants.modID, "Generating Uranus Village at x" + par1 * 16 + " z" + par2 * 16);
		return new StructureVillageStartUranus(this.world, this.rand, par1, par2, this.terrainType);
	}

	@Override
	public String getStructureName() {
		return "UranusVillage";
	}
}

package com.mjr.extraplanets.world.features;

import java.util.Random;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.mjrlegendslib.util.MessageUtilities;
import com.mjr.mjrlegendslib.util.WorldGenUtilities;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCandyCaneType2 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		if (WorldGenUtilities.checkValidSpawn(world, position, 1) == false)
			return false;
		else {
			if (Config.DEBUG_MODE)
				MessageUtilities.debugMessageToLog(Constants.modID, "Spawning Candy Cane Type 2 at (x, y, z)" + position.toString());
			generateStructure(world, rand, position);
		}
		return true;
	}

	public boolean generateStructure(World world, Random rand, BlockPos position) {
		int x = position.getX();
		int y = position.getY();
		int z = position.getZ();
		int randNum = rand.nextInt(11) + 0;
		int metaNumber = 0;
		if (randNum != 0)
			metaNumber = randNum - 1;

		world.setBlockState(new BlockPos(x + 0, y + 0, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 0, y + 1, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 0, y + 2, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 0, y + 3, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 0, y + 4, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 1, y + 5, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS_HORIZONTAL.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 2, y + 6, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS_HORIZONTAL.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 3, y + 6, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS_HORIZONTAL.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 4, y + 5, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS_HORIZONTAL.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 5, y + 4, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS.getStateFromMeta(metaNumber), 2);
		world.setBlockState(new BlockPos(x + 5, y + 3, z + 0), ExtraPlanets_Blocks.CANDY_BLOCKS.getStateFromMeta(metaNumber), 2);
		return true;

	}
}
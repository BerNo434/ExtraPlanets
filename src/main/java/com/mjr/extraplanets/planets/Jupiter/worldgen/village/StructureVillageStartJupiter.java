package com.mjr.extraplanets.planets.Jupiter.worldgen.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureVillageStartJupiter extends StructureStart {
	public StructureVillageStartJupiter() {
	}

	public StructureVillageStartJupiter(World par1World, Random rand, int par3, int par4, int par5) {
		super(par3, par4);

		final ArrayList<StructureVillagePieceWeightJupiter> var6 = StructureVillagePiecesJupiter.getStructureVillageWeightedPieceList(rand, par5);
		final StructureComponentVillageStartPiece var7 = new StructureComponentVillageStartPiece(par1World.getWorldChunkManager(), 0, rand, (par3 << 4) + 2, (par4 << 4) + 2, var6, par5);
		this.components.add(var7);
		var7.buildComponent(var7, this.components, rand);
		final ArrayList<Object> var8 = var7.field_74930_j;
		final ArrayList<Object> var9 = var7.field_74932_i;
		int var10;

		while (!var8.isEmpty() || !var9.isEmpty()) {
			StructureComponent var11;

			if (var8.isEmpty()) {
				var10 = rand.nextInt(var9.size());
				var11 = (StructureComponent) var9.remove(var10);
				var11.buildComponent(var7, this.components, rand);
			} else {
				var10 = rand.nextInt(var8.size());
				var11 = (StructureComponent) var8.remove(var10);
				var11.buildComponent(var7, this.components, rand);
			}
		}

		this.updateBoundingBox();
		var10 = 0;
		final Iterator<StructureComponent> var13 = this.components.iterator();

		while (var13.hasNext()) {
			final StructureComponent var12 = var13.next();

			if (!(var12 instanceof StructureComponentVillageRoadPiece)) {
				++var10;
			}
		}
	}

	/**
	 * currently only defined for Villages, returns true if Village has more than 2 non-road components
	 */
	@Override
	public boolean isSizeableStructure() {
		return true;
	}
}

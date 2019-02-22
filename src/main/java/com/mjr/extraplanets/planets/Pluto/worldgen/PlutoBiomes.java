package com.mjr.extraplanets.planets.Pluto.worldgen;

import com.mjr.extraplanets.Config;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import net.minecraft.world.biome.BiomeGenBase;

public class PlutoBiomes extends BiomeGenBase {

	public static final BiomeGenBase pluto = new BiomeGenPluto(Config.plutoBiomeID).setBiomeName("pluto");

	@SuppressWarnings("unchecked")
	PlutoBiomes(int var1) {
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedZombie.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSpider.class, 10, 4, 4));
		this.rainfall = 0F;
	}

	@Override
	public PlutoBiomes setColor(int var1) {
		return (PlutoBiomes) super.setColor(var1);
	}

	@Override
	public float getSpawningChance() {
		return 0.01F;
	}
}

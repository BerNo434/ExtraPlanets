package com.mjr.extraplanets.moons.Rhea.worldgen;

import com.mjr.extraplanets.Config;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import net.minecraft.world.biome.BiomeGenBase;

public class RheaBiomes extends BiomeGenBase {

	public static final BiomeGenBase rhea = new BiomeGenRhea(Config.rheaBiomeID).setBiomeName("rhea");

	@SuppressWarnings("unchecked")
	RheaBiomes(int var1) {
		super(var1);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedZombie.class, 10, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSpider.class, 10, 4, 4));
		this.rainfall = 0F;
	}

	@Override
	public RheaBiomes setColor(int var1) {
		return (RheaBiomes) super.setColor(var1);
	}

	@Override
	public float getSpawningChance() {
		return 0.01F;
	}
}

package com.mjr.extraplanets.entities.vehicles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import com.mjr.extraplanets.api.block.IPowerDock;
import com.mjr.extraplanets.api.prefabs.entity.EntityPoweredVehicleBase;
import com.mjr.extraplanets.items.ExtraPlanets_Items;
import com.mjr.extraplanets.tileEntities.blocks.TileEntityPoweredChargingPad;
import com.mjr.mjrlegendslib.inventory.IInventoryDefaults;

public class EntityMarsRover extends EntityPoweredVehicleBase implements IInventoryDefaults {
	public float targetAngle;
	public float currentAngle;

	public EntityMarsRover(World world) {
		super(world);
	}

	public EntityMarsRover(World world, float f, float g, float h, int itemDamage) {
		super(world, f, g, h, itemDamage);
	}

	@Override
	public String getInventoryName() {
		return "MarsRover";
	}

	@Override
	public List<ItemStack> getItemsDropped() {
		final List<ItemStack> items = new ArrayList<ItemStack>();

		ItemStack vehicle = new ItemStack(ExtraPlanets_Items.MARS_ROVER, 1, this.roverType);
		vehicle.setTagCompound(new NBTTagCompound());
		vehicle.getTagCompound().setFloat("currentPowerCapacity", 0);
		items.add(vehicle);

		for (ItemStack item : this.stacks) {
			if (item != null) {
				items.add(item);
			}
		}

		return items;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(ExtraPlanets_Items.MARS_ROVER, 1, this.roverType);
	}

	@Override
	public void setPad(IPowerDock pad) {
		this.landingPad = pad;
	}

	@Override
	public IPowerDock getLandingPad() {
		return this.landingPad;
	}

	@Override
	public boolean isDockValid(IPowerDock dock) {
		return dock instanceof TileEntityPoweredChargingPad;
	}

	@Override
	public void featureUpdate() {
		float celestialAngle = (this.world.getCelestialAngle(1.0F));
		this.addPower(celestialAngle / 2, false);
	}
}
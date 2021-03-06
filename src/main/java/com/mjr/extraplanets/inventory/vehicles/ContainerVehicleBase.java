package com.mjr.extraplanets.inventory.vehicles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerVehicleBase extends Container {
	private final IInventory lowerChestInventory;
	private final IInventory spaceshipInv;

	public ContainerVehicleBase(IInventory par1IInventory, IInventory par2IInventory, int type, EntityPlayer player) {
		this.lowerChestInventory = par1IInventory;
		this.spaceshipInv = par2IInventory;
		par2IInventory.openInventory(player);

		int var4;
		int var5;

		if (type != 0) {
			for (var4 = 0; var4 < type * 2; ++var4) {
				for (var5 = 0; var5 < 9; ++var5) {
					this.addSlotToContainer(new Slot(this.spaceshipInv, var5 + var4 * 9, 8 + var5 * 18, 50 + var4 * 18));
				}
			}
		}

		for (var4 = 0; var4 < 3; ++var4) {
			for (var5 = 0; var5 < 9; ++var5) {
				this.addSlotToContainer(new Slot(this.lowerChestInventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 49 + var4 * 18 + 14 + type * 36));
			}
		}

		for (var4 = 0; var4 < 9; ++var4) {
			this.addSlotToContainer(new Slot(this.lowerChestInventory, var4, 8 + var4 * 18, 107 + 14 + type * 36));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return this.spaceshipInv.isUsableByPlayer(entityPlayer);
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int par2) {
		ItemStack var3 = null;
		final Slot var4 = this.inventorySlots.get(par2);
		final int b = this.inventorySlots.size() - 36;

		if (var4 != null && var4.getHasStack()) {
			final ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if (par2 < b) {
				if (!this.mergeItemStack(var5, b, b + 36, true)) {
					return null;
				}
			} else if (!this.mergeItemStack(var5, 0, b, false)) {
				return null;
			}

			if (var5.getCount() == 0) {
				var4.putStack((ItemStack) null);
			} else {
				var4.onSlotChanged();
			}
		}

		return var3;
	}

	/**
	 * Callback for when the crafting gui is closed.
	 */
	@Override
	public void onContainerClosed(EntityPlayer entityPlayer) {
		super.onContainerClosed(entityPlayer);
		this.lowerChestInventory.closeInventory(entityPlayer);
	}

	/**
	 * Return this chest container's lower chest inventory.
	 */
	public IInventory getLowerChestInventory() {
		return this.lowerChestInventory;
	}
}
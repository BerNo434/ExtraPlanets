package com.mjr.extraplanets.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerModuleManager extends Container {
	private final IInventory inventory;

	public ContainerModuleManager(IInventory par1IInventory, EntityPlayer player) {
		this.inventory = par1IInventory;
		// int var6;
		// int var7;

		// Player inv:

		// for (var6 = 0; var6 < 3; ++var6) {
		// for (var7 = 0; var7 < 9; ++var7) {
		// this.addSlotToContainer(new Slot(par1IInventory, var7 + var6 * 9 + 9, 8 + var7 * 18, 91 + 58 + var6 * 18));
		// }
		// }
		//
		// for (var6 = 0; var6 < 9; ++var6) {
		// this.addSlotToContainer(new Slot(par1IInventory, var6, 8 + var6 * 18, 91 + 116));
		// }
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return this.inventory.isUseableByPlayer(entityPlayer);
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

			if (var5.stackSize == 0) {
				var4.putStack(null);
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
		this.inventory.closeInventory(entityPlayer);
	}

	/**
	 * Return this chest container's lower chest inventory.
	 */
	public IInventory getPlayerInventory() {
		return this.inventory;
	}
}
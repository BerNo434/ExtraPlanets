package com.mjr.extraplanets.inventory.machines;

import com.mjr.extraplanets.tileEntities.machines.TileEntityAdvancedRefinery;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAdvancedRefinery extends Container {
	private final TileEntityAdvancedRefinery tileEntity;

	public ContainerAdvancedRefinery(InventoryPlayer par1InventoryPlayer, TileEntityAdvancedRefinery tileEntity) {
		this.tileEntity = tileEntity;

		// Electric Input Slot
		this.addSlotToContainer(new SlotSpecific(tileEntity, 0, 50, 51, ItemElectricBase.class));

		// To be smelted
		this.addSlotToContainer(new Slot(tileEntity, 1, 7, 7));

		// Smelting result
		this.addSlotToContainer(new Slot(tileEntity, 2, 153, 7));
		int var3;

		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 104 + var3 * 18 - 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 144));
		}

		tileEntity.openInventory();
	}

	@Override
	public void onContainerClosed(EntityPlayer entityplayer) {
		super.onContainerClosed(entityplayer);
		this.tileEntity.closeInventory();
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * Called to transfer a stack from one inventory to the other eg. when shift clicking.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1) {
		ItemStack var2 = null;
		final Slot slot = (Slot) this.inventorySlots.get(par1);

		if (slot != null && slot.getHasStack()) {
			final ItemStack var4 = slot.getStack();
			var2 = var4.copy();

			if (par1 < 3) {
				if (!this.mergeItemStack(var4, 3, 39, true)) {
					return null;
				}

				if (par1 == 2) {
					slot.onSlotChange(var4, var2);
				}
			} else {
				if (var4.getItem() instanceof IItemElectric) {
					if (!this.mergeItemStack(var4, 0, 1, false)) {
						return null;
					}
				} else {
					if (FluidUtil.isOilContainerAny(var4)) {
						if (!this.mergeItemStack(var4, 1, 2, false)) {
							return null;
						}
					} else if (FluidUtil.isEmptyContainer(var4, GCItems.fuelCanister)) {
						if (!this.mergeItemStack(var4, 2, 3, false)) {
							return null;
						}
					} else if (par1 < 30) {
						if (!this.mergeItemStack(var4, 30, 39, false)) {
							return null;
						}
					} else if (!this.mergeItemStack(var4, 3, 30, false)) {
						return null;
					}
				}
			}

			if (var4.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (var4.stackSize == var2.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, var4);
		}

		return var2;
	}
}

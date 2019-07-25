package com.mjr.extraplanets.inventory.machines;

import com.mjr.extraplanets.tileEntities.machines.TileEntitySolar;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSolar extends Container {
	private TileEntitySolar tileEntity;

	public ContainerSolar(InventoryPlayer par1InventoryPlayer, TileEntitySolar solarGen) {
		this.tileEntity = solarGen;
		this.addSlotToContainer(new SlotSpecific(solarGen, 0, 152, 83, ItemElectricBase.class));

		int var6;
		int var7;

		// Player inv:

		for (var6 = 0; var6 < 3; ++var6) {
			for (var7 = 0; var7 < 9; ++var7) {
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 51 + 68 + var6 * 18));
			}
		}

		for (var6 = 0; var6 < 9; ++var6) {
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 61 + 116));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return this.tileEntity.isUseableByPlayer(var1);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1) {
		ItemStack var2 = null;
		final Slot slot = (Slot) this.inventorySlots.get(par1);
		final int b = this.inventorySlots.size();

		if (slot != null && slot.getHasStack()) {
			final ItemStack stack = slot.getStack();
			var2 = stack.copy();

			if (par1 == 0) {
				if (!this.mergeItemStack(stack, b - 36, b, true)) {
					return null;
				}
			} else {
				if (stack.getItem() instanceof IItemElectric) {
					if (!this.mergeItemStack(stack, 0, 1, false)) {
						return null;
					}
				} else {
					if (par1 < b - 9) {
						if (!this.mergeItemStack(stack, b - 9, b, false)) {
							return null;
						}
					} else if (!this.mergeItemStack(stack, b - 36, b - 9, false)) {
						return null;
					}
				}
			}

			if (stack.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (stack.stackSize == var2.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, stack);
		}

		return var2;
	}
}

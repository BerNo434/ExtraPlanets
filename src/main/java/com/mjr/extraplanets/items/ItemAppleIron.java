package com.mjr.extraplanets.items;

import java.util.List;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.ExtraPlanets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAppleIron extends ItemFood {
	public ItemAppleIron(int healAmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
		super(healAmount, saturationModifier, isWolfsFavoriteMeat);
		this.setHasSubtypes(true);
		this.setAlwaysEdible();
		this.setUnlocalizedName("apple_iron");
		this.setTextureName(Constants.TEXTURE_PREFIX + "apple_iron");
		this.setCreativeTab(ExtraPlanets.ItemsTab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack) {
		return itemStack.getItemDamage() > 0;
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		return itemStack.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.epic;
	}

	@Override
	protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 250, 0));
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 400, 0));
			player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 600, 0));
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 120, 1));
			player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 120, 1));

		}

		if (itemStack.getItemDamage() > 0) {
			if (!world.isRemote) {
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 350, 0));
				player.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 0));
				player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 700, 0));
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 850, 1));
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 240, 2));
				player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 300, 1));
			}
		} else {
			super.onFoodEaten(itemStack, world, player);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
}

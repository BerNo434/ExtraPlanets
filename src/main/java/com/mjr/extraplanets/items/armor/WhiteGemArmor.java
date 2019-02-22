package com.mjr.extraplanets.items.armor;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.ExtraPlanets;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class WhiteGemArmor extends ItemArmor {
	public String name;

	public WhiteGemArmor(String name, ArmorMaterial material, int placement) {
		super(material, 0, placement);
		setCreativeTab(ExtraPlanets.ArmorTab);
		this.name = name;
	}

	@Override
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type) {
		if (itemStack.getItem() == ExtraPlanets_Armor.WHITE_GEM_HELMET || itemStack.getItem() == ExtraPlanets_Armor.WHITE_GEM_CHEST || itemStack.getItem() == ExtraPlanets_Armor.WHITE_GEM_BOOTS) {
			return Constants.TEXTURE_PREFIX + "textures/model/armor/" + name + "_layer_1.png";
		} else if (itemStack.getItem() == ExtraPlanets_Armor.WHITE_GEM_LEGINGS) {
			return Constants.TEXTURE_PREFIX + "textures/model/armor/" + name + "_layer_2.png";
		} else {
			return null;
		}
	}
}

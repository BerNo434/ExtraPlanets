package com.mjr.extraplanets.client.handlers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import micdoodle8.mods.galacticraft.planets.venus.client.FakeLightningBoltRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.server.permission.PermissionAPI;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.ExtraPlanets;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.blocks.fluid.ExtraPlanets_Fluids;
import com.mjr.extraplanets.blocks.machines.ExtraPlanets_Machines;
import com.mjr.extraplanets.client.gui.overlay.OverlayElectricLaunchCountdown;
import com.mjr.extraplanets.client.gui.overlay.OverlayGeneralLander;
import com.mjr.extraplanets.client.gui.overlay.OverlayJupiterLander;
import com.mjr.extraplanets.client.gui.overlay.OverlayMercuryLander;
import com.mjr.extraplanets.client.gui.overlay.OverlayNeptuneLander;
import com.mjr.extraplanets.client.gui.overlay.OverlayPressure;
import com.mjr.extraplanets.client.gui.overlay.OverlaySaturnLander;
import com.mjr.extraplanets.client.gui.overlay.OverlaySolarRadiation;
import com.mjr.extraplanets.client.gui.overlay.OverlayUranusLander;
import com.mjr.extraplanets.client.gui.screen.CustomCelestialSelection;
import com.mjr.extraplanets.client.handlers.capabilities.CapabilityStatsClientHandler;
import com.mjr.extraplanets.client.handlers.capabilities.IStatsClientCapability;
import com.mjr.extraplanets.entities.landers.EntityGeneralLander;
import com.mjr.extraplanets.entities.landers.EntityJupiterLander;
import com.mjr.extraplanets.entities.landers.EntityMercuryLander;
import com.mjr.extraplanets.entities.landers.EntityNeptuneLander;
import com.mjr.extraplanets.entities.landers.EntitySaturnLander;
import com.mjr.extraplanets.entities.landers.EntityUranusLander;
import com.mjr.extraplanets.entities.rockets.EntityElectricRocketBase;
import com.mjr.extraplanets.network.ExtraPlanetsPacketHandler;
import com.mjr.extraplanets.network.PacketSimpleEP;
import com.mjr.extraplanets.network.PacketSimpleEP.EnumSimplePacket;
import com.mjr.extraplanets.planets.ExtraPlanets_Planets;
import com.mjr.extraplanets.planets.Jupiter.WorldProviderJupiter;
import com.mjr.extraplanets.world.WorldProviderRealisticSpace;
import com.mjr.mjrlegendslib.util.MCUtilities;
import com.mjr.mjrlegendslib.util.TranslateUtilities;

public class MainHandlerClient {

	private static List<ExtraPlanetsPacketHandler> packetHandlers = Lists.newCopyOnWriteArrayList();
	private Map<BlockPos, Integer> lightning = Maps.newHashMap();

	public static void addPacketHandler(ExtraPlanetsPacketHandler handler) {
		MainHandlerClient.packetHandlers.add(handler);
	}

	@SubscribeEvent
	public void worldUnloadEvent(WorldEvent.Unload event) {
		for (ExtraPlanetsPacketHandler packetHandler : packetHandlers) {
			packetHandler.unload(event.getWorld());
		}
	}

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event) {
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final WorldClient world = minecraft.world;
		final EntityPlayerSP player = minecraft.player;

		if (event.phase == Phase.END) {
			if (world != null) {
				for (ExtraPlanetsPacketHandler handler : packetHandlers) {
					handler.tick(world);
				}
			}
		}
		if (event.phase == Phase.START && player != null) {
			boolean isPressed = KeyHandlerClient.spaceKey.isPressed();
			if (!isPressed) {
				ClientProxyCore.lastSpacebarDown = false;
			}

			if (player.getRidingEntity() != null && isPressed) {
				ExtraPlanets.packetPipeline.sendToServer(new PacketSimpleEP(EnumSimplePacket.S_IGNITE_ROCKET, GCCoreUtil.getDimensionID(player.world), new Object[] {}));
				ClientProxyCore.lastSpacebarDown = true;
			}
		}
	}

	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event) {
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;
		final EntityPlayerSP playerBaseClient = PlayerUtil.getPlayerBaseClientFromPlayer(player, false);
		if (player != null && player.world.provider instanceof IGalacticraftWorldProvider && OxygenUtil.shouldDisplayTankGui(minecraft.currentScreen) && OxygenUtil.noAtmosphericCombustion(player.world.provider) && !playerBaseClient.isSpectator()
				&& !minecraft.gameSettings.showDebugInfo) {
			if ((player.world.provider instanceof WorldProviderRealisticSpace)) {
				WorldProviderRealisticSpace provider = (WorldProviderRealisticSpace) player.world.provider;

				if (Config.PRESSURE) {
					int pressureLevel = provider.getPressureLevel();
					OverlayPressure.renderPressureIndicator(pressureLevel, !ConfigManagerCore.oxygenIndicatorLeft, !ConfigManagerCore.oxygenIndicatorBottom);
				}
				if (Config.RADIATION) {
					IStatsClientCapability stats = null;

					if (player != null) {
						stats = playerBaseClient.getCapability(CapabilityStatsClientHandler.EP_STATS_CLIENT_CAPABILITY, null);
					}
					int radiationLevel = (int) Math.floor(stats.getRadiationLevel());
					OverlaySolarRadiation.renderSolarRadiationIndicator(radiationLevel, !ConfigManagerCore.oxygenIndicatorLeft, !ConfigManagerCore.oxygenIndicatorBottom);
				}
			}
		}
		if (minecraft.currentScreen == null && player.getRidingEntity() instanceof EntityElectricRocketBase && minecraft.gameSettings.thirdPersonView != 0 && !minecraft.gameSettings.hideGUI
				&& !((EntityElectricRocketBase) minecraft.player.getRidingEntity()).getLaunched()) {
			OverlayElectricLaunchCountdown.renderCountdownOverlay();
		}
		if (minecraft.currentScreen == null && player.getRidingEntity() instanceof EntityJupiterLander && minecraft.gameSettings.thirdPersonView != 0 && !minecraft.gameSettings.hideGUI) {
			OverlayJupiterLander.renderLanderOverlay();
		}
		if (minecraft.currentScreen == null && player.getRidingEntity() instanceof EntitySaturnLander && minecraft.gameSettings.thirdPersonView != 0 && !minecraft.gameSettings.hideGUI) {
			OverlaySaturnLander.renderLanderOverlay();
		}
		if (minecraft.currentScreen == null && player.getRidingEntity() instanceof EntityMercuryLander && minecraft.gameSettings.thirdPersonView != 0 && !minecraft.gameSettings.hideGUI) {
			OverlayMercuryLander.renderLanderOverlay();
		}
		if (minecraft.currentScreen == null && player.getRidingEntity() instanceof EntityUranusLander && minecraft.gameSettings.thirdPersonView != 0 && !minecraft.gameSettings.hideGUI) {
			OverlayUranusLander.renderLanderOverlay();
		}
		if (minecraft.currentScreen == null && player.getRidingEntity() instanceof EntityNeptuneLander && minecraft.gameSettings.thirdPersonView != 0 && !minecraft.gameSettings.hideGUI) {
			OverlayNeptuneLander.renderLanderOverlay();
		}
		if (minecraft.currentScreen == null && player.getRidingEntity() instanceof EntityGeneralLander && minecraft.gameSettings.thirdPersonView != 0 && !minecraft.gameSettings.hideGUI) {
			OverlayGeneralLander.renderLanderOverlay();
		}
	}

	@SubscribeEvent
	public void renderLightning(ClientProxyCore.EventSpecialRender event) {
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;
		if (player != null && Config.JUITPER_LIGHTING) {
			Iterator<Map.Entry<BlockPos, Integer>> it = lightning.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<BlockPos, Integer> entry = it.next();
				long seed = entry.getValue() / 10 + entry.getKey().getX() + entry.getKey().getZ();
				FakeLightningBoltRenderer.renderBolt(seed, entry.getKey().getX() - ClientProxyCore.playerPosX, entry.getKey().getY() - ClientProxyCore.playerPosY, entry.getKey().getZ() - ClientProxyCore.playerPosZ);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;

		if (player == event.player) {
			if (Config.JUITPER_LIGHTING) {
				Iterator<Map.Entry<BlockPos, Integer>> it = lightning.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<BlockPos, Integer> entry = it.next();
					int val = entry.getValue();
					if (val - 1 <= 0) {
						it.remove();
					} else {
						entry.setValue(val - 1);
					}
				}

				if (player.getRNG().nextInt(100) == 0 && minecraft.world.provider instanceof WorldProviderJupiter) {
					double freq = player.getRNG().nextDouble() * Math.PI * 2.0F;
					double dist = 180.0F;
					double dX = dist * Math.cos(freq);
					double dZ = dist * Math.sin(freq);
					double posX = player.posX + dX;
					double posY = 70;
					double posZ = player.posZ + dZ;
					minecraft.world.playSound(player, posX, posY, posZ, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.WEATHER, 1000.0F, 5.0F + player.getRNG().nextFloat() * 0.2F);
					lightning.put(new BlockPos(posX, posY, posZ), 20);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onToolTip(ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();
		if (stack != null && stack.getItem() != null) {
			if (stack.getItem() instanceof UniversalBucket) {
				if (FluidUtil.getFluidContained(stack) != null && FluidUtil.getFluidContained(stack).getFluid() != null) {
					FluidStack fluidStack = FluidUtil.getFluidContained(stack);
					Fluid fluid = fluidStack.getFluid();
					if (fluid.equals(ExtraPlanets_Fluids.FROZEN_WATER_FLUID) || fluid.equals(ExtraPlanets_Fluids.GLOWSTONE_FLUID) || fluid.equals(ExtraPlanets_Fluids.INFECTED_WATER_FLUID) || fluid.equals(ExtraPlanets_Fluids.LIQUID_HYDROCARBON_FLUID)
							|| fluid.equals(ExtraPlanets_Fluids.MAGMA_FLUID) || fluid.equals(ExtraPlanets_Fluids.METHANE_FLUID) || fluid.equals(ExtraPlanets_Fluids.NITROGEN_FLUID) || fluid.equals(ExtraPlanets_Fluids.RADIO_ACTIVE_WATER_FLUID)
							|| fluid.equals(ExtraPlanets_Fluids.SALT_FLUID) || fluid.equals(ExtraPlanets_Fluids.LIQUID_CARAMEL_FLUID) || fluid.equals(ExtraPlanets_Fluids.LIQUID_CHOCOLATE_FLUID) || fluid.equals(ExtraPlanets_Fluids.NITROGEN_ICE_FLUID)) {
						event.getToolTip().add(EnumColor.AQUA + TranslateUtilities.translate("gui.bucket.message.finding"));
						event.getToolTip().add(EnumColor.AQUA + TranslateUtilities.translate("gui.bucket.message.finding.2." + fluid.getUnlocalizedName().substring(6)));
					} else if (fluid.equals(ExtraPlanets_Fluids.CLEAN_WATER_FLUID))
						event.getToolTip().add(EnumColor.ORANGE + TranslateUtilities.translate("gui.bucket.message.crafting"));

					if (fluid.equals(ExtraPlanets_Fluids.FROZEN_WATER_FLUID) || fluid.equals(ExtraPlanets_Fluids.INFECTED_WATER_FLUID) || fluid.equals(ExtraPlanets_Fluids.NITROGEN_FLUID) || fluid.equals(ExtraPlanets_Fluids.NITROGEN_ICE_FLUID)
							|| fluid.equals(ExtraPlanets_Fluids.RADIO_ACTIVE_WATER_FLUID) || fluid.equals(ExtraPlanets_Fluids.CLEAN_WATER_FLUID)) {
						event.getToolTip().add(EnumColor.BRIGHT_GREEN + TranslateUtilities.translate("gui.bucket.message.extreme.reactors.compact"));
					}
				}
			} else if (stack.getItem().equals(Item.getItemFromBlock(ExtraPlanets_Blocks.VOLCANIC_ROCK))) {
				event.getToolTip().add(EnumColor.AQUA + TranslateUtilities.translate("gui.message.finding.block"));
				event.getToolTip().add(EnumColor.AQUA + TranslateUtilities.translate("gui.message.finding.volcanic.rock"));
			} else if (stack.getItem().equals(Item.getItemFromBlock(ExtraPlanets_Machines.BASIC_DECONTAMINATION_UNIT))) {
				event.getToolTip().add(EnumColor.AQUA + TranslateUtilities.translate("gui.message.decontamination.unit"));
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onGuiOpenEvent(GuiOpenEvent event) {
		if (Config.USE_CUSTOM_CELESTAIAL_SELECTION) {
			if (((event.getGui() instanceof GuiCelestialSelection))) {
				if (GameSettings.isKeyDown(micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient.galaxyMap)) {
					event.setGui(new CustomCelestialSelection(true, null, PermissionAPI.hasPermission(MCUtilities.getMinecraft().player, Constants.PERMISSION_CREATE_STATION)));
				} else {
					event.setGui(new CustomCelestialSelection(false, null, PermissionAPI.hasPermission(MCUtilities.getMinecraft().player, Constants.PERMISSION_CREATE_STATION)));
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
	public void onRenderFogDensity(EntityViewRenderEvent.FogDensity event) {
		if(Config.CUSTOM_FOG){
			if (event.getEntity().world.provider.getDimensionType().getId() == Config.JUPITER_ID) {
				event.setDensity(0.02f);
				GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
				event.setCanceled(true);
			} else if (event.getEntity().world.provider.getDimensionType().getId() == Config.URANUS_ID) {
				event.setDensity(0.008f);
				GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
				event.setCanceled(true);
			} else if (event.getEntity().world.provider.getDimensionType().getId() == Config.SATURN_ID) {
				event.setDensity(0.015f);
				GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
				event.setCanceled(true);
			} else if (event.getEntity().world.provider.getDimensionType().getId() == Config.NEPTUNE_ID) {
				event.setDensity(0.01f);
				GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
				event.setCanceled(true);
			} else
				GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
		}
	}

	@SubscribeEvent
	public void onRenderPlanetPost(CelestialBodyRenderEvent.Post event) {
		Minecraft mc = MCUtilities.getMinecraft();
		if (mc.currentScreen instanceof GuiCelestialSelection) {
			if (event.celestialBody == ExtraPlanets_Planets.SATURN) {
				mc.renderEngine.bindTexture(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/saturn_rings.png"));
				float size = GuiCelestialSelection.getWidthForCelestialBodyStatic(event.celestialBody) / 6.0F;
				((GuiCelestialSelection) mc.currentScreen).drawTexturedModalRect(-7.5F * size, -1.75F * size, 15.0F * size, 3.5F * size, 0, 0, 30, 7, false, false, 30, 7);
			} else if (event.celestialBody == ExtraPlanets_Planets.URANUS) {
				mc.renderEngine.bindTexture(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/uranus_rings.png"));
				float size = GuiCelestialSelection.getWidthForCelestialBodyStatic(event.celestialBody) / 6.0F;
				((GuiCelestialSelection) mc.currentScreen).drawTexturedModalRect(-1.75F * size, -7.0F * size, 3.5F * size, 14.0F * size, 0, 0, 28, 7, false, false, 28, 7);
			}
		} else if (mc.currentScreen instanceof CustomCelestialSelection) {
			if (event.celestialBody == ExtraPlanets_Planets.SATURN) {
				mc.renderEngine.bindTexture(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/saturn_rings.png"));
				float size = GuiCelestialSelection.getWidthForCelestialBodyStatic(event.celestialBody) / 6.0F;
				((CustomCelestialSelection) mc.currentScreen).drawTexturedModalRect(-7.5F * size, -1.75F * size, 15.0F * size, 3.5F * size, 0, 0, 30, 7, false, false, 30, 7);
			} else if (event.celestialBody == ExtraPlanets_Planets.URANUS) {
				mc.renderEngine.bindTexture(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/uranus_rings.png"));
				float size = GuiCelestialSelection.getWidthForCelestialBodyStatic(event.celestialBody) / 6.0F;
				((CustomCelestialSelection) mc.currentScreen).drawTexturedModalRect(-1.75F * size, -7.0F * size, 3.5F * size, 14.0F * size, 0, 0, 28, 7, false, false, 28, 7);
			}
		}
	}
}

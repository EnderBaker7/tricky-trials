package com.ender.trickytrials;

import com.ender.trickytrials.content.*;
import com.ender.trickytrials.content.weathering.logic.WeatheringLogic;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(TrickyTrials.MODID)
public class TrickyTrials {

    public static final String MODID = "trickytrials";

    private static final Logger LOGGER = LogUtils.getLogger();

    public TrickyTrials(FMLJavaModLoadingContext context) {

        IEventBus modEventBus = context.getModEventBus();

        TTCreative.register(modEventBus);
        TTSoundEvents.register(modEventBus);
        TTBlocks.register(modEventBus);
        TTItems.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(WeatheringLogic.WeatheringStage::setupStageMap);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(TTItems.CHISELED_COPPER);
            event.accept(TTItems.EXPOSED_CHISELED_COPPER);
            event.accept(TTItems.WEATHERED_CHISELED_COPPER);
            event.accept(TTItems.OXIDIZED_CHISELED_COPPER);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    private static void registerCutout(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
               registerCutout(TTBlocks.COPPER_GRATE.get());
               registerCutout(TTBlocks.EXPOSED_COPPER_GRATE.get());
               registerCutout(TTBlocks.WEATHERED_COPPER_GRATE.get());
               registerCutout(TTBlocks.OXIDIZED_COPPER_GRATE.get());

               registerCutout(TTBlocks.WAXED_COPPER_GRATE.get());
               registerCutout(TTBlocks.WAXED_EXPOSED_COPPER_GRATE.get());
               registerCutout(TTBlocks.WAXED_WEATHERED_COPPER_GRATE.get());
               registerCutout(TTBlocks.WAXED_OXIDIZED_COPPER_GRATE.get());

               registerCutout(TTBlocks.COPPER_DOOR.get());
               registerCutout(TTBlocks.EXPOSED_COPPER_DOOR.get());
               registerCutout(TTBlocks.WEATHERED_COPPER_DOOR.get());
               registerCutout(TTBlocks.OXIDIZED_COPPER_DOOR.get());

               registerCutout(TTBlocks.WAXED_COPPER_DOOR.get());
               registerCutout(TTBlocks.WAXED_EXPOSED_COPPER_DOOR.get());
               registerCutout(TTBlocks.WAXED_WEATHERED_COPPER_DOOR.get());
               registerCutout(TTBlocks.WAXED_OXIDIZED_COPPER_DOOR.get());
            });
        }
    }
}

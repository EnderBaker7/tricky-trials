package com.ender.trickytrials.content.datagen;

import com.ender.trickytrials.TrickyTrials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TrickyTrials.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        generator.addProvider(event.includeServer(), new TTRecipeProvider(output));
        generator.addProvider(event.includeClient(), new TTBlockStateProvider(output, TrickyTrials.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new TTItemModelProvider(output, TrickyTrials.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new TTLootTableProvider(output));
        generator.addProvider(event.includeServer(), new TTBlockTagProvider(output, event.getLookupProvider(), TrickyTrials.MODID, event.getExistingFileHelper()));
    }
}

package com.ender.trickytrials.content;

import com.ender.trickytrials.TrickyTrials;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TTCreative {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TrickyTrials.MODID);

    public static final RegistryObject<CreativeModeTab> TRICKY_TAB = CREATIVE_TABS.register("creative_tab",
            () -> CreativeModeTab.builder()
                    // .icon(() -> new ItemStack(TTItems.TRIAL_KEY.get()))
                    .icon(() -> new ItemStack(Items.COPPER_INGOT))
                    .title(Component.translatable("itemGroup.trickytrials"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(TTItems.CHISELED_COPPER.get());
                        output.accept(TTItems.EXPOSED_CHISELED_COPPER.get());
                        output.accept(TTItems.WEATHERED_CHISELED_COPPER.get());
                        output.accept(TTItems.OXIDIZED_CHISELED_COPPER.get());

                        output.accept(TTItems.WAXED_CHISELED_COPPER.get());
                        output.accept(TTItems.WAXED_EXPOSED_CHISELED_COPPER.get());
                        output.accept(TTItems.WAXED_WEATHERED_CHISELED_COPPER.get());
                        output.accept(TTItems.WAXED_OXIDIZED_CHISELED_COPPER.get());

                        output.accept(TTItems.COPPER_BULB.get());
                        output.accept(TTItems.EXPOSED_COPPER_BULB.get());
                        output.accept(TTItems.WEATHERED_COPPER_BULB.get());
                        output.accept(TTItems.OXIDIZED_COPPER_BULB.get());

                        output.accept(TTItems.WAXED_COPPER_BULB.get());
                        output.accept(TTItems.WAXED_EXPOSED_COPPER_BULB.get());
                        output.accept(TTItems.WAXED_WEATHERED_COPPER_BULB.get());
                        output.accept(TTItems.WAXED_OXIDIZED_COPPER_BULB.get());

                        output.accept(TTItems.COPPER_GRATE.get());
                        output.accept(TTItems.EXPOSED_COPPER_GRATE.get());
                        output.accept(TTItems.WEATHERED_COPPER_GRATE.get());
                        output.accept(TTItems.OXIDIZED_COPPER_GRATE.get());

                        output.accept(TTItems.WAXED_COPPER_GRATE.get());
                        output.accept(TTItems.WAXED_EXPOSED_COPPER_GRATE.get());
                        output.accept(TTItems.WAXED_WEATHERED_COPPER_GRATE.get());
                        output.accept(TTItems.WAXED_OXIDIZED_COPPER_GRATE.get());

                        output.accept(TTItems.COPPER_DOOR.get());
                        output.accept(TTItems.EXPOSED_COPPER_DOOR.get());
                        output.accept(TTItems.WEATHERED_COPPER_DOOR.get());
                        output.accept(TTItems.OXIDIZED_COPPER_DOOR.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}

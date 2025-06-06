package com.ender.trickytrials.content;

import net.minecraft.world.level.block.DoorBlock;

public class BaseCopperDoorBlock extends DoorBlock {
    public BaseCopperDoorBlock(Properties properties) {
        super(properties.noOcclusion(), TTBlockSetTypes.COPPER);
    }
}

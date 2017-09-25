package com.pikycz.novamobs.advance;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityInteractable;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * @author PikyCZ
 */
public abstract class Interact extends EntityInteractable {

    public Interact(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    public boolean interact(Player player) {
        return true;
    }
    
}

package com.pikycz.novamobs.advance;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityOwnable;
import cn.nukkit.entity.passive.EntityTameable;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * @author PikyCZ
 */
public abstract class Tameable extends EntityTameable implements EntityOwnable {
    
    public Tameable(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    public boolean isOwner(Player player){
        return player.equals(this.getOwner());
    }
    
}

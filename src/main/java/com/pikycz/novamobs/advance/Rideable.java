package com.pikycz.novamobs.advance;

import cn.nukkit.entity.EntityRideable;
import cn.nukkit.math.Vector3;

/**
 * @author PikyCZ
 */
public interface Rideable extends EntityRideable {
    
    public Vector3 getRidePosition();
    
}

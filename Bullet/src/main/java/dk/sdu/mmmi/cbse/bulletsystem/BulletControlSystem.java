/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author Michael-PC
 */
public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(EntityType.BULLET)) {
            if (entity.getIsHit()) {
                world.removeEntity(entity);
            }
            entity.setX(entity.getX() + entity.getSpeed() * (float) Math.cos(entity.getRadians()) * gameData.getDelta());
            entity.setY(entity.getY() + entity.getSpeed() * (float) Math.sin(entity.getRadians()) * gameData.getDelta());
            
            entity.setShapeX(new float[]{/*Left*/entity.getX() + 2.5f * (float) Math.cos(entity.getRadians() + Math.PI * 0.8),
                                         /*Forward*/entity.getX() + 3 * (float) Math.cos(entity.getRadians()), 
                                         /*Right*/entity.getX() + 2.5f * (float) Math.cos(entity.getRadians() + Math.PI * 1.2),
                                         /*Backward*/entity.getX() + 1.25f * (float) Math.cos(entity.getRadians() + Math.PI)});
            
            entity.setShapeY(new float[]{/*Left*/entity.getY() + 2.5f * (float) Math.sin(entity.getRadians() + Math.PI * 0.8),
                                         /*Forward*/entity.getY() + 3 * (float) Math.sin(entity.getRadians()), 
                                         /*Right*/entity.getY() + 2.5f * (float) Math.sin(entity.getRadians() + Math.PI * 1.2),
                                         /*Backward*/entity.getY() + 1.25f * (float) Math.sin(entity.getRadians() + Math.PI)});
        }
    }
    
}

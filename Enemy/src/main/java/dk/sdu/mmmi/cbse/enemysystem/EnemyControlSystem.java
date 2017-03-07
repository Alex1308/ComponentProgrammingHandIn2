/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Random;

/**
 *
 * @author Michael-PC
 */
public class EnemyControlSystem implements IEntityProcessingService {

    Random rand = new Random();
    
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(EntityType.ENEMY)) {
            entity.setShootTimer(entity.getShootTimer() + 1);
            entity.setSpeed(entity.getMaxSpeed());
            
            for (Entity player : world.getEntities(EntityType.PLAYER)) {
                 entity.setRadians((float) Math.atan2(player.getY() - entity.getY(), player.getX() - entity.getX()));
            }
            
            
            entity.setX(entity.getX() + entity.getSpeed() * (float) Math.cos(entity.getRadians()) * gameData.getDelta());
            entity.setY(entity.getY() + entity.getSpeed() * (float) Math.sin(entity.getRadians()) * gameData.getDelta());
            
            if (entity.getShootTimer() > 200 - rand.nextInt(50)) {
                gameData.addEvent(new Event(EventType.ENEMY_SHOOT, entity.getID()));
                entity.setShootTimer(0);
            }
            
            entity.setShapeX(new float[]{/*Left*/entity.getX() + 10 * (float) Math.cos(entity.getRadians() + Math.PI * 0.8),
                                         /*Forward*/entity.getX() + 12 * (float) Math.cos(entity.getRadians()), 
                                         /*Right*/entity.getX() + 10 * (float) Math.cos(entity.getRadians() + Math.PI * 1.2),
                                         /*Backward*/entity.getX() + 5 * (float) Math.cos(entity.getRadians() + Math.PI)});
            
            entity.setShapeY(new float[]{/*Left*/entity.getY() + 10 * (float) Math.sin(entity.getRadians() + Math.PI * 0.8),
                                         /*Forward*/entity.getY() + 12 * (float) Math.sin(entity.getRadians()), 
                                         /*Right*/entity.getY() + 10 * (float) Math.sin(entity.getRadians() + Math.PI * 1.2),
                                         /*Backward*/entity.getY() + 5 * (float) Math.sin(entity.getRadians() + Math.PI)});
        }
        
    }
    
}

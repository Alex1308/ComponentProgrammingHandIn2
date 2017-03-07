/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroid;

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
public class AsteroidControlSystem implements IEntityProcessingService {

    int counter = 201;
    int checker = 0;

    @Override
    public void process(GameData gameData, World world) {
        Random rand = new Random();

        for (Entity entity : world.getEntities(EntityType.ASTEROIDS)) {

            //Moving
            entity.setX(entity.getX() + entity.getSpeed() * (float) Math.cos(entity.getRadians()) * gameData.getDelta());
            entity.setY(entity.getY() + entity.getSpeed() * (float) Math.sin(entity.getRadians()) * gameData.getDelta());

            entity.setShapeX(new float[]{
                entity.getX() - entity.getRadius(),
                entity.getX() - entity.getRadius() / 2,
                entity.getX() + entity.getRadius() / 2,
                entity.getX() + entity.getRadius(),
                entity.getX() + entity.getRadius() / 2,
                entity.getX() - entity.getRadius() / 2});
            entity.setShapeY(new float[]{
                entity.getY(),
                entity.getY() + (float) Math.sqrt((Math.pow(entity.getRadius(), 2) - Math.pow(entity.getRadius() / 2, 2))),
                entity.getY() + (float) Math.sqrt((Math.pow(entity.getRadius(), 2) - Math.pow(entity.getRadius() / 2, 2))),
                entity.getY(),
                entity.getY() - (float) Math.sqrt((Math.pow(entity.getRadius(), 2) - Math.pow(entity.getRadius() / 2, 2))),
                entity.getY() - (float) Math.sqrt((Math.pow(entity.getRadius(), 2) - Math.pow(entity.getRadius() / 2, 2)))});

        }

    }

    public void split(Entity e, World world, GameData gameData) {
        if (e.getRadius() > 4) {
            gameData.getEvents().add(new Event(EventType.ASTEROID_SPLIT, e.getID()));
        } else world.removeEntity(e);
    }
}

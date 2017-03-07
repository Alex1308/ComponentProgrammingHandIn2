package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.events.EventType;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(EntityType.PLAYER)) {

            if (gameData.getKeys().isDown(0)) {
                //Forward
                if (entity.getSpeed() < entity.getMaxSpeed()) {
                    entity.setSpeed(entity.getSpeed() + entity.getAcceleration());
                } else {
                    entity.setSpeed(entity.getMaxSpeed());
                }
            }
            if (gameData.getKeys().isDown(2)) {
                //Backward
                if (entity.getSpeed() > -entity.getMaxSpeed()) {
                    entity.setSpeed(entity.getSpeed() - entity.getAcceleration());
                } else {
                    entity.setSpeed(-entity.getMaxSpeed());
                }
            }
            if (gameData.getKeys().isDown(1)) {
                //Left
                entity.setRadians(entity.getRadians() + entity.getRotationSpeed() * 0.75f * (float) Math.PI * gameData.getDelta());
            }

            if (gameData.getKeys().isDown(3)) {
                //Right
                entity.setRadians(entity.getRadians() - entity.getRotationSpeed() * 0.75f * (float) Math.PI * gameData.getDelta());
            }
            if (gameData.getKeys().isPressed(6)) {
                //Shoot
                gameData.addEvent(new Event(EventType.PLAYER_SHOOT, entity.getID()));
            }
            if (entity.getSpeed() > 0 && !gameData.getKeys().isDown(1) && !gameData.getKeys().isDown(1)) {
                entity.setSpeed(entity.getSpeed() - entity.getDeacceleration());
            }
            if (entity.getSpeed() < 0 && !gameData.getKeys().isDown(1) && !gameData.getKeys().isDown(1)) {
                entity.setSpeed(entity.getSpeed() + entity.getDeacceleration());
            }

            entity.setX(entity.getX() + entity.getSpeed() * (float) Math.cos(entity.getRadians()) * gameData.getDelta());
            entity.setY(entity.getY() + entity.getSpeed() * (float) Math.sin(entity.getRadians()) * gameData.getDelta());

            entity.setShapeX(new float[]{/*Left*/entity.getX() + 10 * (float) Math.cos(entity.getRadians() + Math.PI * 0.8),
                /*Forward*/ entity.getX() + 12 * (float) Math.cos(entity.getRadians()),
                /*Right*/ entity.getX() + 10 * (float) Math.cos(entity.getRadians() + Math.PI * 1.2),
                /*Backward*/ entity.getX() + 5 * (float) Math.cos(entity.getRadians() + Math.PI)});

            entity.setShapeY(new float[]{/*Left*/entity.getY() + 10 * (float) Math.sin(entity.getRadians() + Math.PI * 0.8),
                /*Forward*/ entity.getY() + 12 * (float) Math.sin(entity.getRadians()),
                /*Right*/ entity.getY() + 10 * (float) Math.sin(entity.getRadians() + Math.PI * 1.2),
                /*Backward*/ entity.getY() + 5 * (float) Math.sin(entity.getRadians() + Math.PI)});
        }

    }
}

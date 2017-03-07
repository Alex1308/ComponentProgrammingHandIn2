/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ENEMY;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

/**
 *
 * @author Michael-PC
 */
public class EntityPlugin implements IGamePluginService {

    private Entity enemy;

    public EntityPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Entity();
        enemyShip.setType(ENEMY);
        Random rand = new Random();

        if (rand.nextFloat() < 0.25f) {
            enemyShip.setPosition(gameData.getDisplayWidth() * rand.nextFloat(), 0);
        } else if (0.25f <= rand.nextFloat() && rand.nextFloat() < 0.49f) {
            enemyShip.setPosition(0, gameData.getDisplayHeight() * rand.nextFloat());
        } else if (0.50f <= rand.nextFloat() && rand.nextFloat() < 0.74f) {
            enemyShip.setPosition(gameData.getDisplayWidth(), gameData.getDisplayHeight() * rand.nextFloat());
        } else if (0.75f <= rand.nextFloat() && rand.nextFloat() < 1) {
            enemyShip.setPosition(gameData.getDisplayWidth() * rand.nextFloat(), gameData.getDisplayHeight());
        }

        enemyShip.setMaxSpeed(50);
        enemyShip.setAcceleration(10);
        enemyShip.setDeacceleration(10);
        enemyShip.setWrap(true);
        enemyShip.setLife(2);
        enemyShip.setCollision(true);

        enemyShip.setRadians(3.1415f / 2);
        enemyShip.setRotationSpeed(1);

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }

}

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
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

/**
 *
 * @author Michael-PC
 */
public class EntityPlugin implements IGamePluginService {

    private Entity asteroid;

    public EntityPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroidShip = new Entity();
        asteroidShip.setType(EntityType.ASTEROIDS);
        Random rand = new Random();

        float randomNumber = rand.nextFloat();

        if (rand.nextFloat() < 0.25f) {
            asteroidShip.setPosition(gameData.getDisplayWidth() * rand.nextFloat(), 0);
        } else if (0.25f <= rand.nextFloat() && rand.nextFloat() < 0.49f) {
            asteroidShip.setPosition(0, gameData.getDisplayHeight() * rand.nextFloat());
        } else if (0.50f <= rand.nextFloat() && rand.nextFloat() < 0.74f) {
            asteroidShip.setPosition(gameData.getDisplayWidth(), gameData.getDisplayHeight() * rand.nextFloat());
        } else if (0.75f <= rand.nextFloat() && rand.nextFloat() < 1) {
            asteroidShip.setPosition(gameData.getDisplayWidth() * rand.nextFloat(), gameData.getDisplayHeight());
        }

        int[] sizes = {8, 16, 32};
        asteroidShip.setSpeed(80);
        asteroidShip.setRadius(sizes[rand.nextInt(3)]);
        asteroidShip.setRadians((float) (Math.PI * 2 * rand.nextFloat()));
        asteroidShip.setWrap(true);
        asteroidShip.setCollision(true);

        return asteroidShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }

}

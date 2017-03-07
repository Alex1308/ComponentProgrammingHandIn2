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
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 *
 * @author Michael-PC
 */
public class EntityPlugin implements IGamePluginService {

    private Entity bullet;

    private float x, y, radian;

    public EntityPlugin(float x, float y, float radian) {
        this.x = x;
        this.y = y;
        this.radian = radian;
    }

    @Override
    public void start(GameData gameData, World world) {
        bullet = createBullet(gameData);
        world.addEntity(bullet);
    }

    private Entity createBullet(GameData gameData) {
        Entity bulletInstance = new Entity();
        bulletInstance.setType(EntityType.BULLET);

        bulletInstance.setSpeed(300);

        bulletInstance.setX(x + 15 * (float) Math.cos(radian));
        bulletInstance.setY(y + 15 * (float) Math.sin(radian));

        bulletInstance.setRadians(radian);

        return bulletInstance;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(bullet);
    }

}

package net.minestom.server.entity.type;

import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.utils.Position;

public class EntityCaveSpider extends EntityCreature {
    public EntityCaveSpider(Position spawnPosition) {
        super(EntityType.CAVE_SPIDER, spawnPosition);
        setBoundingBox(0.7f, 0.5f, 0.7f);
    }
}
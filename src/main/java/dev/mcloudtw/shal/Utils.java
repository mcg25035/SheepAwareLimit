package dev.mcloudtw.shal;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Sheep;
import org.bukkit.util.Vector;

import java.util.UUID;


public class Utils {
    public static boolean isMobInSmallSpace(Entity entity) {
        if (!entity.isOnGround()) return false;

        Vector[] wall = new Vector[]{
                new Vector(0, -1, 0),
                new Vector(1, 0, 0),
                new Vector(-1, 0, 0),
                new Vector(0, 0, 1),
                new Vector(0, 0, -1)
        };

        Location entityLocation = entity.getLocation().toBlockLocation();

        int debugCount = 0;

        for (Vector vector : wall) {
            boolean isBlock = true;
            for (int i = 0; i < (vector.getY() == 0 ? 2 : 1); i++) {
                Location location = entityLocation.clone().add(vector.clone()).add(0, i, 0);
                isBlock = isBlock && !location.getBlock().isPassable();
                debugCount++;
            }

            if (!isBlock) return false;


        }
        return true;
    }

    public static void setAIByIsInLargeSpace(Mob mob) {
        if (mob.isOnGround()) {
            mob.setAI(!isMobInSmallSpace(mob));
            return;
        }
        mob.setAI(true);
    }

    public static void sheepEatGrass(Sheep sheep) {
       if (sheep.readyToBeSheared()) return;
       Block sheepGroundBlock = sheep.getLocation().subtract(0, 1, 0).getBlock();
       boolean isGrassGround = sheepGroundBlock.getType() == Material.GRASS_BLOCK;
       if (!isGrassGround) return;
       sheepGroundBlock.setType(Material.DIRT);
       sheepGroundBlock.getState().update();

       sheep.setSheared(false);
    }

    public static IntVec3D getEntityIntVec3D(Entity entity) {
        Location location = entity.getLocation();
        return new IntVec3D(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}

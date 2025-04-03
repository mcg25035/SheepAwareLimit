package dev.mcloudtw.shal;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public final class Main extends JavaPlugin {
    public static HashMap<IntVec3D, Boolean> cacheAIResult = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getScheduler().runTaskTimer(this, this::onSecond, 0L, 20*3L);
    }

    public void onSecond() {

        Consumer<Sheep> sheepEatGrass = (sheep)->{if (!sheep.hasAI()) Utils.sheepEatGrass(sheep);};

//        int sheepCount = 0;
        cacheAIResult.clear();
        for (Entity entity : getServer().getWorld("world").getEntities()) {
            if (!(entity instanceof Sheep sheep)) continue;
            if (cacheAIResult.containsKey(Utils.getEntityIntVec3D(sheep))) {
                sheep.setAI(cacheAIResult.get(Utils.getEntityIntVec3D(sheep)));
                sheepEatGrass.accept(sheep);
                continue;
            }

//            sheepCount++;

            Utils.setAIByIsInLargeSpace(sheep);
            cacheAIResult.put(Utils.getEntityIntVec3D(sheep), sheep.hasAI());
            sheepEatGrass.accept(sheep);


        }
//        getLogger().info("Sheep count: " + sheepCount);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

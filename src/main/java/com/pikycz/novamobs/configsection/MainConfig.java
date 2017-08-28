package com.pikycz.novamobs.configsection;

import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import com.pikycz.novamobs.utils.ConfigFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author PikyCZ
 */
public class MainConfig extends ConfigFile {

    public final HashMap<Integer, Level> levelsToSpawn = new HashMap<>();
    public List<String> disabledWorlds;
    public static boolean SpawnAnimals;
    public static boolean SpawnMobs;
    public static boolean MOB_AI_ENABLED;
    public static int SpawnDelay;

    public MainConfig(PluginBase plugin) {
        super(plugin, "config");
        this.disabledWorlds = this.config.getList("worlds-spawn-disabled", new ArrayList());
        MainConfig.SpawnAnimals = this.config.getBoolean("entities.spawn-animals");
        MainConfig.SpawnMobs = this.config.getBoolean("entities.spawn-mobs");
        MainConfig.MOB_AI_ENABLED = this.config.getBoolean("entities.mob-ai");
        MainConfig.SpawnDelay = this.config.getInt("entities.auto-spawn-tick");
    }

    public List<String> getdisabledWorlds() {
        return this.disabledWorlds;
    }

    public boolean getspawnAnimals() {
        return MainConfig.SpawnAnimals;
    }

    public boolean getspawnMobs() {
        return MainConfig.SpawnMobs;
    }

    public boolean getMOB_AI_ENABLED() {
        return MainConfig.MOB_AI_ENABLED;
    }

    public int getSpawnDelay() {
        return MainConfig.SpawnDelay;
    }

}

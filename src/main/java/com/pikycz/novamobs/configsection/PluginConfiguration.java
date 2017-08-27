package com.pikycz.novamobs.configsection;

import cn.nukkit.Server;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * TODO Rewrite 
 * @author PikyCZ
 */
public class PluginConfiguration extends PluginBase {

    private ConfigSection entities;
    
    public static boolean MOB_AI_ENABLED = true;
    
    public final HashMap<Integer, Level> levelsToSpawn = new HashMap<>();
    private List<String> disabledWorlds;
    public static boolean spawnAnimals = true;
    public static boolean spawnMonsters = true;

    public void LoadConfig() {
        this.getLogger().info("Prepare" + this.getDataFolder() + "NovaMobs.yml");
        this.getDataFolder().mkdirs();
        this.loadAll();
    }

    public Config getPluginConfig() {
        return new Config(this.getDataFolder() + "NovaMobs.yml", Config.YAML);
    }

    public void loadAll() {
        this.saveConfig();
        this.loadEntities();
    }

    @SuppressWarnings("serial")
    public void loadEntities() {
        this.entities = new Config(this.getDataFolder() + "NovaMobs.yml", Config.YAML,
                new ConfigSection(new LinkedHashMap<String, Object>() {
                    {
                        put ("entities.mob-ai", false);
                        put("entities.spawn-animals", true);
                        put("entities.spawn-mobs", true);
                        put ("disabledWorlds", "");
                    }
                })).getSections();
    }

    public void saveAll() {
        this.saveEntities();
        this.getConfig().save();
    }

    public void saveEntities(boolean async) {
        Config mobplugin = new Config(this.getDataFolder() + "NovaMobs.yml", Config.YAML);
        mobplugin.setAll(this.entities);
        mobplugin.save(async);
    }

    public void saveEntities() {
        Config mobplugin = new Config(this.getDataFolder() + "NovaMobs.yml", Config.YAML);
        mobplugin.setAll(this.entities);
        mobplugin.save();
    }

    public void disableWorlds() {
        for (Level level : Server.getInstance().getLevels().values()) {
            if (disabledWorlds.contains(level.getFolderName().toLowerCase())) {
                continue;
            }

            levelsToSpawn.put(level.getId(), level);
        }
    }
}

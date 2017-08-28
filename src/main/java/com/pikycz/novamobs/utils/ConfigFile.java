package com.pikycz.novamobs.utils;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import java.io.File;
import java.io.IOException;

/**
 * @author PikyCZ
 */
public abstract class ConfigFile extends File {

    protected final Config config;

    public ConfigFile(PluginBase plugin, String name) {
        super(plugin.getDataFolder(), name + ".yml");
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
        if (!this.exists()) {
            try {
                if (plugin.getResource(name + ".yml") != null) {
                    plugin.saveResource(name + ".yml", true);
                } else {
                    this.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = new Config((File) this, 1);
    }

    public void saveConfig() {
        this.config.save();
    }
}

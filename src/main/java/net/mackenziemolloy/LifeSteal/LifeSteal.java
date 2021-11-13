package net.mackenziemolloy.LifeSteal;

import net.mackenziemolloy.LifeSteal.hooks.PlaceHolderAPIHook;
import net.mackenziemolloy.LifeSteal.utils.CommentedConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LifeSteal extends JavaPlugin {

    public CommentedConfiguration config;
    public CommentedConfiguration dataStorage;

    public void onEnable() {
        this.config = new CommentedConfiguration();
        this.dataStorage = new CommentedConfiguration();

        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            // Hooks only if the config option is enabled
            if (config.getBoolean("options.hooks.placeholderapi")) {
                // PlaceHolderAPI Hook Initializer
                new PlaceHolderAPIHook(this).register();
                this.getLogger().info("Hooked into PlaceHolderAPI!");
            }
        }
    }

    public CommentedConfiguration getConfiguration() {
        return this.config;
    }

    public CommentedConfiguration getDataStorage() {
        return this.dataStorage;
    }

    public void generateFiles() {
        saveDefaultConfig();
        File pluginFolder = getDataFolder();
        File configFile = new File(pluginFolder, "config.yml");

        try {
            this.config.load(configFile);

            InputStream jarConfig = getResource("config.yml");
            this.config.syncWithConfig(configFile, jarConfig, "stupid_option");
        } catch(IOException | InvalidConfigurationException ex) {
            Logger logger = getLogger();
            logger.log(Level.SEVERE, "Failed to load the 'config.yml' file due to an error:", ex);
        }
    }

}

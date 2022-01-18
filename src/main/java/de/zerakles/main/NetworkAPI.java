package de.zerakles.main;

import de.zerakles.settings.Config;
import de.zerakles.settings.ConfigData;
import org.bukkit.plugin.java.JavaPlugin;

public class NetworkAPI extends JavaPlugin {

    private static NetworkAPI networkAPI;

    public ConfigData configData;
    private Config config = new Config();

    public static NetworkAPI getNetworkAPI() {
        return networkAPI;
    }

    @Override
    public void onEnable(){
        networkAPI = this;
        configData = config.loadConfig();
        if(configData == null)
            return;
    }

    public void onDisable(){

    }

}

package de.zerakles.main;

import de.zerakles.coins.Coins;
import de.zerakles.mysql.MySQL;
import de.zerakles.permission.Permission;
import de.zerakles.settings.Config;
import de.zerakles.settings.ConfigData;
import org.bukkit.plugin.java.JavaPlugin;

public class NetworkAPI extends JavaPlugin {

    private static NetworkAPI networkAPI;

    public ConfigData configData;
    public Coins coinManager = new Coins();
    public Permission permissionManager = new Permission();
    private Config config = new Config();
    public MySQL mySQL;

    public static NetworkAPI getNetworkAPI() {
        return networkAPI;
    }

    @Override
    public void onEnable(){
        networkAPI = this;
        configData = config.loadConfig();
        if(configData == null)
            return;
        mySQL = new MySQL(configData.getMysqlHost(), configData.getMysqlPort()+"",
                configData.getMysqlDatabase(), configData.getMysqlUser(), configData.getMysqlPassword());
        if(configData.isCoinsActive())
            coinManager.activateCoins();
        if(configData.isPluginPermissionsActive())
            permissionManager.activatePermissions();
    }

    public void onDisable(){}

}

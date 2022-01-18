package de.zerakles.settings;

import de.zerakles.main.NetworkAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private File file;
    private String path = NetworkAPI.getNetworkAPI().getDataFolder().getPath() + "/NetworkAPI/config.yml";
    private boolean exist;

    public ConfigData loadConfig(){
        file = new File(path);
        if(!checkExist(file))
            createFile(file);
        ConfigData configData = loadVars();
        return configData;
    }

    private ConfigData loadVars() {
        if(!exist)
            return null;
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        //mysql
        String mysqlUser = fileConfiguration.getString("mysql.user");
        String mysqlPassword = fileConfiguration.getString("mysql.password");
        String mysqlDatabase = fileConfiguration.getString("mysql.database");
        String mysqlHost = fileConfiguration.getString("mysql.host");
        int mysqlPort = fileConfiguration.getInt("mysql.port");
        //coins
        boolean coinsActive = fileConfiguration.getBoolean("coins.active");
        String coinName = fileConfiguration.getString("coins.name");
        int coinWinAmount = fileConfiguration.getInt("coins.win");
        int coinLoseAmount = fileConfiguration.getInt("coins.lose");
        boolean coinAddMethodActive = fileConfiguration.getBoolean("coins.addmethodactive");
        //permission
        boolean pluginPermissions = fileConfiguration.getBoolean("plugin.permissions");
        ConfigData configData = new ConfigData(mysqlUser, mysqlDatabase, mysqlPassword, mysqlHost, mysqlPort,
                coinsActive, coinName, coinWinAmount, coinLoseAmount, coinAddMethodActive, pluginPermissions);
        return configData;
    }

    private void createFile(File file) {
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("mysql.user", "root");
        fileConfiguration.set("mysql.password", "password");
        fileConfiguration.set("mysql.database", "database");
        fileConfiguration.set("mysql.port", 3306);
        fileConfiguration.set("mysql.host", "localhost");
        fileConfiguration.set("coins.active", true);
        fileConfiguration.set("coins.name", "Coins");
        fileConfiguration.set("coins.win", 10);
        fileConfiguration.set("coins.lose", 5);
        fileConfiguration.set("coins.addmethodactive", true);
        fileConfiguration.set("plugin.permissions", true);

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        NetworkAPI.getNetworkAPI().getServer().getConsoleSender().sendMessage("§cServer restarting in 2 sec!");
        Bukkit.getScheduler().scheduleSyncDelayedTask(NetworkAPI.getNetworkAPI(), new Runnable() {
            @Override
            public void run() {
                NetworkAPI.getNetworkAPI().getServer().getConsoleSender().sendMessage("§cServer restarting in 1 sec!");
            }
        },1000);
        Bukkit.getScheduler().scheduleSyncDelayedTask(NetworkAPI.getNetworkAPI(), new Runnable() {
            @Override
            public void run() {
                NetworkAPI.getNetworkAPI().getServer().getConsoleSender().sendMessage("§cServer restarting now!");
                Bukkit.getServer().shutdown();
            }
        },2100);
    }

    private boolean checkExist(File file) {
        exist = file.exists();
        if(file.exists())
            return true;
        return false;
    }

}

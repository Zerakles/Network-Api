package de.zerakles.settings;

public class ConfigData {

    // mysql info
    private String mysqlUser;
    private String mysqlDatabase;
    private String mysqlHost;
    private int mysqlPort;
    private String mysqlPassword;

    //coins
    private boolean coinsActive;
    private String coinsName;
    private int coinWinAmount;
    private int coinLoseAmount;
    private boolean coinCustomAddMethod;

    //plugin permissions
    private boolean pluginPermissionsActive;

    public ConfigData(String mysqlUser, String mysqlDatabase, String mysqlPassword,
                      String mysqlHost, int mysqlPort, boolean coinsActive,
                      String coinsName, int coinWinAmount, int coinLoseAmount,
                      boolean coinCustomAddMethod, boolean pluginPermissionsActive) {
        this.mysqlUser = mysqlUser;
        this.mysqlDatabase = mysqlDatabase;
        this.mysqlPassword = mysqlPassword;
        this.mysqlHost = mysqlHost;
        this.mysqlPort = mysqlPort;
        this.coinsActive = coinsActive;
        this.coinsName = coinsName;
        this.coinWinAmount = coinWinAmount;
        this.coinLoseAmount = coinLoseAmount;
        this.coinCustomAddMethod = coinCustomAddMethod;
        this.pluginPermissionsActive = pluginPermissionsActive;
        return;
    }

    public boolean isCoinCustomAddMethod() {
        return coinCustomAddMethod;
    }

    public boolean isCoinsActive() {
        return coinsActive;
    }

    public boolean isPluginPermissionsActive() {
        return pluginPermissionsActive;
    }

    public int getCoinLoseAmount() {
        return coinLoseAmount;
    }

    public int getCoinWinAmount() {
        return coinWinAmount;
    }

    public int getMysqlPort() {
        return mysqlPort;
    }

    public String getCoinsName() {
        return coinsName;
    }

    public String getMysqlDatabase() {
        return mysqlDatabase;
    }

    public String getMysqlHost() {
        return mysqlHost;
    }

    public String getMysqlPassword() {
        return mysqlPassword;
    }

    public String getMysqlUser() {
        return mysqlUser;
    }

    public void setCoinCustomAddMethod(boolean coinCustomAddMethod) {
        this.coinCustomAddMethod = coinCustomAddMethod;
    }

    public void setCoinLoseAmount(int coinLoseAmount) {
        this.coinLoseAmount = coinLoseAmount;
    }

    public void setCoinsActive(boolean coinsActive) {
        this.coinsActive = coinsActive;
    }

    public void setCoinsName(String coinsName) {
        this.coinsName = coinsName;
    }

    public void setCoinWinAmount(int coinWinAmount) {
        this.coinWinAmount = coinWinAmount;
    }

    public void setMysqlDatabase(String mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }

    public void setMysqlHost(String mysqlHost) {
        this.mysqlHost = mysqlHost;
    }

    public void setMysqlPassword(String mysqlPassword) {
        this.mysqlPassword = mysqlPassword;
    }

    public void setMysqlPort(int mysqlPort) {
        this.mysqlPort = mysqlPort;
    }

    public void setMysqlUser(String mysqlUser) {
        this.mysqlUser = mysqlUser;
    }

    public void setPluginPermissionsActive(boolean pluginPermissionsActive) {
        this.pluginPermissionsActive = pluginPermissionsActive;
    }
}
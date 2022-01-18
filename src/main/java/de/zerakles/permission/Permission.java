package de.zerakles.permission;

import de.zerakles.main.NetworkAPI;
import de.zerakles.mysql.MySQL;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Permission {


    private MySQL getMySQL() {
        return NetworkAPI.getNetworkAPI().mySQL;
    }

    public void activatePermissions(){
        getMySQL().update("CREATE TABLE IF NOT EXISTS rank(name VARCHAR(40), level VARCHAR(2), prefix VARCHAR(40));");
        getMySQL().update("CREATE TABLE IF NOT EXISTS playerRank(player_name VARCHAR(40), player_uuid VARCHAR(40), rank VARCHAR(40))");
    }

    public String getRank(Player player){

        return "";
    }

    public String getPrefix(String rank){
        return "";
    }

    public String getRank(OfflinePlayer player){

        return "";
    }

    public  void setRank(Player player){

    }

    public  void setRank(OfflinePlayer player){

    }

    public  void removeRank(Player player, String rank){

    }

    public  void removeRank(OfflinePlayer player, String rank){

    }

    public void hasRank(Player player){

    }

    public boolean hasRank(OfflinePlayer offlinePlayer){
        return false;
    }

    public void registerRank(String rank, int level, String prefix){

    }

    public void unregisterRank(String rank){

    }

    public boolean rankExists(String rank){
        return false;
    }

    public boolean checkPlayerRankLevel(Player player, int level){
        return false;
    }

}

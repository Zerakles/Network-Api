package de.zerakles.permission;

import de.zerakles.main.NetworkAPI;
import de.zerakles.mysql.MySQL;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Permission {


    private MySQL getMySQL() {
        return NetworkAPI.getNetworkAPI().mySQL;
    }

    public void activatePermissions(){
        getMySQL().update(
                "CREATE TABLE IF NOT EXISTS rank(name VARCHAR(40), level VARCHAR(2), prefix VARCHAR(40));");
        getMySQL().update(
                "CREATE TABLE IF NOT EXISTS playerRank(player_name VARCHAR(40), player_uuid VARCHAR(40)," +
                        " rank VARCHAR(40))");
    }

    public String getRank(Player player){
        if(!hasRank(player))
            return null;
        ResultSet resultSet = getMySQL().getResult("SELECT * FROM playerRank WHERE player_uuid='" +
                player.getUniqueId().toString() + "';");
        try {
            if(resultSet.next()){
                return resultSet.getString("rank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPrefix(String rank){
        if(!rankExists(rank))
            return null;
        ResultSet resultSet = getMySQL().getResult("SELECT * FROM rank WHERE name='" + rank + "';");
        try {
            if(resultSet.next()){
                return resultSet.getString("prefix");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRank(OfflinePlayer offlinePlayer){
        if(!hasRank(offlinePlayer))
            return null;
        ResultSet resultSet = getMySQL().getResult("SELECT * FROM playerRank WHERE player_uuid='" +
                offlinePlayer.getUniqueId().toString() + "';");
        try {
            if(resultSet.next()){
                return resultSet.getString("rank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  void setRank(Player player,  String rank){
        if(hasRank(player)){
            getMySQL().update("UPDATE playerRank SET rank='" + rank + "' WHERE player_uuid='"
                    + player.getUniqueId().toString() + "';");
            return;
        }
        getMySQL().update("INSERT INTO playerRank(player_name, player_uuid, rank) VALUES ('" +
                player.getName() + "','" + player.getUniqueId().toString() + "','" + rank +"');");
        return;
    }

    public  void setRank(OfflinePlayer offlinePlayer, String rank){
        if(hasRank(offlinePlayer)){
            getMySQL().update("UPDATE playerRank SET rank='" + rank + "' WHERE player_uuid='"
                    + offlinePlayer.getUniqueId().toString() + "';");
            return;
        }
        getMySQL().update("INSERT INTO playerRank(player_name, player_uuid, rank) VALUES ('" +
                offlinePlayer.getName() + "','" + offlinePlayer.getUniqueId().toString() + "','" + rank +"');");
        return;
    }

    public  void removeRank(Player player){
        if(hasRank(player)){
            getMySQL().update("DELETE FROM playerRank WHERE player_uuid='" +
                    player.getUniqueId().toString()+"';");
            return;
        }
        return;
    }

    public  void removeRank(OfflinePlayer offlinePlayer){
        if(hasRank(offlinePlayer)){
            getMySQL().update("DELETE FROM playerRank WHERE player_uuid='" +
                    offlinePlayer.getUniqueId().toString()+"';");
            return;
        }
        return;
    }

    public boolean hasRank(Player player){
        ResultSet resultSet = getMySQL().getResult("SELECT * FROM playerRank WHERE player_uuid='"
                + player.getUniqueId().toString() + "';");
        try {
            if(resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasRank(OfflinePlayer offlinePlayer){
        ResultSet resultSet = getMySQL().getResult("SELECT * FROM playerRank WHERE player_uuid='"
                + offlinePlayer.getUniqueId().toString() + "';");
        try {
            if(resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registerRank(String rank, int level, String prefix){
        getMySQL().update("INSERT INTO rank(rank, level, prefix) VALUES ('" + rank + "','" + level + "','"
                + prefix + "');");
        return;
    }

    public void unregisterRank(String rank){
        getMySQL().update("DELETE FROM rank WHERE name='"+rank+"';");
        return;
    }

    public boolean rankExists(String rank){
        ResultSet resultSet = getMySQL().getResult("SELECT * FROM rank WHERE name='"
                + rank + "';");
        try {
            if(resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPlayerRankLevel(Player player, int level){
        if(!hasRank(player))
            return false;
        String string = getRank(player);
        ResultSet resultSet = getMySQL().getResult("SELECT * FROM rank WHERE name='"
                + string + "';");
        try {
            if(resultSet.next()){
                if(resultSet.getInt("level") == level || resultSet.getInt("level")>level){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPlayerRankLevel(OfflinePlayer offlinePlayer, int level){
        if(!hasRank(offlinePlayer))
            return false;
        String string = getRank(offlinePlayer);
        ResultSet resultSet = getMySQL().getResult("SELECT * FROM rank WHERE name='"
                + string + "';");
        try {
            if(resultSet.next()){
                if(resultSet.getInt("level") == level || resultSet.getInt("level")>level){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

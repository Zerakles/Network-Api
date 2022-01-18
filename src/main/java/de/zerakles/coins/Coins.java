package de.zerakles.coins;

import de.zerakles.main.NetworkAPI;
import de.zerakles.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Coins {

    private MySQL getMySQL() {
        return NetworkAPI.getNetworkAPI().mySQL;
    }

    public void activateCoins(){
        getMySQL().update("CREATE TABLE IF NOT EXISTS "+NetworkAPI.getNetworkAPI().configData.getCoinsName()
                + "(player_name VARCHAR(40), player_uuid VARCHAR(40), amount VARCHAR(999));");
    }

    public void addCustomCoins(Player player, Integer amount){
        if(!checkPlayerExists(player)){
            int current = getCoins(player);
            int newAmount = current + amount;
            getMySQL().update("UPDATE " +NetworkAPI.getNetworkAPI().configData.getCoinsName() + " SET amount='"
                    + newAmount + "' WHERE player_uuid='" + player.getUniqueId().toString() + "';");
            return;
        }
        getMySQL().update("INSERT INTO " +NetworkAPI.getNetworkAPI().configData.getCoinsName() + "(player_name," +
                "player_uuid, amount) VALUES ('" + player.getName() + "','" + player.getUniqueId().toString() + "','" +
                amount+"');");
        return;
    }

    public void addWin(Player player){
        int amount = NetworkAPI.getNetworkAPI().configData.getCoinWinAmount();
        if(!checkPlayerExists(player)){
            int current = getCoins(player);
            int newAmount = current + amount;
            getMySQL().update("UPDATE " +NetworkAPI.getNetworkAPI().configData.getCoinsName() + " SET amount='"
                    + newAmount + "' WHERE player_uuid='" + player.getUniqueId().toString() + "';");
            return;
        }
        getMySQL().update("INSERT INTO " +NetworkAPI.getNetworkAPI().configData.getCoinsName() + "(player_name," +
                "player_uuid, amount) VALUES ('" + player.getName() + "','" + player.getUniqueId().toString() + "','" +
                amount+"');");
        return;
    }

    public void addLose(Player player){
        int amount = NetworkAPI.getNetworkAPI().configData.getCoinLoseAmount();
        if(!checkPlayerExists(player)){
            int current = getCoins(player);
            int newAmount = current + amount;
            getMySQL().update("UPDATE " +NetworkAPI.getNetworkAPI().configData.getCoinsName() + " SET amount='"
                    + newAmount + "' WHERE player_uuid='" + player.getUniqueId().toString() + "';");
            return;
        }
        getMySQL().update("INSERT INTO " +NetworkAPI.getNetworkAPI().configData.getCoinsName() + "(player_name," +
                "player_uuid, amount) VALUES ('" + player.getName() + "','" + player.getUniqueId().toString() + "','" +
                amount+"');");
        return;
    }

    public int getCoins(Player player){
        if(!checkPlayerExists(player)){
            String Statement = "SELECT * FROM " + NetworkAPI.getNetworkAPI().configData.getCoinsName()
                    + " WHERE player_uuid='" + player.getUniqueId().toString() +"'";
            ResultSet resultSet = getMySQL().getResult(Statement);
            try {
                if(resultSet.next()){
                    return resultSet.getInt("amount");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void removeCoins(Player player, Integer amount){
        if(!checkPlayerExists(player)){
            int current = getCoins(player);
            int newAmount = current - amount;
            getMySQL().update("UPDATE " +NetworkAPI.getNetworkAPI().configData.getCoinsName() + " SET amount='"
                    + newAmount + "' WHERE player_uuid='" + player.getUniqueId().toString() + "';");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("§CILLEGAL ARGUMENT, PLEASE CHECK PLAYER AMOUNT FIRST!!!!");
        return;
    }

    private boolean checkPlayerExists(Player player){
        String Statement = "SELECT * FROM " + NetworkAPI.getNetworkAPI().configData.getCoinsName()
                + " WHERE player_uuid='" + player.getUniqueId().toString() +"'";
        ResultSet resultSet = getMySQL().getResult(Statement);
        try {
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}

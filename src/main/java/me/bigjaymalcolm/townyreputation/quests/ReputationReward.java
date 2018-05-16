package me.bigjaymalcolm.townyreputation.quests;

import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.blackvein.quests.CustomReward;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import me.bigjaymalcolm.townyreputation.Main;

import java.util.Map;

public class ReputationReward extends CustomReward
{
    public ReputationReward()
    {
        this.setName("Reputation Reward");
        this.setAuthor("BigJayMalcolm");
        this.setRewardName("Town Reputation");
        this.addData("Town/Nation");
        this.addData("Reputation");
        this.addDescription("Town/Nation", "Enter the name of the town/nation to alter reputation of.");
        this.addDescription("Reputation", "Enter the number to increase reputation by.");
    }

    @Override
    public void giveReward(Player player, Map<String, Object> data)
    {
        Integer reputation = Integer.parseInt((String) data.get("Reputation"));
        String town = (String) data.get("Town/Nation");

        if (TownyUniverse.getDataSource().hasTown(town) || TownyUniverse.getDataSource().hasNation(town))
        {
            String playerName = player.getName();
            FileConfiguration playerData = Main.PlayerReputations.get(playerName);

            if (reputation > 0)
            {
                playerData.set("reputation." + town.toLowerCase(), (reputation + ((Integer) playerData.get("reputation." + town.toLowerCase()))));
            }
            else
            {
                playerData.set("reputation." + town.toLowerCase(), reputation);
            }

            player.sendMessage("Your reputation towards " + town + " has been changed by " + reputation.toString()); // Inform the player that their reputation has changed
        }
    }
}
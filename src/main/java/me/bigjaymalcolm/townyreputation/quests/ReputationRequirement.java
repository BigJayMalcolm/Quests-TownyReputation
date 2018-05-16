package me.bigjaymalcolm.townyreputation.quests;

import com.palmergames.bukkit.towny.object.TownyUniverse;
import me.bigjaymalcolm.townyreputation.Main;
import me.blackvein.quests.CustomRequirement;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;

public class ReputationRequirement extends CustomRequirement
{
    public ReputationRequirement()
    {
        this.setName("Reputation Requirement");
        this.setAuthor("BigJayMalcolm");
        this.addData("Town/Nation");
        this.addData("Reputation");
        this.addDescription("Town/Nation", "The town/nation the player needs to have reputation with.");
        this.addDescription("Reputation", "What the reputation need to be at least.");
    }

    @Override
    public boolean testRequirement(Player player, Map<String, Object> data)
    {
        Boolean result = false;

        String town = (String) data.get("Town/Nation");
        Integer reputation = Integer.parseInt((String) data.get("Reputation"));
        FileConfiguration playerData = Main.PlayerReputations.get(player.getName());

        if (TownyUniverse.getDataSource().hasTown(town) || TownyUniverse.getDataSource().hasNation(town))
        {
            if (playerData.contains("reputation." + town.toLowerCase()))
            {
                Integer currentReputation = (Integer) playerData.get("reputation." + town.toLowerCase());

                if (currentReputation >= reputation)
                {
                    result = true;
                }
            }
            else
            {
                if (reputation <= 0)
                {
                    result = true;
                }
            }
        }

        return result;
    }
}

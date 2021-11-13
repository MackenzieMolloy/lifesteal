package net.mackenziemolloy.LifeSteal.hooks;

import net.mackenziemolloy.LifeSteal.LifeSteal;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceHolderAPIHook extends PlaceholderExpansion implements Listener  {

    private LifeSteal lifeSteal;

    public PlaceHolderAPIHook(final LifeSteal lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "lifesteal";
    }

    @Override
    public String getAuthor() {
        return "Mackenzie Molloy";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    public String onPlaceholderRequest(final Player player, final String params) {

        if(params.equalsIgnoreCase("hearts")) {
            return "TBC";
        }

        else {
            return "%lifesteal_" + params + "%";
        }

    }

}

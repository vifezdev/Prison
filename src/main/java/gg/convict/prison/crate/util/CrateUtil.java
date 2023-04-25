package gg.convict.prison.crate.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CrateUtil {

    public static <T> T getRandom(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    public static void removeChance(ItemStack itemStack) {
        List<String> newLore = new ArrayList<>();
        if (!hasChance(itemStack))
            return;

        ItemMeta itemMeta = itemStack.getItemMeta();
        for (String s : itemMeta.getLore()) {
            if (s.contains("Chance: "))
                continue;

            newLore.add(s);
        }

        itemMeta.setLore(newLore);
        itemStack.setItemMeta(itemMeta);
    }

    public static boolean hasChance(ItemStack itemStack) {
        if (!itemStack.hasItemMeta()
                || !itemStack.getItemMeta().hasLore())
            return false;

        for (String s : itemStack.getItemMeta().getLore()) {
            if (s.contains("Chance: "))
                return true;
        }

        return false;
    }

}
package gg.convict.prison.pickaxe;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.configuration.StaticConfiguration;
import org.hydrapvp.libraries.utils.ItemNbtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class PickaxeHandler implements StaticConfiguration {

    private final Map<UUID, PickaxeData> dataMap = new HashMap<>();

    public void addData(ItemStack itemStack, PickaxeData data) {
        UUID uuid = readId(itemStack);

        if (uuid != null)
            dataMap.put(uuid, data);
    }

    public PickaxeData getData(ItemStack itemStack) {
        if (itemStack == null
                || itemStack.getType() != Material.DIAMOND_PICKAXE)
            return null;

        UUID uuid = readId(itemStack);
        return uuid == null
                ? null : dataMap.get(uuid);
    }

    public UUID readId(ItemStack itemStack) {
        String string = ItemNbtUtil.getString(itemStack, "ToolUUID");

        return string == null ? null : UUID.fromString(string);
    }

}
package gg.convict.prison.privatemine.grid.menu.button;

import com.google.common.collect.ImmutableList;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.privatemine.grid.MineGrid;
import gg.convict.prison.privatemine.grid.SchematicType;
import gg.convict.prison.privatemine.grid.prompt.GridAmountPrompt;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.NullConversationPrefix;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.CC;

import java.util.List;

@RequiredArgsConstructor
public class GridAddTypeButton extends Button {

    private final MineGrid grid;
    private final SchematicType type;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(grid.isCanPaste() ? type.getItemStack() : new ItemStack(Material.BEDROCK))
                .setDisplayName(CC.format("&bAdd Island &7┃ %s%s", type.getColor(), type.getTextName()))
                .setLore(getLore()).build();
    }

    public List<String> getLore() {
        return ImmutableList.of(
                "",
                CC.format("&fFree Mines: &b%s", MineModule.get().getHandler().getMineCount(type)),
                "",
                CC.translate("&a&lLEFT-CLICK &ato create 1 mine"),
                CC.translate("&e&lMIDDLE-CLICK &eto create x mines"),
                CC.translate("&b&lSHIFT + LEFT-CLICK&b to create 10 mines")
        );
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (clickType != ClickType.MIDDLE) {
            grid.copySchematics(type, clickType.isShiftClick() ? 10 : 1, createdMines ->
                    player.sendMessage(CC.format("&fSuccessfully generated &b%s&f mines.", createdMines)));
            return;
        }

        GridAmountPrompt gridAmountPrompt = new GridAmountPrompt(grid, type, integer ->
                player.sendMessage(CC.format("&fSuccessfully generated &b%s&f mines.", integer)));

        ConversationFactory factory = new ConversationFactory(PrisonPlugin.get())
                .withModality(true)
                .withPrefix(new NullConversationPrefix())
                .withFirstPrompt(gridAmountPrompt).withEscapeSequence("/no")
                .withLocalEcho(false).withTimeout(10)
                .thatExcludesNonPlayersWithMessage("Go away evil console!");

        player.closeInventory();
        player.beginConversation(factory.buildConversation(player));
    }

}
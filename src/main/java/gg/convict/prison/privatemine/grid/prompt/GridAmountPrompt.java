package gg.convict.prison.privatemine.grid.prompt;

import gg.convict.prison.privatemine.grid.MineGrid;
import gg.convict.prison.privatemine.grid.SchematicType;
import gg.convict.prison.privatemine.grid.menu.GridAdminMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;
import gg.convict.prison.util.CC;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class GridAmountPrompt extends StringPrompt {

    private final MineGrid grid;
    private final SchematicType type;
    private final Consumer<Integer> consumer;

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return CC.GREEN + "Enter the amount of mines: (type \"exit\" to cancel the process)";
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        if (input.equalsIgnoreCase("exit")) {
            context.getForWhom().sendRawMessage(CC.RED + "You have cancelled the grid process.");
            return Prompt.END_OF_CONVERSATION;
        }

        try {
            int i = Integer.parseInt(input);

            if (i < 1 || i > 300) {
                context.getForWhom().sendRawMessage(CC.RED + "You must enter a number between 1 and 300.");
                return END_OF_CONVERSATION;
            }

            grid.copySchematics(type, i, consumer);
            GridAdminMenu.INSTANCE.openMenu((Player) context.getForWhom());
        } catch (Exception exception) {
            context.getForWhom().sendRawMessage(CC.format(
                    "&cAn error occurred whilst creating mines: %s.",
                    exception.getMessage()
            ));
        }

        return END_OF_CONVERSATION;
    }

}
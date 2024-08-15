package gg.convict.prison.util.command.bukkit;

import gg.convict.prison.PrisonPlugin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import gg.convict.prison.util.command.CommandNode;
import gg.convict.prison.util.command.CommandService;
import gg.convict.prison.util.command.annotation.Flag;
import gg.convict.prison.util.command.data.FlagData;
import gg.convict.prison.util.command.data.ParameterData;
import gg.convict.prison.util.command.permission.PermissionAdapter;
import gg.convict.prison.util.CC;
import org.spigotmc.SpigotConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 03.06.2020 / 10:10
 * libraries / cc.invictusgames.libraries.commandapi.bukkit
 */

@RequiredArgsConstructor
public class BukkitCommandNode implements CommandExecutor, TabCompleter {

    @Getter
    private final CommandNode node;
    private final JavaPlugin plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        label = label.replace(plugin.getName().toLowerCase() + ":", "");

        List<String> arguments = new ArrayList<>();
        List<String> flags = new ArrayList<>();

        for (String s : args) {
            if (!s.isEmpty()) {
                if ((s.charAt(0) == '-')
                        && (!s.equals("-")
                        && !s.equals("--"))
                        && (Flag.FLAG_PATTERN.matcher(s).matches())) {
                    String flag = s.replaceFirst("-", "");
                    flags.add(flag);
                } else arguments.add(s);
            }
        }

        CommandNode executionNode = node.findNode(arguments);
        String realLabel = (sender instanceof Player ? "/" : "") + executionNode.getFullLabel();

        flags.removeIf(flag -> !executionNode.getFlags().contains(flag));

        if (executionNode.getPermission() != null) {
            PermissionAdapter adapter = CommandService.getPermissionAdapter(executionNode.getPermission());
            if (adapter != null && !executionNode.isHidden() && !adapter.test(sender))
                return true;
        }

        if (!executionNode.canUse(sender)) {
            if (executionNode.isHidden()) {
                sender.sendMessage(SpigotConfig.unknownCommandMessage);
                return true;
            }

            sender.sendMessage(CommandService.NO_PERMISSION_MESSAGE);
            return true;
        }

        if (executionNode.isAsync()) {
            PrisonPlugin.EXECUTOR.execute(() -> {
                try {
                    if (!executionNode.invoke(sender, arguments, flags)) {
                        executionNode.getUsage(realLabel).send(sender);
                    }
                } catch (Exception e) {
                    if (sender.isOp()) {
                        sender.sendMessage(CC.RED + "An error (" + e.getClass().getSimpleName() + ": " + e.getMessage() + ")"
                                + " occurred while executing your command. ");
                    } else {
                        sender.sendMessage(CC.RED + "An error occurred while executing your command. " +
                                "Please contact the server administration if this continues to happen.");
                    }
                    e.printStackTrace();
                }
            });
        } else {
            try {
                if (!executionNode.invoke(sender, arguments, flags)) {
                    executionNode.getUsage(realLabel).send(sender);
                }
            } catch (Exception e) {
                if (sender.isOp()) {
                    sender.sendMessage(CC.RED + "An error (" + e.getClass().getSimpleName() + ": " + e.getMessage() + ")"
                            + " occurred while executing your command. ");
                } else {
                    sender.sendMessage(CC.RED + "An error occurred while executing your command. " +
                            "Please contact the server administration if this continues to happen.");
                }
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!node.canUse(sender)) {
            return new ArrayList<>();
        }
        List<String> arguments = new ArrayList<>();

        for (String s : args) {
            if (!s.isEmpty()) {
                if ((s.charAt(0) == '-')
                        && (!s.equals("-")
                        && !s.equals("--"))
                        && (Flag.FLAG_PATTERN.matcher(s).matches())) {
                    String flag = s.replaceFirst("-", "");
                    if (!node.getFlags().contains(flag))
                        arguments.add(s);
                } else {
                    arguments.add(s);
                }
            }
        }

        List<String> completions = new ArrayList<>();
        CommandNode tabbingNode = node.findNode(arguments);
        int offset = tabbingNode.getFullLabel().split(" ").length - 1;

        if (!tabbingNode.getChilds().isEmpty()) {
            tabbingNode.getChilds().forEach(child -> {
                if (child.canUse(sender) && !child.isHidden()) {
                    completions.add(child.getLabel());
                    completions.addAll(child.getAliases());
                }
            });
        }

        List<ParameterData> parameters = new ArrayList<>();
        tabbingNode.getParameters().forEach(data -> {
            if (data instanceof ParameterData) {
                parameters.add((ParameterData) data);
            }
        });

        List<FlagData> flagList = new ArrayList<>();
        tabbingNode.getParameters().forEach(data -> {
            if (data instanceof FlagData)
                flagList.add((FlagData) data);
        });

        List<String> unusedArguments = new ArrayList<>();
        for (String arg : args) {
            if (!tabbingNode.getFullLabel().contains(arg)) {
                unusedArguments.add(arg);
            }
        }

        if (!parameters.isEmpty()) {
            /*int index = parameters.size() - unusedArguments.size() - 1;
            if (index >= 0) {
                completions.addAll(parameters.get(index).getParameterType().tabComplete(sender));
            }*/
            int index = Math.max(0, args.length - 1) - offset;
            if (index >= 0 && index < parameters.size()) {
                ParameterData parameterData = parameters.get(index);
                completions.addAll(parameterData.getParameterType().tabComplete(sender,
                        parameterData.getCompletionFlags()));
            }
        }

        if (!flagList.isEmpty())
            flagList.forEach(flag -> flag.getNames().forEach(s -> completions.add("-" + s)));

        return getCompletions(args, completions);
    }

    private List<String> getCompletions(String[] args, List<String> input) {
        List<String> result = new ArrayList<>();
        String argument = args[args.length - 1];
        for (String string : input)
            if (string.regionMatches(true, 0, argument,
                    0, argument.length()) && result.size() < 80)
                result.add(string);

        return result;
    }
}

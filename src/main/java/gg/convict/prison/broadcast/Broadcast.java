package gg.convict.prison.broadcast;

import gg.convict.prison.util.CC;
import lombok.Data;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Broadcast {

    private final List<String> messages = new ArrayList<>();

    public Broadcast(String... messages) {
        this.messages.addAll(Arrays.asList(messages));
    }

    public void broadcast() {
        Bukkit.broadcastMessage("");
        messages.forEach(s -> Bukkit.broadcastMessage(CC.translate(s)));
        Bukkit.broadcastMessage("");
    }

}
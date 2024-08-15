package gg.convict.prison.broadcast;

import lombok.Getter;
import gg.convict.prison.util.configuration.StaticConfiguration;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BroadcastConfig implements StaticConfiguration {

    private transient int index = 0;
    private final int broadcastDelayTicks = 1200;

    private final List<Broadcast> broadcasts = new ArrayList<Broadcast>() {{
        add(new Broadcast(
                "Test 1",
                "Test 1 Line 2"
        ));

        add(new Broadcast("Test 2"));
    }};

    public Broadcast getNextBroadcast() {
        if (index >= broadcasts.size()) {
            index = 0;
            return broadcasts.get(index);
        }

        return broadcasts.get(index++);
    }

}
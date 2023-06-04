package gg.convict.prison.broadcast.runnable;

import gg.convict.prison.broadcast.BroadcastModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BroadcastRunnable implements Runnable {

    private final BroadcastModule module;

    @Override
    public void run() {
        module.getConfig().getNextBroadcast().broadcast();
    }

}
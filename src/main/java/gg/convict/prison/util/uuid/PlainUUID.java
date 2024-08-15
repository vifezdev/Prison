package gg.convict.prison.util.uuid;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class PlainUUID {

    private final UUID uuid;

    public boolean equals(UUID uuid) {
        return this.uuid.equals(uuid);
    }

    public UUID asUuid() {
        return uuid;
    }

}
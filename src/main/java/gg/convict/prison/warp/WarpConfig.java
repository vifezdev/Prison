package gg.convict.prison.warp;

import lombok.Getter;
import org.hydrapvp.libraries.configuration.StaticConfiguration;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WarpConfig implements StaticConfiguration {

    private final List<Warp> warps = new ArrayList<>();

}
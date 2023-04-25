package gg.convict.prison.privatemine.util;

import org.bukkit.block.BlockFace;

public class AngleUtil {

    public static int parseAngle(int x) {
        if (x > 360)
            return 360;

        if (x > 270)
            return 270;

        if (x > 180)
            return 180;

        return 90;
    }

    public static int parseFace(BlockFace face) {
        switch (face) {
            case NORTH:
            case NORTH_NORTH_WEST:
            case NORTH_NORTH_EAST:
                return 180;
            case EAST:
            case EAST_NORTH_EAST:
            case EAST_SOUTH_EAST:
                return -90;
            case SOUTH:
            case SOUTH_SOUTH_EAST:
            case SOUTH_SOUTH_WEST:
                return 0;
            case WEST:
            case WEST_NORTH_WEST:
            case WEST_SOUTH_WEST:
                return 90;
            case NORTH_EAST:
                return -145;
            case NORTH_WEST:
                return 145;
            case SOUTH_EAST:
                return -45;
            case SOUTH_WEST:
                return 45;
        }

        return -180;
    }

}
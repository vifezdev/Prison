/*
 * Copyright (c) 2020. Joel Evans
 *
 * Use and or redistribution of compiled JAR file and or source code is permitted only if given
 * explicit permission from original author: Joel Evans
 */

package lite

import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld
import org.hydrapvp.libraries.cuboid.Cuboid

class LiteRegion(
    val world: CraftWorld,
    val minX: Int,
    val minY: Int,
    val minZ: Int,
    val maxX: Int,
    val maxY: Int,
    val maxZ: Int
) {

    constructor(cuboid: Cuboid) : this(
        cuboid.world as CraftWorld,
        cuboid.minimumX,
        cuboid.minimumPoint.blockY,
        cuboid.minimumZ,
        cuboid.maximumX,
        cuboid.maximumPoint.blockY,
        cuboid.maximumZ
    )

    operator fun contains(loc: Location): Boolean {
        return (loc.world === world && minX <= loc.x && loc.x <= maxX
                && minY <= loc.y && loc.y <= maxY
                && minZ <= loc.z && loc.z <= maxZ)
    }

    override fun toString(): String {
        return "Region{" +
                "world=" + world +
                ", minX=" + minX +
                ", minY=" + minY +
                ", minZ=" + minZ +
                ", maxX=" + maxX +
                ", maxY=" + maxY +
                ", maxZ=" + maxZ +
                '}'
    }

}
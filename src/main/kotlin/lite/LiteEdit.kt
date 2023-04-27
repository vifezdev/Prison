/*
 * Copyright (c) 2020. Joel Evans
 *
 * Use and or redistribution of compiled JAR file and or source code is permitted only if given
 * explicit permission from original author: Joel Evans
 */

package lite

import net.minecraft.server.v1_8_R3.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_8_R3.CraftChunk
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.hydrapvp.libraries.lite.util.LiteTask
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

object LiteEdit {

    private val executor: Executor = Executors.newSingleThreadExecutor()

    @JvmStatic
    fun fill(region: LiteRegion, material: Material, data: Int) {
        if (Bukkit.isPrimaryThread()) {
            fill(region, getData(material, data))
        } else {
            LiteTask.runSync { fill(region, getData(material, data)) }
        }
    }

    @JvmStatic
    fun countAir(region: LiteRegion, callback: CountAirCallback) {
        if (Bukkit.isPrimaryThread()) {
            countAirSync(region, callback)
        } else {
            LiteTask.runSync { countAirSync(region, callback) }
        }
    }

    private fun fill(region: LiteRegion, data: IBlockData) {
        val minChunkX: Int = region.minX shr 4
        val minChunkZ: Int = region.minZ shr 4
        val maxChunkX: Int = region.maxX shr 4
        val maxChunkZ: Int = region.maxZ shr 4
        val world: CraftWorld = region.world

        LiteTask.runSync {
            val chunks: ArrayList<Chunk> = ArrayList(64)
            for (x in minChunkX..maxChunkX) {
                for (z in minChunkZ..maxChunkZ) {
                    val chunk = getOrLoadChunkBukkit(world, x, z)
                    if (chunk != null) {
                        chunks.add(chunk)
                    }
                }
            }

            LiteTask.runAsync {
                for (chunk in chunks) {
                    executor.execute {
                        try {
                            val mask = fill(chunk, region, data)
                            chunk.mustSave = true

                            val chunkCoord = chunk.j() // chunk.k() on 1.12

                            for (player in region.world.players) {
                                if (!isChunkInViewDistance(player, chunkCoord)) {
                                    continue
                                }

                                (player as CraftPlayer).handle.playerConnection.sendPacket(
                                    PacketPlayOutMapChunk(
                                        chunk,
                                        true,
                                        mask
                                    )
                                )
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    private fun fill(chunk: Chunk, region: LiteRegion, data: IBlockData): Int {
        val sections: Array<ChunkSection?> = chunk.sections
        var mask = 0
        val minX: Int = 0.coerceAtLeast(region.minX - chunk.locX * 16)
        val maxX: Int = 15.coerceAtMost(region.maxX - chunk.locX * 16)
        val minZ: Int = 0.coerceAtLeast(region.minZ - chunk.locZ * 16)
        val maxZ: Int = 15.coerceAtMost(region.maxZ - chunk.locZ * 16)

        for (i in sections.indices.reversed()) {
            mask = mask shl 1

            if (region.minY shr 4 > i) {
                continue
            }

            if (region.maxY shr 4 < i) {
                continue
            }

            mask += 1

            val minY = 0.coerceAtLeast(region.minY - i * 16)
            val maxY = 15.coerceAtMost(region.maxY - i * 16)

            for (x in minX..maxX) {
                for (z in minZ..maxZ) {
                    for (y in minY..maxY) {
                        if (sections[i] == null) {
                            for (i2 in sections.indices) {
                                if (sections[i2] == null) {
                                    sections[i2] =
                                        ChunkSection(i2, chunk.world.worldProvider.e()) // worldProvider.m() on 1.12
                                }
                            }
                        }
                        sections[i]!!.setType(x, y, z, data)
                    }
                }
            }
        }

        return mask
    }

    @JvmStatic
    fun fill(region: LiteRegion, filter: FillHandler, callback: ProgressCallback?) {
        val progress = callback ?: VoidProgressCallBack
        val minChunkX: Int = region.minX shr 4
        val minChunkZ: Int = region.minZ shr 4
        val maxChunkX: Int = region.maxX shr 4
        val maxChunkZ: Int = region.maxZ shr 4
        val world: CraftWorld = region.world

        LiteTask.runSync {
            val chunks: ArrayList<Chunk> = ArrayList(64)
            for (x in minChunkX..maxChunkX) {
                for (z in minChunkZ..maxChunkZ) {
                    val chunk = getOrLoadChunkBukkit(world, x, z)
                    if (chunk != null) {
                        chunks.add(chunk)
                    }
                }
            }

            LiteTask.runAsync {
                executor.execute {
                    val size: Int = chunks.size

                    progress.start(size)

                    for ((i, chunk) in chunks.withIndex()) {
                        progress.progress(i + 1, size)

                        try {
                            val mask = fill(chunk, region, filter)
                            chunk.mustSave = true

                            val chunkCoord = chunk.j() // chunk.k() on 1.12

                            for (player in region.world.players) {
                                if (!isChunkInViewDistance(player, chunkCoord)) {
                                    continue
                                }

                                (player as CraftPlayer).handle.playerConnection.sendPacket(
                                    PacketPlayOutMapChunk(
                                        chunk,
                                        true,
                                        mask
                                    )
                                )
                            }
                        } catch (e: Throwable) {
                            e.printStackTrace()
                        }
                    }

                    progress.end(size)
                }
            }
        }
    }

    private fun fill(chunk: Chunk, region: LiteRegion, data: FillHandler): Int {
        val ox: Int = chunk.locX * 16
        val oz: Int = chunk.locZ * 16

        var mask = 0

        val sections: Array<ChunkSection?> = chunk.sections
        for (i in sections.indices.reversed()) {
            val oy = i * 16

            mask = mask shl 1
            mask += 1

            for (x in 0..15) {
                for (z in 0..15) {
                    for (y in 0..15) {
                        if (sections[i] == null) {
                            sections[i] = ChunkSection(i, chunk.world.worldProvider.e()) // worldProvider.m() on 1.12
                        }

                        if (oy + y <= 2) {
                            sections[i]!!.setType(x, y, z, getData(Material.BEDROCK, 0))
                            continue
                        }

                        if ((ox + x) in region.minX..region.maxX && (oy + y) in region.minY..region.maxY && (oz + z) in region.minZ..region.maxZ) {
                            val ibd: IBlockData? = data.getBlock(ox + x, oy + y, oz + z)
                            if (ibd != null) {
                                sections[i]!!.setType(x, y, z, ibd)
                            } else {
                                sections[i]!!.setType(
                                    x,
                                    y,
                                    z,
                                    chunk.getBlockData(BlockPosition(ox + x, oy + y, oz + z))
                                )
                            }
                        } else {
                            sections[i]!!.setType(x, y, z, chunk.getBlockData(BlockPosition(ox + x, oy + y, oz + z)))
                        }
                    }
                }
            }
        }

        return mask
    }

    private fun countAirSync(region: LiteRegion, callback: CountAirCallback) {
        val minCX: Int = region.minX shr 4
        val minCZ: Int = region.minZ shr 4
        val maxCX: Int = region.maxX shr 4
        val maxCZ: Int = region.maxZ shr 4
        val world: CraftWorld = region.world

        val count = AtomicInteger(0)
        val skipped = AtomicInteger(0)
        val total = AtomicInteger(0)

        val chunks: ArrayList<Chunk> = ArrayList(64)
        for (x in minCX..maxCX) {
            for (z in minCZ..maxCZ) {
                val chunk: Chunk? = getChunkIfLoaded(world, x, z)
                if (chunk != null) {
                    chunks.add(chunk)
                } else {
                    skipped.addAndGet(65536)
                }
            }
        }

        executor.execute {
            for (chunk in chunks) {
                try {
                    countAir(chunk, region, count, total)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            LiteTask.runSync { callback.callback(total.get(), count.get(), skipped.get()) }
        }
    }

    private fun countAir(chunk: Chunk, region: LiteRegion, count: AtomicInteger, total: AtomicInteger) {
        val minX: Int = 0.coerceAtLeast(region.minX - (chunk.locX * 16))
        val maxX: Int = 15.coerceAtMost(region.maxX - (chunk.locX * 16))
        val minZ: Int = 0.coerceAtLeast(region.minZ - (chunk.locZ * 16))
        val maxZ: Int = 15.coerceAtMost(region.maxZ - (chunk.locZ * 16))

        val sections: Array<ChunkSection?> = chunk.sections
        for (i in sections.indices.reversed()) {
            if (region.minY shr 4 > i) {
                continue
            }

            if (region.maxY shr 4 < i) {
                continue
            }

            val minY: Int = 0.coerceAtLeast(region.minY - (i * 16))
            val maxY: Int = 15.coerceAtMost(region.maxY - (i * 16))

            total.addAndGet((maxX - minX + 1) * (maxY - minY + 1) * (maxZ - minZ + 1))

            if (sections[i] == null) {
                count.addAndGet((maxX - minX + 1) * (maxY - minY + 1) * (maxZ - minZ + 1))
                continue
            }

            for (x in minX..maxX) {
                for (z in minZ..maxZ) {
                    for (y in minY..maxY) {
                        val data = sections[i]!!.getType(x, y, z)
                        if (data == null) {
                            count.incrementAndGet()
                        } else if (data.block === Blocks.AIR) {
                            count.incrementAndGet()
                        }
                    }
                }
            }
        }
    }

    @JvmStatic
    fun isAir(world: CraftWorld, x: Int, y: Int, z: Int): Boolean {
        val chunkX = x shr 4
        val chunkY = y shr 4
        val chunkZ = z shr 4

        if (chunkY < 0 || chunkY > 15) {
            return true
        }

        val chunk: Chunk = getChunkIfLoaded(world, chunkX, chunkZ)!!
        val sections: Array<ChunkSection?> = chunk.sections
        if (sections[chunkY] == null) {
            return true
        }

        val data: IBlockData = sections[chunkY]!!.getType(x and 15, y and 15, z and 15) ?: return true
        return data.block === Blocks.AIR
    }

    private fun getChunkIfLoaded(world: CraftWorld, x: Int, z: Int): Chunk? {
        return world.handle.getChunkIfLoaded(x, z)
    }

    private fun getOrLoadChunkBukkit(world: CraftWorld, x: Int, z: Int): Chunk? {
        var chunk: org.bukkit.Chunk? = world.getChunkAt(x, z)
        if (chunk == null) {
            world.loadChunk(x, z)
            chunk = world.getChunkAt(x, z)
        }

        if (chunk != null && !chunk.isLoaded) {
            chunk.load(false)

            if (chunk.isLoaded) {
                (chunk as CraftChunk).handle.mustSave = true
            }
        }

        // load neighbors for some stupid fucking reason
        if (chunk != null) {
            for (chunkX in (chunk.x - 4)..(chunk.x + 4)) {
                for (chunkZ in (chunk.z - 4)..(chunk.z + 4)) {
                    val neighbor = world.getChunkAt(chunkX, chunkZ)
                    if (!neighbor.isLoaded) {
                        neighbor.load(false)
                    }
                }
            }
        }

        return (chunk as CraftChunk?)?.handle
    }

    private fun isChunkInViewDistance(player: Player, chunk: ChunkCoordIntPair): Boolean {
        val viewDistanceChunks = Bukkit.getServer().viewDistance
        return player.location.chunk.x in (chunk.x - viewDistanceChunks - 2)..(chunk.x + viewDistanceChunks + 2)
                || player.location.chunk.z in (chunk.z - viewDistanceChunks - 2)..(chunk.z + viewDistanceChunks + 2)
    }

    fun getData(material: Material, data: Int): IBlockData {
        return Block.REGISTRY.a(material.id).fromLegacyData(data)
    }

    @JvmStatic
    val VoidProgressCallBack: ProgressCallback = object : ProgressCallback {
        override fun start(totalChunks: Int) {}
        override fun end(totalChunks: Int) {}
        override fun progress(chunks: Int, totalChunks: Int) {}
    }

    interface FillHandler {
        fun getBlock(x: Int, y: Int, z: Int): IBlockData?
        fun getData(material: Material, data: Int): IBlockData {
            return LiteEdit.getData(material, data)
        }
    }

    interface ProgressCallback {
        fun start(totalChunks: Int)
        fun progress(chunks: Int, totalChunks: Int)
        fun end(totalCHunks: Int)
    }

    interface CountAirCallback {
        fun callback(total: Int, air: Int, skipped: Int)
    }

}
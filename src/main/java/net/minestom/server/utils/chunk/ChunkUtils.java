package net.minestom.server.utils.chunk;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.Instance;
import net.minestom.server.utils.BlockPosition;
import net.minestom.server.utils.MathUtils;
import net.minestom.server.utils.Position;

public final class ChunkUtils {

    private ChunkUtils() {

    }

    /**
     * Get if a chunk is loaded
     *
     * @param chunk the chunk to check
     * @return true if the chunk is loaded, false otherwise
     */
    public static boolean isLoaded(Chunk chunk) {
        return chunk != null && chunk.isLoaded();
    }

    /**
     * Get if a chunk is loaded
     *
     * @param instance the instance to check
     * @param x        instance X coordinate
     * @param z        instance Z coordinate
     * @return true if the chunk is loaded, false otherwise
     */
    public static boolean isLoaded(Instance instance, float x, float z) {
        final int chunkX = getChunkCoordinate((int) x);
        final int chunkZ = getChunkCoordinate((int) z);

        final Chunk chunk = instance.getChunk(chunkX, chunkZ);
        return isLoaded(chunk);
    }

    /**
     * @param xz the instance coordinate to convert
     * @return the chunk X or Z based on the argument
     */
    public static int getChunkCoordinate(int xz) {
        // Assume Chunk.CHUNK_SIZE_X == Chunk.CHUNK_SIZE_Z
        return Math.floorDiv(xz, Chunk.CHUNK_SIZE_X);
    }

    /**
     * Get the chunk index of chunk coordinates
     *
     * @param chunkX the chunk X
     * @param chunkZ the chunk Z
     * @return a number storing the chunk X and Z
     */
    public static long getChunkIndex(int chunkX, int chunkZ) {
        return (((long) chunkX) << 32) | (chunkZ & 0xffffffffL);
    }

    /**
     * @param index the chunk index computed by {@link #getChunkIndex(int, int)}
     * @return an array containing both the chunk X and Z (index 0 = X; index 1 = Z)
     */
    public static int[] getChunkCoord(long index) {
        final int chunkX = (int) (index >> 32);
        final int chunkZ = (int) index;
        return new int[]{chunkX, chunkZ};
    }

    public static int getSectionAt(int y) {
        return y / Chunk.CHUNK_SECTION_SIZE;
    }

    /**
     * Get the chunks in range of a position
     *
     * @param position the initial position
     * @param range    how far should it retrieves chunk
     * @return an array containing chunks index which can be converted using {@link #getChunkCoord(long)}
     */
    public static long[] getChunksInRange(final Position position, int range) {
        long[] visibleChunks = new long[MathUtils.square(range + 1)];
        final int startLoop = -(range / 2);
        final int endLoop = range / 2 + 1;
        int counter = 0;
        for (int x = startLoop; x < endLoop; x++) {
            for (int z = startLoop; z < endLoop; z++) {
                final int chunkX = getChunkCoordinate((int) (position.getX() + Chunk.CHUNK_SIZE_X * x));
                final int chunkZ = getChunkCoordinate((int) (position.getZ() + Chunk.CHUNK_SIZE_Z * z));
                visibleChunks[counter] = getChunkIndex(chunkX, chunkZ);
                counter++;
            }
        }
        return visibleChunks;
    }

    /**
     * Get all the loaded neighbours of a chunk and itself, no diagonals
     *
     * @param instance the instance of the chunks
     * @param chunkX   the chunk X
     * @param chunkZ   the chunk Z
     * @return an array containing all the loaded neighbours
     * can be deserialized using {@link #indexToChunkPosition(int)}
     */
    public static long[] getNeighbours(Instance instance, int chunkX, int chunkZ) {
        LongList chunks = new LongArrayList();
        // Constants used to loop through the neighbors
        final int[] posX = {1, 0, -1};
        final int[] posZ = {1, 0, -1};

        for (int x : posX) {
            for (int z : posZ) {

                // No diagonal check
                if ((Math.abs(x) + Math.abs(z)) == 2)
                    continue;

                final int targetX = chunkX + x;
                final int targetZ = chunkZ + z;
                final Chunk chunk = instance.getChunk(targetX, targetZ);
                if (ChunkUtils.isLoaded(chunk)) {
                    // Chunk is loaded, add it
                    final long index = getChunkIndex(targetX, targetZ);
                    chunks.add(index);
                }

            }
        }
        return chunks.toArray(new long[0]);
    }

    /**
     * Get the block index of a position
     *
     * @param x the block X
     * @param y the block Y
     * @param z the block Z
     * @return an index which can be used to store and retrieve later data linked to a block position
     */
    public static int getBlockIndex(int x, int y, int z) {
        x = x % Chunk.CHUNK_SIZE_X;
        z = z % Chunk.CHUNK_SIZE_Z;

        short index = (short) (x & 0x000F);
        index |= (y << 4) & 0x0FF0;
        index |= (z << 12) & 0xF000;
        return index & 0xffff;
    }

    /**
     * @param index  an index computed from {@link #getBlockIndex(int, int, int)}
     * @param chunkX the chunk X
     * @param chunkZ the chunk Z
     * @return the instance position of the block located in {@code index}
     */
    public static BlockPosition getBlockPosition(int index, int chunkX, int chunkZ) {
        final int[] pos = indexToPosition(index, chunkX, chunkZ);
        return new BlockPosition(pos[0], pos[1], pos[2]);
    }

    /**
     * @param index  an index computed from {@link #getBlockIndex(int, int, int)}
     * @param chunkX the chunk X
     * @param chunkZ the chunk Z
     * @return the world position of the specified index with its chunks being {@code chunkX} and {@code chunk Z}
     * positions in the array are in the order X/Y/Z
     */
    public static int[] indexToPosition(int index, int chunkX, int chunkZ) {
        int z = (byte) (index >> 12 & 0xF);
        final int y = (index >>> 4 & 0xFF);
        // index >> 0 = index
        int x = (byte) (index & 0xF);

        x += 16 * chunkX;
        z += 16 * chunkZ;

        return new int[]{x, y, z};
    }

    /**
     * @param index an index computed from {@link #getBlockIndex(int, int, int)}
     * @return the chunk position (O-15) of the specified index,
     * positions in the array are in the order X/Y/Z
     */
    public static int[] indexToChunkPosition(int index) {
        return indexToPosition(index, 0, 0);
    }
}

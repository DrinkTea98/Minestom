package net.minestom.server.instance.block.states;
import net.minestom.server.instance.block.BlockAlternative;
import static net.minestom.server.instance.block.Block.*;
/**
 * Completely internal. DO NOT USE. IF YOU ARE A USER AND FACE A PROBLEM WHILE USING THIS CODE, THAT'S ON YOU.
 */
@Deprecated(forRemoval = false, since = "forever")
public class BrickSlab {
	public static void initStates() {
		BRICK_SLAB.addBlockAlternative(new BlockAlternative((short) 8372, "type=top", "waterlogged=true"));
		BRICK_SLAB.addBlockAlternative(new BlockAlternative((short) 8373, "type=top", "waterlogged=false"));
		BRICK_SLAB.addBlockAlternative(new BlockAlternative((short) 8374, "type=bottom", "waterlogged=true"));
		BRICK_SLAB.addBlockAlternative(new BlockAlternative((short) 8375, "type=bottom", "waterlogged=false"));
		BRICK_SLAB.addBlockAlternative(new BlockAlternative((short) 8376, "type=double", "waterlogged=true"));
		BRICK_SLAB.addBlockAlternative(new BlockAlternative((short) 8377, "type=double", "waterlogged=false"));
	}
}

package net.minestom.server.instance.block.states;
import net.minestom.server.instance.block.BlockAlternative;
import static net.minestom.server.instance.block.Block.*;
/**
 * Completely internal. DO NOT USE. IF YOU ARE A USER AND FACE A PROBLEM WHILE USING THIS CODE, THAT'S ON YOU.
 */
@Deprecated(forRemoval = false, since = "forever")
public class SmoothRedSandstoneSlab {
	public static void initStates() {
		SMOOTH_RED_SANDSTONE_SLAB.addBlockAlternative(new BlockAlternative((short) 10795, "type=top", "waterlogged=true"));
		SMOOTH_RED_SANDSTONE_SLAB.addBlockAlternative(new BlockAlternative((short) 10796, "type=top", "waterlogged=false"));
		SMOOTH_RED_SANDSTONE_SLAB.addBlockAlternative(new BlockAlternative((short) 10797, "type=bottom", "waterlogged=true"));
		SMOOTH_RED_SANDSTONE_SLAB.addBlockAlternative(new BlockAlternative((short) 10798, "type=bottom", "waterlogged=false"));
		SMOOTH_RED_SANDSTONE_SLAB.addBlockAlternative(new BlockAlternative((short) 10799, "type=double", "waterlogged=true"));
		SMOOTH_RED_SANDSTONE_SLAB.addBlockAlternative(new BlockAlternative((short) 10800, "type=double", "waterlogged=false"));
	}
}

package net.minestom.instance.block.states;
import net.minestom.server.instance.block.BlockAlternative;
import static net.minestom.instance.block.TmpBlock.*;
/**
 * Completely internal. DO NOT USE. IF YOU ARE A USER AND FACE A PROBLEM WHILE USING THIS CODE, THAT'S ON YOU.
 */
@Deprecated(forRemoval = false, since = "forever")
public class JungleLog {
	public static void initStates() {
		JUNGLE_LOG.addBlockAlternative(new BlockAlternative((short) 81, "axis=x"));
		JUNGLE_LOG.addBlockAlternative(new BlockAlternative((short) 82, "axis=y"));
		JUNGLE_LOG.addBlockAlternative(new BlockAlternative((short) 83, "axis=z"));
	}
}
package net.minestom.instance.block.states;
import net.minestom.server.instance.block.BlockAlternative;
import static net.minestom.instance.block.TmpBlock.*;
/**
 * Completely internal. DO NOT USE. IF YOU ARE A USER AND FACE A PROBLEM WHILE USING THIS CODE, THAT'S ON YOU.
 */
@Deprecated(forRemoval = false, since = "forever")
public class DarkOakPressurePlate {
	public static void initStates() {
		DARK_OAK_PRESSURE_PLATE.addBlockAlternative(new BlockAlternative((short) 3881, "powered=true"));
		DARK_OAK_PRESSURE_PLATE.addBlockAlternative(new BlockAlternative((short) 3882, "powered=false"));
	}
}
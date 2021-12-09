package knockout;

import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;

public class KnockoutUtils_v1_8 {

	@SuppressWarnings("deprecation")
	public void setBlockData(Block b, FallingBlock e) {
		
		b.getLocation().getBlock().setData(e.getBlockData());
		
	}
	
}

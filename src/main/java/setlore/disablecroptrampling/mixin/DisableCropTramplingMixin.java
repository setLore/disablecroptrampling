package setlore.disablecroptrampling.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Setting priority to 500 ensures this runs before other mods or vanilla logic
@Mixin(value = FarmlandBlock.class, priority = 500)
public class DisableCropTramplingMixin {

    @Inject(
        method = "onLandedUpon", 
        at = @At("HEAD"),
        cancellable = true
    )
    private void stopTrampling(World level, BlockState state, BlockPos pos, Entity entity, double fallDistance, CallbackInfo info) {
        // System.out.println("DEBUG: Mixin is firing! Entity " + entity.getName().getString() + " landed on crops."); // Uncomment for debugging when an entity lands on farmland
        entity.handleFallDamage((float)fallDistance, 1.0F, level.getDamageSources().fall());
        
        info.cancel();
    }
}
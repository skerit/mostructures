package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.MoStructures;
import io.github.frqnny.mostructures.util.StrucUtils;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.PillagerOutpostFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseChunkGenerator.class)
public class MixinNoiseChunkGenerator {

    @Inject(at = @At("HEAD"), method = "getEntitySpawnList", cancellable = true)
    public void injectSpawnList(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos, CallbackInfoReturnable<Pool<SpawnSettings.SpawnEntry>> info) {
        if (group == SpawnGroup.MONSTER) {
            if (accessor.getStructureAt(pos, MoStructures.PILLAGER_FACTORY).hasChildren()) {
                info.setReturnValue(PillagerOutpostFeature.MONSTER_SPAWNS);
            } else if (accessor.getStructureAt(pos, MoStructures.ICE_TOWER).hasChildren()) {
                info.setReturnValue(StrucUtils.ICE_TOWER_SPAWNS);
            } else if (accessor.getStructureAt(pos, MoStructures.ABANDONED_CHURCH).hasChildren()) {
                info.setReturnValue(StrucUtils.ABANDONED_CHURCH_SPAWNS);
            }
        }
    }
}

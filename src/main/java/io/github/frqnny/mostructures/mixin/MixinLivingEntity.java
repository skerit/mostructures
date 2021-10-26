package io.github.frqnny.mostructures.mixin;

import io.github.frqnny.mostructures.util.ItemUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RabbitEntity.class)
public abstract class MixinLivingEntity extends AnimalEntity {


    @Shadow public abstract int getRabbitType();

    protected MixinLivingEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    //@Inject(method = "onDeath", at = @At("TAIL"))
    public void onDeath(DamageSource source) {

        if (this.getRabbitType() == 99) {

            ItemStack goldIngots = new ItemStack(Items.GOLDEN_CARROT);
            goldIngots.setCount(Math.max(8, this.getRandom().nextInt(10)));
            ItemUtils.spawnStack(this.world, this.getX(), this.getY() + 0.5, this.getZ(), goldIngots);

            return;
        }

        super.onDeath(source);
    }
}

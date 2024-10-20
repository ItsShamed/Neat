package vazkii.neat;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class EntityHelper {
    private static final List<Class<? extends Entity>> boss_classes = List.of(
            EnderDragon.class,
            WitherBoss.class,
            Warden.class,
            ElderGuardian.class
    );

    public static boolean IsBoss(Entity entity) {
        return boss_classes.stream().anyMatch(clazz -> entity.getClass().isAssignableFrom(clazz));
    }

    public static boolean IsHealthBarRenderedOn(Entity entity) {
        if (!(entity instanceof LivingEntity))
            return false;

        if (entity instanceof Player)
            return NeatConfig.instance.showOnPlayers();

        if (entity instanceof Enemy) {
            if (IsBoss(entity))
                return NeatConfig.instance.showOnBosses();
            return NeatConfig.instance.showOnHostile();
        }

        return NeatConfig.instance.showOnPassive();
    }
}

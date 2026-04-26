package net.tech.cortisolmod.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tech.cortisolmod.CortisolMod;
import net.tech.cortisolmod.cortisol.PlayerCortisolProvider;
import net.tech.cortisolmod.item.custom.CortisolSwordItem;

@Mod.EventBusSubscriber(modid = CortisolMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CortisolSwordSyncEvent {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide) return;

        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof CortisolSwordItem)) return;

        player.getCapability(PlayerCortisolProvider.PLAYER_CORTISOL).ifPresent(cap -> {

            float cortisol = cap.getCortisol();
            float old = stack.getOrCreateTag()
                    .getFloat(CortisolSwordItem.TAG_CORTISOL);

            if (old != cortisol) {
                stack.getOrCreateTag()
                        .putFloat(CortisolSwordItem.TAG_CORTISOL, cortisol);
            }
        });
    }
}
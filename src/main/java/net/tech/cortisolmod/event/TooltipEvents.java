package net.tech.cortisolmod.event;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tech.cortisolmod.item.custom.CortisolSwordItem;

import java.util.List;

@Mod.EventBusSubscriber(modid = "cortisolmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TooltipEvents {

    @SubscribeEvent
    public static void onTooltip(net.minecraftforge.event.entity.player.ItemTooltipEvent event) {

        ItemStack stack = event.getItemStack();

        if (!(stack.getItem() instanceof CortisolSwordItem)) return;

        List<Component> tooltip = event.getToolTip();

        Component itemName = tooltip.get(0);
        tooltip.clear();
        tooltip.add(itemName);

        tooltip.add(Component.translatable("tooltip.cortisolmod.sword.desc"));

        // Vérifie si l'épée est dans la main du joueur
        var player = net.minecraft.client.Minecraft.getInstance().player;
        if (player != null && player.getMainHandItem() == stack) {

            float cortisol = stack.getOrCreateTag().getFloat(CortisolSwordItem.TAG_CORTISOL);

            tooltip.add(Component.translatable("tooltip.cortisolmod.cortisol.separator"));
            tooltip.add(Component.translatable("tooltip.cortisolmod.cortisol", cortisol));
            tooltip.add(Component.translatable("tooltip.cortisolmod.damage", CortisolSwordItem.getDamageForCortisol(cortisol)));
            tooltip.add(Component.translatable("tooltip.cortisolmod.stage", CortisolSwordItem.getLevel(cortisol)));
        }
    }
}
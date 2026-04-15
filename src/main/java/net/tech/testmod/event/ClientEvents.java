package net.tech.testmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tech.testmod.TestMod;
import net.tech.testmod.client.CortisolHudOverlay;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid= TestMod.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("cortisol", CortisolHudOverlay.HUD_CORTISOL);
        }
    }

}

package net.tech.testmod.event;

import com.google.common.eventbus.Subscribe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.tech.testmod.TestMod;
import net.tech.testmod.cortisol.PlayerCortisol;
import net.tech.testmod.cortisol.PlayerCortisolProvider;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player){
           if (!event.getObject().getCapability(PlayerCortisolProvider.PLAYER_CORTISOL).isPresent()){
               event.addCapability(new ResourceLocation(TestMod.MOD_ID, "properties"),new PlayerCortisolProvider());
           }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if (event.isWasDeath()){
            event.getOriginal().getCapability(PlayerCortisolProvider.PLAYER_CORTISOL).ifPresent(oldStore -> {
                event.getEntity().getCapability(PlayerCortisolProvider.PLAYER_CORTISOL).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                    });
                });
            }
        }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(PlayerCortisol.class);
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){

        if (event.side== LogicalSide.SERVER){
            event.player.getCapability(PlayerCortisolProvider.PLAYER_CORTISOL).ifPresent(cortisol-> {
                if (cortisol.getCortisol()<100 && event.player.getRandom().nextFloat()<0.005f) {
                    cortisol.addCortisol(1);

                }

            });
        }
    }

}


package net.tech.cortisolmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.tech.cortisolmod.client.ClientCortisolData;

import java.util.function.Supplier;

public class CortisolSyncS2CPacket {
    private final float cortisol;

    public CortisolSyncS2CPacket(float cortisol){
        this.cortisol=cortisol;
    }

    public CortisolSyncS2CPacket(FriendlyByteBuf buf){
        this.cortisol=buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFloat(cortisol);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            //on the client
            ClientCortisolData.set(cortisol);


        });
        return true;
    }
}

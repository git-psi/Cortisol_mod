package net.tech.cortisolmod.client;

public class ClientCortisolData {

    private static float playerCortisol;
    public static void set(float cortisol){
        ClientCortisolData.playerCortisol = cortisol;
    }
    public static float getPlayerCortisol( ){
        return playerCortisol;
    }
}

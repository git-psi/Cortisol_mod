package net.tech.testmod.client;

public class ClientCortisolData {

    private static int playerCortisol;
    public static void set(int cortisol){
        ClientCortisolData.playerCortisol = cortisol;
    }
    public static int getPlayerCortisol( ){
        return playerCortisol;
    }
}

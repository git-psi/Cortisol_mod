package net.tech.testmod.cortisol;

import net.minecraft.nbt.CompoundTag;

public class PlayerCortisol {
    private int cortisol;
    private final int MAX_CORTISOL=100;
    private final int MIN_CORTISOL=0;

    public int getCortisol() {
        return cortisol;
    }

    public void addCortisol(int add){
        this.cortisol = Math.min(cortisol+add,MAX_CORTISOL);
    }
    public void subCortisol(int sub){
        this.cortisol = Math.max(cortisol-sub,MIN_CORTISOL);
    }

    public void copyFrom(PlayerCortisol source){
        this.cortisol = source.cortisol;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("cortisol",cortisol);
    }

    public void loadNBTData(CompoundTag nbt){
        cortisol =nbt.getInt("cortisol");
    }

}

package net.tech.cortisolmod.cortisol;

import net.minecraft.nbt.CompoundTag;

public class PlayerCortisol {
    public static final float MIN_CORTISOL = 0f;
    public static final float VISIBLE_MAX_CORTISOL = 100f;
    public static final float REAL_MAX_CORTISOL = 130f;

    private float cortisol;

    public float getCortisol() {
        return cortisol;
    }

    public void setCortisol(float value) {
        this.cortisol = Math.max(MIN_CORTISOL, Math.min(value, REAL_MAX_CORTISOL));
    }

    public void addCortisol(float add) {
        setCortisol(cortisol + add);
    }

    public void subCortisol(float sub) {
        setCortisol(cortisol - sub);
    }

    public void copyFrom(PlayerCortisol source) {
        this.cortisol = source.cortisol;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putFloat("cortisol", cortisol);
    }

    public void loadNBTData(CompoundTag nbt) {
        cortisol = nbt.getFloat("cortisol");
    }
}

package mod.encherbs.classes.util;

import net.minecraft.util.IStringSerializable;

public enum GrowthStages implements IStringSerializable {
    stage0,
    stage1,
    stage2,
    stage3,
    produce0,
    produce1,
    produce2;

    @Override
    public String getName() {
        String name = this.toString();
        if (name != null)
            return name;
        else
            return "";
    }

    public GrowthStages grow() {
        switch (this) {
            case stage0:
                return stage1;
            case stage1:
                return stage2;
            case stage2:
                return stage3;
            case stage3:
                return produce0;
            case produce0:
                return produce1;
            default:
                return produce2;
        }
    }

}

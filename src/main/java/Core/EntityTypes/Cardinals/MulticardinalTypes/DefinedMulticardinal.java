package Core.EntityTypes.Cardinals.MulticardinalTypes;

import Core.EntityTypes.DefinedEntity;
import Core.EntityTypes.Entity;
import Core.Utilities.Utils;

import java.util.ArrayList;

public abstract class DefinedMulticardinal extends DefinedEntity implements Multicardinal {
    public String name;

    public DefinedMulticardinal(String n) {
        this.name = n;
    }

    public String toString() {
        ArrayList<String> allInputs = new ArrayList<>(0);
        for (String inputType : this.getInputTypes()) {
            for (Entity ent : this.getInputs().get(inputType)) {
                allInputs.add(ent.toString());
            }
        }
        return Utils.className(this) + "(" + String.join(", ", allInputs.toArray(new String[0])) + ")";
    }

    public String getName() {
        return this.name;
    }
}
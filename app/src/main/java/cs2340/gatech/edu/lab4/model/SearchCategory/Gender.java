package cs2340.gatech.edu.lab4.model.SearchCategory;

/**
 * Created by Zumong on 3/4/18.
 */

public enum Gender {
    ALL("Anyone"),
    MALE("Men"),
    FEMALE("Women");

    private final String code;

    Gender(String pcode){
        code = pcode;
    }

    @Override
    public String toString() { return code; }
    public static Gender getEnum(String value) {
        for(Gender v : values())
            if(v.code.equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}

package cs2340.gatech.edu.lab4.model.SearchCategory;

/**
 * Created by Zumong on 3/4/18.
 */

public enum Age {
    ALL("Anyone"),
    NEWBORN("Newborns"),
    CHILDREN("Children"),
    YOUNG_ADULTS("Young Adults");

    private final String code;

    Age(String pcode){
        code = pcode;
    }

    @Override
    public String toString() { return code; }

    /**
     * use this method instead of valueOf because valueOf only takes parameters of ALL, NEWBORN, CHILDREN ...
     * But this method can take parameters of "anyone", "newborns" ...etc.
     * @param value
     * @return
     */
    public static Age getEnum(String value) {
        for(Age v : values())
            if(v.code.equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}

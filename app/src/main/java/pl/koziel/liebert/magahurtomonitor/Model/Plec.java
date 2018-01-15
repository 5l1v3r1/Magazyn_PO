package pl.koziel.liebert.magahurtomonitor.Model;

/**
 * Created by wojciech.liebert on 01.01.2018.
 */

public class Plec {
    private static final String MALE = "M";
    public static final String FEMALE = "F";

    // WHITE MALE PRIVILEGE
    private boolean isMale;

    public void setMale() {
        isMale = true;
    }

    public void setFemale() {
        isMale = false;
    }

    public String getPlec() {
        String plec = isMale ? MALE : FEMALE;
        return plec;
    }
}
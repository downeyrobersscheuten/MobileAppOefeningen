package bte.howest.nmct.studentenmodulepunten.model;

/**
 * Created by downe on 17/02/2017.
 */

public class ModulePunt {
    private String moduleNaam;
    private int aantalStudiePunten;
    private double score;

    public String getModuleNaam() {
        return moduleNaam;
    }

    public void setModuleNaam(String moduleNaam) {
        this.moduleNaam = moduleNaam;
    }

    public int getAantalStudiePunten() {
        return aantalStudiePunten;
    }

    public void setAantalStudiePunten(int aantalStudiePunten) {
        this.aantalStudiePunten = aantalStudiePunten;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ModulePunt(double score, int aantalStudiePunten, String moduleNaam) {
        this.score = score;
        this.aantalStudiePunten = aantalStudiePunten;
        this.moduleNaam = moduleNaam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModulePunt that = (ModulePunt) o;

        if (aantalStudiePunten != that.aantalStudiePunten) return false;
        if (Double.compare(that.score, score) != 0) return false;
        return moduleNaam != null ? moduleNaam.equals(that.moduleNaam) : that.moduleNaam == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = moduleNaam != null ? moduleNaam.hashCode() : 0;
        result = 31 * result + aantalStudiePunten;
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

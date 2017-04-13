package howest.nmctstudentscores.Models;

/**
 * Created by downe on 23/02/2017.
 */

public class ModulePunt {
    private String moduleNaam;
    private int aantalStudiePunten;
    private Double score;

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ModulePunt(String moduleNaam, int aantalStudiePunten, Double score) {
        this.moduleNaam = moduleNaam;
        this.aantalStudiePunten = aantalStudiePunten;
        this.score = score;
    }

    public ModulePunt( String moduleNaam , Double score) {
        this.score = score;
        this.moduleNaam = moduleNaam;
    }

    @Override
    public String toString() {
        return "ModulePunt{" +
                "moduleNaam='" + moduleNaam + '\'' +
                ", aantalStudiePunten=" + aantalStudiePunten +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModulePunt that = (ModulePunt) o;

        if (moduleNaam != null ? !moduleNaam.equals(that.moduleNaam) : that.moduleNaam != null)
            return false;

        return true;
    }



    @Override
    public int hashCode() {
        int result = moduleNaam != null ? moduleNaam.hashCode() : 0;
        result = 31 * result + aantalStudiePunten;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}

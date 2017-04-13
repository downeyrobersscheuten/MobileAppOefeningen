package be.howest.nmct.bmicalculator.model;

/**
 * Created by downe on 17/02/2017.
 */

public class Student {

    private String naam;
    private String voornaam;
    private String gemeente;
    private int leeftijd;

    public Student(String naam, String voornaam, String gemeente, int leeftijd) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.gemeente = gemeente;
        this.leeftijd = leeftijd;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "naam='" + naam + '\'' +
                ", voornaam='" + voornaam + '\'' +
                ", gemeente='" + gemeente + '\'' +
                ", leeftijd=" + leeftijd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (leeftijd != student.leeftijd) return false;
        if (naam != null ? !naam.equals(student.naam) : student.naam != null) return false;
        if (voornaam != null ? !voornaam.equals(student.voornaam) : student.voornaam != null)
            return false;
        return gemeente != null ? gemeente.equals(student.gemeente) : student.gemeente == null;

    }

    @Override
    public int hashCode() {
        int result = naam != null ? naam.hashCode() : 0;
        result = 31 * result + (voornaam != null ? voornaam.hashCode() : 0);
        result = 31 * result + (gemeente != null ? gemeente.hashCode() : 0);
        result = 31 * result + leeftijd;
        return result;
    }
}


package howest.nmctstudentscores.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by downe on 24/02/2017.
 */

public class Student implements Comparable<Student> {
    private String emailStudent;
    private HashMap<String,ModulePunt> scoresStudent = new HashMap<String,ModulePunt>();

    public String getEmailStudent() {
        return emailStudent;
    }

    public void setEmailStudent(String emailStudent) {
        this.emailStudent = emailStudent;
    }

    public Student(String emailStudent) {
        this.emailStudent = emailStudent;
    }

    public Student(String emailStudent, HashMap<String, ModulePunt> scoresStudent) {
        this.emailStudent = emailStudent;
        this.scoresStudent = scoresStudent;
    }
    public void VoegScoreToe(String moduleNaam, Double score,int studiePunten){
        //Make an object of ModulePunt
        ModulePunt punt = new ModulePunt(moduleNaam, studiePunten,score);
        this.scoresStudent.put(moduleNaam,punt);

    }
    public double getTotaleScoreStudent(){
        double result=0;
        double totaalStudiePunten=0;
        //bepaal som alle studiepunten
        for (ModulePunt punt: scoresStudent.values()) {
            totaalStudiePunten+= punt.getAantalStudiePunten();
        }
        //overloop alle scores.
        // score * (modulestudiepunten / totaal studie punt)
        // tel je uitkomst op
        for(ModulePunt punt: scoresStudent.values()){
            double weight = punt.getAantalStudiePunten()/totaalStudiePunten;
            double totaalPunten = punt.getScore() * weight;
            result= result + totaalPunten;
        }
        return result;
    }
    public static List<Double> getScoresModule(List<Student> studs, String  moduleNaam){
        List<Double> result = new ArrayList<Double>();
        for (Student stud: studs ) {
            for(Map.Entry<String,ModulePunt> map:stud.scoresStudent.entrySet()){
                if(map.getKey().equals(moduleNaam) ){
                    result.add(map.getValue().getScore());
                }
            }
        }
        return result;
    }
    public static double getGemiddeldeScoresModule(List<Student> studs, String moduleNaam){
        double result = 0;
        double counter=0;
        for (Student stud: studs) {
            for (Map.Entry<String,ModulePunt> map:stud.scoresStudent.entrySet()) {
                if(map.getKey().equals(moduleNaam)){
                    counter++;
                    result+=map.getValue().getScore();
                }
            }
        }
        result/=counter;
        return result;
    }
    public static void sorteerStudenten(List<Student> Studs){
        Collections.sort(Studs);
    }




    @Override
    public int compareTo(Student o) {
        return Double.compare(this.getTotaleScoreStudent(),o.getTotaleScoreStudent());
    }

    @Override
    public String toString() {
        return "Student{" +
                "emailStudent='" + emailStudent + '\'' +
                ", Gemiddelde score=" + this.getTotaleScoreStudent() +
                '}';
    }
}

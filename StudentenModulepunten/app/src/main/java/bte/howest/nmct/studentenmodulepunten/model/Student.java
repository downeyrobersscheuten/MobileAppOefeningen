package bte.howest.nmct.studentenmodulepunten.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by downe on 17/02/2017.
 */

public class Student {
    private String emailStudent;
    private HashMap<String,ModulePunt> scoresStudent = new HashMap<>();

    //const,get,set (geen setter voor scoresStudent) ,to string, equals & hashcode

    public String getEmailStudent() {
        return emailStudent;
    }

    public void setEmailStudent(String emailStudent) {
        this.emailStudent = emailStudent;
    }

    public HashMap<String, ModulePunt> getScoresStudent() {
        return scoresStudent;
    }

    public Student(HashMap<String, ModulePunt> scoresStudent, String emailStudent) {
        this.scoresStudent = scoresStudent;
        this.emailStudent = emailStudent;
    }

    @Override
    public String toString() {
        return this.emailStudent;
    }

    //methode om modulepunt obj toe te voegen

    //static - methodes
    //1 static methode om uit lijst van studenten een lijst te genereeren waarin de scores van 1 module zitten
    public static List<Double> getScoresModule(String modulenaam,List<Student> studenten){
        List<Double> res = new ArrayList<>();
        for(Student stud : studenten){
                Double score;
                if(stud.getScoresStudent().containsKey(modulenaam)){
                    score= stud.getScoresStudent().get(modulenaam).getScore();
                    res.add(score);
                }
        }
        return res;
    }
    public void voegScoreToe(String moduleNaam,Double score,int studiepunten){
        ModulePunt m = new ModulePunt(score,studiepunten,moduleNaam);
        this.scoresStudent.put(moduleNaam,m);
    }


    //2 static- methode: analoog als 1, maar geeft gemiddelde van deze scores terug
   /* public static List<Double> StudentenGemiddelde(List<Student> studenten){
        List<Double> res = new ArrayList<>();

        for(Student stud : studenten){
            BerekenGemiddelde(stud.getScoresStudent());
        }

        return res;

    }

    private static void BerekenGemiddelde(HashMap<String, ModulePunt> scoresStudent) {
        /*scoresStudent.forEach((k,v)->{

        });
        for(Map.Entry<String, ModulePunt> entry: scoresStudent.entrySet()){
            String Key = entry.getKey();
            ModulePunt value = entry.getValue();



        }
    }*/
    private double geefTotaalPercentage(){
        int somSP=0;
        for(ModulePunt mp : scoresStudent.values()){
            somSP += mp.getAantalStudiePunten();
            double gewicht = (double) mp.getAantalStudiePunten() / somSP;
        }


    }

    //3 static-methode: sorteert de studenten op basis van hun totaal percentage

}

package be.howest.nmct.bmicalculator.Model;

import java.util.Scanner;

import be.howest.nmct.bmicalculator.model.BMIInfo;
import be.howest.nmct.bmicalculator.model.Student;

/**
 * Created by downe on 17/02/2017.
 */

public class TestBmi {

    public static void main(String[] args){
        /*System.out.println("Welkom in Mobile App Development");
        System.out.println("let's go bby");

        Student stud = new Student("Robersscheuten","Downey","Jabbeke",21);*/


        Scanner s = new Scanner(System.in);
        System.out.println("weight???");
        int f = Integer.parseInt( s.next());
        System.out.println("height???");
        BMIInfo bmiinfo = new BMIInfo(1.82f,f);
        System.out.println(bmiinfo);
    }

}

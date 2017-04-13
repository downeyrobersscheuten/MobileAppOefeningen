package be.howest.nmct.bmicalculator.Model;

/**
 * Created by downe on 17/02/2017.
 */

public class BMIInfo {
    private Float height=1.70f;
    private int mass = 70;
    private float bmiindex;

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public float getBmiindex() {
        return recalculate();
    }

    public float recalculate(){
        return this.mass/(this.height * this.height);
    }

    @Override
    public String toString() {
        return "de bmi index bedraagt" + this.bmiindex + ",de bijhorende categorie is: " + categorie.getGategory(this.bmiindex);
    }

    public BMIInfo(Float height, int mass) {
        this.height = height;
        this.mass = mass;
        this.bmiindex = recalculate();
    }

    public enum categorie{
        GROOT_ONDER(Float.NEGATIVE_INFINITY,15,"Groot ondergewicht"),
        ERNSTIG_ONDER(15,16,"Ernstig ondergewicht"),
        ONDERGEWICHT(16,18.5f,"Ondergewicht"),
        NORMAAL(18.5f,25,"Normaal"),
        OVERGEWICHT(25,30,"Overgewicht"),
        MATIG(30,35,"Matig overgewitch"),
        ERNSTIG_OVER(35,40,"Ernstig Overgewicht"),
        GROOT_OVER(40,Float.NEGATIVE_INFINITY,"Groot overgewicht");

        private float lowerBoundry;
        private float upperBoundry;
        private String omschrijving;

        public float getUpperBoundry() {
            return upperBoundry;
        }

        public float getLowerBoundry() {
            return lowerBoundry;
        }

        categorie(float lower, float upper, String omsc){
            this.lowerBoundry = lower;
            this.upperBoundry = upper;
             this.omschrijving = omsc;
        }

        public String getOmschrijving() {
            return omschrijving;
        }

        @Override
        public String toString() {
            return this.omschrijving;
        }
        public boolean IsInBoundary(float index){
            //controleer of het in het interval ligt

            return (index > this.lowerBoundry) && (index <= this.upperBoundry);

        }
        public static categorie getGategory(float index){
            for(categorie c : categorie.values()){
                if(c.IsInBoundary(index))
                    return c;
            }
            return null; //als hij niets zou terugvinden
        }
    }

}

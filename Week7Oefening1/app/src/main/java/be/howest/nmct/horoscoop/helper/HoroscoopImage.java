package be.howest.nmct.horoscoop.helper;

import be.howest.nmct.horoscoop.R;
import be.howest.nmct.horoscoop.model.Data;

/**
 * Created by Stijn on 15/03/2016.
 */
public class HoroscoopImage {

    public static int getResourceId(Data.Horoscoop horoscoop) {
        switch (horoscoop) {
            case WATERMAN:
                return R.drawable.waterman;
            case VISSEN:
                return R.drawable.vissen;
            case RAM:
                return R.drawable.ram;
            case STIER:
                return R.drawable.stier;
            case TWEELING:
                return R.drawable.tweeling;
            case KREEFT:
                return R.drawable.kreeft;
            case LEEUW:
                return R.drawable.leeuw;
            case MAAGD:
                return R.drawable.maagd;
            case WEEGSCHAAL:
                return R.drawable.weegschaal;
            case SCHORPIOEN:
                return R.drawable.schorpioen;
            case BOOGSCHUTTER:
                return R.drawable.boogschutter;
            default:
                return R.drawable.steenbok;
        }
    }
}

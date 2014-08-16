package przan.mobilnaredovalnica;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rok on 7/3/13.
 */
public class Letnik implements Serializable {
    public String ime, sola;
    public Ocena koncna_ocena;
    public ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
}

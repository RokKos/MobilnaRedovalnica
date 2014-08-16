package przan.mobilnaredovalnica;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rok on 7/3/13.
 */
public class Predmet implements Serializable {
    public String ime;
    public Ocena koncna_ocena;
    public ArrayList<Ocena> ocene = new ArrayList<Ocena>();
}

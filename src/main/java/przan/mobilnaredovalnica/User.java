package przan.mobilnaredovalnica;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rok on 7/3/13.
 */
public class User implements Serializable {
    public String username= new String();
    public String password = new String();
    public ArrayList<Letnik> letnik = new ArrayList<Letnik>();

}

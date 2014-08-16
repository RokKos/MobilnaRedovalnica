package przan.mobilnaredovalnica;

import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rok on 7/4/13.
 */
public class Sola implements Serializable {
    public ArrayList<User> sola = new ArrayList<User>();
    private static String filename= "/data/data/przan.mobilnaredovalnica/files/test1";
    private static Sola instance =null;
    public static Sola getsola(){
        if (instance != null){
            return instance;
        }
        else{
            try {
                instance = branje();
                return  instance;

            }catch (Exception e){
                instance = new Sola();
                return instance;
            }
        }
    }
    public void shrani(){
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        }
        catch(IOException i){
            i.printStackTrace();
            //shrani.setText("error1");
        }

    /*TextView str = (TextView)findViewById(R.id.txt);
    str.setText(getApplicationContext().getFilesDir().getAbsolutePath());*/
    }
    public static Sola branje()throws IOException,ClassNotFoundException{
        Sola user2 = null;
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        user2 = (Sola) in.readObject();
        in.close();
        fileIn.close();
        return user2;

    }
}

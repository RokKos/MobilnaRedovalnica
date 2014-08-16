package przan.mobilnaredovalnica;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by rok on 7/2/13.
 */
public class Save extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
    }

    ArrayList <String> readStuff = new ArrayList<String>();

    public void save (View view){
      /*  //Pisanje
        String filename = "poskus";
        String string = "Hello world!;";
        String string1 = "Zivjo!";
        FileOutputStream outputStream;

     //   File file = new File(getApplicationContext().getFilesDir(), filename);

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.write(string1.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView str = (TextView)findViewById(R.id.txt);
        str.setText(getApplicationContext().getFilesDir().getAbsolutePath());

        //Branje
     //   String fileloc = "/data/data/com.example.save/files/poskus.txt";
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream in = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                int lenght = line.length();
                int count1 = -1;
                for (int x = 0; x<line.length(); x++){
                   if (x==0 || line.charAt(x)==';'){
                      readStuff.add("");
                      count1++;
                      if (x==0){
                          readStuff.set(count1,Character.toString(line.charAt(x)));
                      }
                      //line.charAt(x);
                   }
                }
             }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException i){
            i.printStackTrace();
        }
        TextView shrani = (TextView)findViewById(R.id.shran);
        shrani.setText(readStuff.get(0));  */

        TextView shrani = (TextView)findViewById(R.id.shran);
        try {
            User user = new User();
            user.username = "test";
            user.password = "pass";
            user.letnik = new ArrayList<Letnik>();
            FileOutputStream fos = new FileOutputStream("/data/data/przan.mobilnaredovalnica/files/test.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            oos.close();
            fos.close();
        }
        catch(IOException i){
            i.printStackTrace();
            shrani.setText("error1");
        }

        TextView str = (TextView)findViewById(R.id.txt);
        str.setText(getApplicationContext().getFilesDir().getAbsolutePath());
        try
        {
            User user2 = null;
            FileInputStream fileIn = new FileInputStream("/data/data/przan.mobilnaredovalnica/files/test.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user2 = (User) in.readObject();
            in.close();
            fileIn.close();
            shrani.setText(user2.password);
        }catch(IOException i){
            shrani.setText("error2");
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            shrani.setText("error3");
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
    }
}

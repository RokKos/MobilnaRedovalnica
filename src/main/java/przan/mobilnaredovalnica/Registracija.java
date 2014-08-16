package przan.mobilnaredovalnica;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by rok on 7/5/13.
 */
public class Registracija extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

    }
    public void makeNewUser(View view){
        Sola prev = Sola.getsola();
        //Ustvarjanje novega uporabnika
        EditText upor = (EditText)findViewById(R.id.editUpor);
        EditText geslo1 = (EditText)findViewById(R.id.editGeslo1);
        EditText geslo2 = (EditText)findViewById(R.id.editGeslo2);
        //preverjanje če uporabnik ze obstaja
        boolean jenot = false;
        for (int i=0; i<prev.sola.size(); i++){
            if (prev.sola.get(i).username.equals(upor.getText().toString())){
                jenot = true;
                break;
            }

        }
        AlertDialog.Builder ald;
        if (jenot){
            upor.setText("");
            geslo1.setText("");
            geslo2.setText("");
            ald = new AlertDialog.Builder(this)
                    .setTitle("Napaka")
                    .setMessage("Uporabnik že obstaja!")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            ald.show();
        }
        //Preverjanje gesel
        else if (!geslo1.getText().toString().equals(geslo2.getText().toString())){
            geslo1.setText("");
            geslo2.setText("");
            ald = new AlertDialog.Builder(this)
                    .setTitle("Napaka")
                    .setMessage("Gesli se ne ujemata. Prosim poskusite ponovno.")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            ald.show();

        }
        //Delanje uporabnika z default settingi
        else{
            Sola newUser = Sola.getsola();
            User ime = new User();
            ime.username = upor.getText().toString();
            ime.password = geslo1.getText().toString();
            ArrayList<Letnik> pred = new ArrayList<Letnik>();
            String[] let = new String[] { "1.letnik","2.letnik","3.letnik","4.letnik",};

            Letnik t;
            //Pretvarjanje
            for (int i=0; i<let.length; i++){
                t= new Letnik();
                t.ime=let[i];
                pred.add(t);
            }
            ArrayList<Predmet> predmet = new ArrayList<Predmet>();
            String [] imepred = new String[] { "Matematika", "Slovenščina", "Angleščina"};
            Predmet p;
            //Pretvarjanje
            for (int i=0; i<imepred.length; i++){
                p=new Predmet();
                p.ime=imepred[i];
                predmet.add(p);
            }
            Integer index = new Integer(newUser.sola.size());
            if (index<0) index=0;
            //Dodajanje in shranjevanje
            newUser.sola.add(ime);
            for (int i=0; i<pred.size(); i++){
                newUser.sola.get(index).letnik.add(pred.get(i));
            }

            for (int i=0; i<pred.size(); i++){
                for(int j=0; j<predmet.size(); j++){
                    newUser.sola.get(index).letnik.get(i).predmeti.add(predmet.get(j));
                }

            }
            newUser.shrani();
            ald = new AlertDialog.Builder(this)
                    .setTitle("Čestitamo")
                    .setMessage("Uporabnik je bil uspešno narejen")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            ald.show();
        }
     }
}
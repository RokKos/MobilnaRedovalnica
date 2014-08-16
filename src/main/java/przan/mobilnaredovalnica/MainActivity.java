package przan.mobilnaredovalnica;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import android.content.Context;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //reset();
        //makeUser();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void test(View view){
        Intent intent = new Intent(this, Predmeti.class);
        startActivity(intent);
    }
    public void prikazsave (View view){
        Intent intent = new Intent(this, Save.class);
        startActivity(intent);
    }
    public void prikazocene (View view){
        Intent intent = new Intent(this, OceneActivity.class);
        startActivity(intent);
    }
    public void posReg (View view){
        Intent intent = new Intent(this, Registracija.class);
        startActivity(intent);
    }
    public void reset(){
        try{
            String filename= "/data/data/przan.mobilnaredovalnica/files/test1";
            FileOutputStream fos = new FileOutputStream(filename);
            fos.close();
        }catch (IOException i){

        }
    }
    public void makeUser(){
        //Ustvarjanje novega uporabnika
        Sola newUser = Sola.getsola();
        User ime = new User();
        ime.username = "t";
        ime.password = "p";
        ArrayList<Letnik> pred = new ArrayList<Letnik>();
        String[] let = new String[] { "1.razred","2.razred","3.razred","4.razred","5.razred","6.razred",
                "7.razred","8.razred","9.razred","1.letnik","2.letnik","3.letnik","4.letnik",};

        Letnik t;
        //Pretvarjanje
        for (int i=0; i<let.length; i++){
            t= new Letnik();
            t.ime=let[i];
            pred.add(t);
        }
        ArrayList<Predmet> predmet = new ArrayList<Predmet>();
        String [] imepred = new String[] { "Mat", "Slo", "Ang", "Fiz",
                "Kem", "Gla", "Lvz", "Švz"};
        Predmet p;
        //Pretvarjanje
        for (int i=0; i<imepred.length; i++){
            p=new Predmet();
            p.ime=imepred[i];
            predmet.add(p);
        }
        ArrayList<Ocena> skocen = new ArrayList<Ocena>();
        Ocena ocena;
        //Pretvarjanje
        for(int i=0; i<4; i++){
            ocena= new Ocena();
            ocena.ocena= 5;
            skocen.add(ocena);
        }
        //Dodajanje in shranjevanje
        newUser.sola.add(ime);
        newUser.sola.get(0).letnik=pred;
        newUser.sola.get(0).letnik.get(0).predmeti=predmet;
        newUser.sola.get(0).letnik.get(0).predmeti.get(0).ocene=skocen;
        newUser.shrani();
    }
    public void prikazLetniki(View view){
        EditText uporabnik = (EditText)findViewById(R.id.editUporabnik);
        EditText geslo = (EditText)findViewById(R.id.editGeslo);
        final Sola newUpo = Sola.getsola();
        boolean nared = false;
        AlertDialog.Builder ald = new AlertDialog.Builder(this)
                    .setTitle("Napaka")
                    .setMessage("Napačno uporabniško ime ali geslo")//Integer.toString(newUpo.sola.size()))
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Naredi uporabnika
                        }
                    });
        for (int i=0;i<newUpo.sola.size();i++){
            if (newUpo.sola.get(i).username.equals(uporabnik.getText().toString())  &&
                    newUpo.sola.get(i).password.equals(geslo.getText().toString())){
                nared = true;
                Intent letniki = new Intent(this, Letniki.class);
                ArrayList<Integer> drevo =new  ArrayList<Integer>();
                drevo.add(i);
                letniki.putExtra("Uporabnik",drevo);

                startActivity(letniki);
                drevo.remove(drevo.size()-1);
                break;
            }

        }
        if (!nared){
            ald.show();
            geslo.setText("");
        }



    }
}

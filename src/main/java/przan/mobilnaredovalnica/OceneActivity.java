package przan.mobilnaredovalnica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import przan.mobilnaredovalnica.R;

public class OceneActivity extends Activity {
    ArrayAdapter<String> listAdapter;
    ArrayList<Integer> drevo;//To je arraylist od indeksov
    Predmet oc;
    ArrayList<String> conv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocene);
        //Nastavitev listviewa
        ListView mainListView = (ListView) findViewById( R.id.listOcene );
        Sola sola = Sola.getsola();
        drevo= (ArrayList<Integer>) getIntent().getSerializableExtra("ocene");
        oc = sola.sola.get(drevo.get(0)).letnik.get(drevo.get(1)).predmeti.get(drevo.get(2));
        conv = new ArrayList<String>();
        //Pretvarjanje
        for (int i=0; i<oc.ocene.size(); i++){
            conv.add(Integer.toString(oc.ocene.get(i).ocena));
        }
        listAdapter = new ArrayAdapter<String>(this, R.layout.activity_ocene_samplerow, conv);
        mainListView.setAdapter( listAdapter );
        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                conv.remove(pos);
                oc.ocene.remove(pos);
                Sola.getsola().shrani();
                listAdapter.notifyDataSetChanged();
                conint();
                return true;
            }
        });
        //POkaze povrečje
        conint();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void funDod(View view) {
        //Dodajanje ocen
        EditText editText1= (EditText) findViewById(R.id.editText);;
        Integer numOcena = Integer.parseInt(editText1.getText().toString());
        editText1.setText("");
        Ocena novocena = new Ocena();
        novocena.ocena=numOcena;
        oc.ocene.add(novocena);
        //SHranjevanje ocene
        Sola.getsola().shrani();
        conv.add(Integer.toString(numOcena));
        listAdapter.notifyDataSetChanged();
        conint();

    }

    public  void conint (){
        ArrayList<Integer> intconv = new ArrayList<Integer>();
        for (int i=0; i<conv.size(); i++){
            intconv.add(Integer.parseInt(conv.get(i)));
        }
        double povp = average(intconv);
        TextView pop = (TextView)findViewById(R.id.Povp);
        pop.setText(String.valueOf(povp));
        if (povp > 1.0 && povp < 1.49)
            pop.setTextColor(getResources().getColor(R.color.red));
        if (povp > 1.5 && povp < 2.49)
            pop.setTextColor(getResources().getColor(R.color.orange));
        if (povp > 2.5 && povp < 3.49)
            pop.setTextColor(getResources().getColor(R.color.yellow));
        if (povp > 3.5 && povp < 4.49)
            pop.setTextColor(getResources().getColor(R.color.blue));
        if (povp > 4.5)
            pop.setTextColor(getResources().getColor(R.color.green));

    }
    public double average(ArrayList<Integer> ai) {
        //Računanje povprečja
        double vsota = 0;
        for (int i=0; i<ai.size(); i++){
            vsota += ai.get(i);
        }
        vsota /= ai.size();
        return (double)Math.round(vsota*100)/100;

    }

}

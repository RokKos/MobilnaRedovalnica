package przan.mobilnaredovalnica;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;
import android.view.View;
import 	android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;


public class Predmeti extends Activity {
    ArrayList<Integer> drevo;//To je arraylist od indeksov
    ArrayAdapter<String> listAdapter;
    ArrayList<String> conv;
    Letnik leti;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predmeti);
        ListView mainListView = (ListView) findViewById( R.id.listView );
        Sola sola = Sola.getsola();
        drevo = (ArrayList<Integer>) getIntent().getSerializableExtra("raz");
        leti = sola.sola.get(drevo.get(0)).letnik.get(drevo.get(1));
        final Integer n = (Integer) getIntent().getSerializableExtra("podatki");//POGLEJ!!!
        // Set the ArrayAdapter as the ListView's adapter.
        conv = new ArrayList<String>();
        for (int i=0; i<leti.predmeti.size(); i++){
            conv.add(leti.predmeti.get(i).ime);
        }
        listAdapter = new ArrayAdapter<String>(this, R.layout.activity_predmeti_samplerow, conv);
        mainListView.setAdapter( listAdapter );
        //listAdapter.notifyDataSetChanged();
        //Ko klinkneš na element se zgodi ta funkcija
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                //final String item = (String) parent.getItemAtPosition(position);
                Intent newActivity = new Intent(getApplicationContext(), OceneActivity.class);
                Integer k = new Integer(position);
                drevo.add(k);
                //Pošiljanje naprej indeksa
                newActivity.putExtra("ocene",drevo);
                startActivity(newActivity);
                drevo.remove(drevo.size()-1);
            }
        });
        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                conv.remove(pos);
                leti.predmeti.remove(pos);
                Sola.getsola().shrani();
                listAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
    public void fundodPred(View view){
        EditText editText1= (EditText) findViewById(R.id.editText);;
        String nameLet = editText1.getText().toString();
        editText1.setText("");
        Predmet pred = new Predmet();
        pred.ime=nameLet;
        leti.predmeti.add(pred);
        //SHranjevanje ocene
        Sola.getsola().shrani();
        conv.add(nameLet);
        listAdapter.notifyDataSetChanged();
    }
}
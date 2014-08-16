package przan.mobilnaredovalnica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rok on 7/4/13.
 */
public class Letniki extends Activity {
    ArrayList<String > con;
    User u;
    ArrayList<Integer> drevo;//To je arraylist od indeksov
    ArrayAdapter<String> listAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letniki);
        ListView mainListView = (ListView) findViewById( R.id.listView );
        Sola sola = Sola.getsola();
        drevo = (ArrayList<Integer>) getIntent().getSerializableExtra("Uporabnik");
        u = sola.sola.get(drevo.get(0));
        // Set the ArrayAdapter as the ListView's adapter.
        con = new ArrayList<String>();
        //Pretvarjanje
        for (int i=0; i<u.letnik.size(); i++){
            con.add(u.letnik.get(i).ime);
        }
        listAdapter = new ArrayAdapter<String>(this, R.layout.activity_letniki_samplerow, con);
        mainListView.setAdapter(listAdapter);
        //Ko klinkneš na element se zgodi ta funkcija
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Intent newActivity = new Intent(getApplicationContext(), Predmeti.class);
                Integer j = new Integer(position);
                drevo.add(j);
                //POšiljanje naprej indeksa
                newActivity.putExtra("raz", drevo);
                startActivity(newActivity);
                drevo.remove(drevo.size()-1);
            }
        });
        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                con.remove(pos);
                u.letnik.remove(pos);
                Sola.getsola().shrani();
                listAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }
    public void fundodLet(View view){
        EditText editText1= (EditText) findViewById(R.id.editText);;
        String nalet = "";
        String nameLet = editText1.getText().toString();
        editText1.setText("");
        Letnik neki = new Letnik();
        neki.ime=nameLet;
        u.letnik.add(neki);
        //SHranjevanje ocene
        Sola.getsola().shrani();
        con.add(nameLet);
        listAdapter.notifyDataSetChanged();
    }
}
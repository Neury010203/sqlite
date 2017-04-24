package com.software.nafl.sqlite_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.software.nafl.sqlite_test.Modelos.DBHelper;
import com.software.nafl.sqlite_test.Modelos.Vehiculo;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DialogForVehiculo.DialogAddVehiculoListener {

    private static final String TAG = MainActivity.class.getName();

    private DBHelper mDBHelper;

    private List<Vehiculo> mAllVehiculos;
    private ArrayList<String> mAllTitulos;
    private ArrayAdapter mAdapter;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDBHelper = new DBHelper(this);
        preaparListView();

       // guardar = (Button)findViewById(R.id.button);


      /*  guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ver = new Intent(MainActivity.this,VehiculosRegistrados.class);
                startActivity(ver);
            }
        });*/


    }












    //===========para presentar el menu===========
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Aqui se manejan las opciones del menu del Action Bar

        //Obtenemos el ID de la opción de Menu
        int id = item.getItemId();

        //Comparamos el ID para saber que opcion es la que el user selecciono
        if (id == R.id.action_add) {
            showAddVehiculoDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    private void preaparListView(){
        //Instanciamos el ListView
        ListView mListView = (ListView) findViewById(R.id.lvNotes);

        // Definimos un array con los valores de los titulos de las notas
        // que presentaremos en el ListView
        resfreshNotes();

        //Definimos el Adaptar el cual se ecaragará de mapear con el UI los difernetes
        // titulos de las notas
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mAllTitulos);


        // Asiganmos el adapter al ListView, para presentar los datos en pantalla
        if (mListView != null) {
            mListView.setAdapter(mAdapter);
            //Definimos un OnItemClickListener para saber cuando el usuario ha seleccionado
            // una Nota y poder navegar a la vista de detalle de la nota
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Vehiculo selVehiculo = mAllVehiculos.get(position);
                   // Log.d(TAG, "Selected Note: " + selNote.getTitle());
                    //Instanciamos el DialogForNote
                    DialogForVehiculo newAddVehiculo = new DialogForVehiculo();

                    //Seteamos la nota que deseamos actualizar
                    newAddVehiculo.setmVehiculoForUpd(selVehiculo);

                    //Mostramos el DialogForNote asignadole el tag "addnote"
                    newAddVehiculo.show(getFragmentManager(), "addvihiculo");

                }
            });

            /*//Definimos un  OnItemLongClickListener con el cual conseguiremos borrar la nota
            mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   // Log.d(TAG, "onItemLongClick" + mAllNotes.get(position).getTitle());
                    //Obtenemos la referencia a la nota sobre la cual el usuario
                    // esta haciendo LongClick
                    Vehiculo delNote = mAllVehiculos.get(position);
                    if (mDBHelper != null){
                        //Borramos la nota de la base de datos
                        mDBHelper.deleteNote(delNote);
                        //Refrescamos la lista de notas
                        resfreshNotes();
                        //Devolvemos true para evitar que se ejecute el OnItemClickListener
                        return true;
                    }

                    //Devolvemos false para que se ejecute el OnItemClickListener ya que no
                    // se ha logrado el borrado de la nota
                    return false;
                }
            });*/
        }
    }






    private void showAddVehiculoDialog(){
        Log.d(TAG, "showAddNoteDialog");
        //Instanciamos el DialogForNote
        DialogForVehiculo newAddVehiculo = new DialogForVehiculo();
        //Mostramos el DialogForNote asignadole el tag "addvehiculo"
        newAddVehiculo.show(getFragmentManager(),"addvehiculo");
        //newAddVehiculo.show(getSupportFragmentManager(), "addvehiculo");
    }






    @Override
    public void onDialogAddVehiculo(Vehiculo newVehiculo) {
        Log.d(TAG, "onDialogAddNote");
        if (mDBHelper != null){
            //Insertamos el nuevo registro
            mDBHelper.insertReg2403(newVehiculo);
            //Refrescamos el ListView
            resfreshNotes();
        }
    }

    private void resfreshNotes(){
        //Cargamos todas las notas
        mAllVehiculos = mDBHelper.getAllVehiculos();

        //Iteramos sobre todas las notas para pasar los titulos a un Array String
        int idx = 0;
        mAllTitulos = new ArrayList<String>();
        for (Vehiculo aVehiculo : mAllVehiculos) {
            mAllTitulos.add(aVehiculo.getCompania_2403());
            idx++;
        }
        //Si el Adapter esta instanciado notificamos los cambios
        if (mAdapter != null){
            //Limpiamos todos los datos
            mAdapter.clear();
            //Agreamos los nuevos datos
            mAdapter.addAll(mAllTitulos);
            //Notificamos los cambios
            mAdapter.notifyDataSetChanged();
        }
    }






    @Override
    public void onDialogCancel() {

    }

    @Override
    public void onDialogUpdNote(Vehiculo newVehiculo) {
        Log.d(TAG, "onDialogUpdNote");
        if (mDBHelper != null){
            //Insertamos el nuevo registro
            mDBHelper.updateNote(newVehiculo);
            //Refrescamos el ListView
            resfreshNotes();
        }
    }
}

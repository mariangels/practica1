package com.example.mariangeles.practica1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Principal extends Activity {

    ArrayList<Palabra> palabras;
    private Adaptador aa;

    /*LOS POR DEFECTO*/
    /**********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*MAS DE ON*/
    /**********************************************************************************************/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opciones, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final int index = info.position;

        Object o = info.targetView.getTag();
        Adaptador.ViewHolder vh;
        vh = (Adaptador.ViewHolder)o;

        if (id == R.id.actBorrar){
            tostada("Borrado elemento: "+index);
            palabras.remove(index);
            aa.notifyDataSetChanged();

        }else if (id == R.id.actModificar){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Editar elemento");
            LayoutInflater inflater = LayoutInflater.from(this);
            final View vista = inflater.inflate(R.layout.aniadir, null);
            alert.setView(vista);

            EditText txNombre=null, txIdioma=null, txTraduccion=null, txSignificado=null;
            txNombre.setText(palabras.get(index).getNombre());
            txIdioma.setText(palabras.get(index).getIdioma());
            txTraduccion.setText(palabras.get(index).getTraduccion());
            txSignificado.setText(palabras.get(index).getSignificado());

            alert.setPositiveButton("Editar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String nombre, idioma, significado, traduccion;
                            nombre = ((EditText) vista.findViewById(R.id.txNombre)).toString();
                            idioma= ((EditText) vista.findViewById(R.id.txIdioma)).toString();
                            significado= ((EditText) vista.findViewById(R.id.txSignificado)).toString();
                            traduccion= ((EditText) vista.findViewById(R.id.txTraduccion)).toString();
                            Palabra pal=new Palabra(nombre,idioma,traduccion,significado);
                            palabras.set(index,pal);
                            aa.notifyDataSetChanged();
                            tostada("Elemento editado");
                        }
                    });
            alert.setNegativeButton("cancelar",null);
            alert.show();
        }
        return super.onContextItemSelected(item);
    }


    /**********************************************************************************************/
    public void mas(View view){


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alta");
        LayoutInflater inflater = LayoutInflater.from(this);
        final View vista = inflater.inflate(R.layout.aniadir, null);
        alert.setView(vista);
        alert.setPositiveButton("insertar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String nombre, idioma, significado, traduccion;
                        nombre = ((EditText) vista.findViewById(R.id.txNombre)).toString();
                        idioma= ((EditText) vista.findViewById(R.id.txIdioma)).toString();
                        significado= ((EditText) vista.findViewById(R.id.txSignificado)).toString();
                        traduccion= ((EditText) vista.findViewById(R.id.txTraduccion)).toString();
                        Palabra pal=new Palabra(nombre,idioma,traduccion,significado);
                        palabras.add(pal);

                        aa.notifyDataSetChanged();
                        tostada("Elemento añadido");
                    }
                });
        alert.setNegativeButton("cancelar",null);
        alert.show();

    }


    public void abrirDiccionario(View view) {
        setContentView(R.layout.diccionario);

        final ArrayList palabras=new ArrayList<Palabra>();
        palabras.add( new Palabra("red", "ingles", "rojo", ""));
        palabras.add( new Palabra("yellow", "ingles", "amarillo", ""));
        palabras.add( new Palabra("Kartöfel", "aleman", "patata", ""));
        palabras.add( new Palabra("hold on", "ingles", "espere", ""));
        palabras.add( new Palabra("Salut", "frances", "hola", ""));

        final ListView lt = (ListView) findViewById(R.id.lista);
        Adaptador aa = new Adaptador(this, R.layout.elemento, palabras);
        lt.setAdapter(aa);

        lt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tostada(((Palabra)palabras.get(i)).getNombre() );
            }
        });
        registerForContextMenu(lt);

    }
    public void atras(View view){
        setContentView(R.layout.activity_principal);
    }
    public void fin(View view){
        System.exit(0);
    }

    /****AUXILIARES*****/
    /**********************************************************************************************/


    /******TOSTADA*******/
    private void tostada(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}

package com.example.mariangeles.practica1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Adaptador extends ArrayAdapter<Palabra> {

    private Context contexto;
    private ArrayList<Palabra> lista;
    private int recurso;
    private Random rnd = new Random();

    private static LayoutInflater i;                                                                //Para que no lo cree cada vez que llama al GetView()

    public static class ViewHolder{
        public TextView tv1;
    }

    public Adaptador(Context context, int resource, ArrayList<Palabra> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.lista = objects;
        this.recurso = resource;

        this.i = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);       //CREAMOS INFLADOR
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
                                                            //Para ver si crece el arraylist por logs
        //Con ViewHolder

        ViewHolder vh = null;
        if(convertView == null){
            convertView = i.inflate(recurso, null);
            vh = new ViewHolder();
            vh.tv1 = (TextView)convertView.findViewById(R.id.tvNombre);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        vh.tv1.setText(lista.get(position).mostrar());

        return convertView;
    }
}

package com.example.mariangeles.practica1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Adaptador extends ArrayAdapter<Palabra> {

    private Context contexto;
    private ArrayList<Palabra> lista;
    private int recurso;
    private Random rnd = new Random();

    private static LayoutInflater i;

    public static class ViewHolder{
        public TextView tvNombre;
        public ImageView ivImagen;
    }

    public Adaptador(Context context, int resource, ArrayList<Palabra> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.lista = objects;
        this.recurso = resource;

        this.i = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Con ViewHolder

        ViewHolder vh = null;
        if(convertView == null){
            convertView = i.inflate(recurso, null);
            vh = new ViewHolder();
            vh.tvNombre = (TextView)convertView.findViewById(R.id.tvNombre);
            vh.ivImagen = (ImageView)convertView.findViewById(R.id.ivImagen);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }


        icono(lista.get(position).getIdioma(), vh);

        vh.tvNombre.setText(lista.get(position).mostrar());



        return convertView;
    }


    public void icono(String idioma, ViewHolder vh){
        if(idioma=="aleman"){
            vh.ivImagen.setImageResource(R.drawable.alemania);
        }else{
            if(idioma=="español"){
                vh.ivImagen.setImageResource(R.drawable.espanol);
            }else{
                if(idioma=="frances") {
                    vh.ivImagen.setImageResource(R.drawable.frances);
                }else{
                    vh.ivImagen.setImageResource(R.drawable.ingles);
                }

            }
        }
    }
}

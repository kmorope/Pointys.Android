package com.konradlorenz.pointys;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wenikore on 19/11/16.
 */

public class AdaptadorDatosCatalogo extends RecyclerView.Adapter<AdaptadorDatosCatalogo.Vista> {

    private String[] textos;
    private int[] imagenes;



    private Listener listener;

    public static interface Listener {
        public void onClick(int posicion);
    }

    public AdaptadorDatosCatalogo(String[] textos, int[] imagenes) {
        this.textos = textos;
        this.imagenes = imagenes;

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public AdaptadorDatosCatalogo.Vista onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_datos_catalogos, parent, false);
        return new Vista(cv);
    }

    @Override
    public void onBindViewHolder(Vista holder, final int posicion) {

        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.info_imagenT);

        Drawable drawable = ResourcesCompat.getDrawable(cardView.getResources(), imagenes[posicion], null);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(textos[posicion]);


        TextView textView = (TextView) cardView.findViewById(R.id.info_textoT);
        textView.setText(textos[posicion]);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(posicion);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return textos.length;
    }


    public static class Vista extends RecyclerView.ViewHolder {

        private CardView cardView;

        public Vista(CardView v) {
            super(v);
            cardView = v;
        }
    }


}

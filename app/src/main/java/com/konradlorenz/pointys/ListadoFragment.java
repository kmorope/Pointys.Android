package com.konradlorenz.pointys;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ListadoFragment extends ListFragment {

    private ArrayList<Lista> listado = new ArrayList<>();

    public ListadoFragment() {
        // Required empty public constructor
    }

//    public static final Lista[] listadoCerca = {
//            new Lista(),
//            new Lista("Teatro Libre", "Cl. 62 #9-65,", 4.648428, -74.062635),
//            new Lista("Bodytech Chapinero", "Carrera 7 #63-25", 4.648166, -74.059835),
//            new Lista("Casa Del Musico La Colonial", "Carrera 7 #57-43, ", 4.643993, -74.061922),
//            new Lista("Starbucks", "Cl. 67 #7-98", 4.651392, -74.059107),
//    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView();
        listado.add(new Lista(" picos caffé", " Ac 63 #11-49", 719));
        listado.add(new Lista("Bodytech Chapinero", "Carrera 7 #63-25", 684));
        listado.add(new Lista("Casa Del Musico La Colonial", "Carrera 7 #57-43, ", 922));
        listado.add(new Lista("Viaggio 6.1.7", "Calle 61 #7-2 ", 5));
        listado.add(new Lista("Tuticket", "Carrera 13 #67-11", 99));
        listado.add(new Lista("Teatro Nacional Fanny", "Avenida Calle 72 #11-01", 312));
        listado.add(new Lista("C.C Avenida Chile", "Cl. 67 #7-98", 766));



        View rootView = inflater.inflate(R.layout.fragment_listado, container, false);


        ArrayAdapter<Lista> adapter = new propertyArrayAdapter(getActivity().getApplication(), 0, listado);


        setListAdapter(adapter);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                inflater.getContext(),
//                R.layout.simple_list_icom,
//                R.id.Itemname,
//                listado                );
//        setListAdapter(adapter);
        //  return inflater.inflate(R.layout.fragment_listado, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
        //return rootView;
    }

    //custom ArrayAdapater
    class propertyArrayAdapter extends ArrayAdapter<Lista> {

        private Context context;
        private List<Lista> listProperties;

        public propertyArrayAdapter(Context context, int resource, ArrayList<Lista> objects) {
            super(context, resource, objects);
            this.context = context;
            this.listProperties = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            Lista lista = listProperties.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.simple_list_icom, null);

            TextView lugar = (TextView) view.findViewById(R.id.Itemname);
            TextView ubicacion = (TextView) view.findViewById(R.id.txv_ubicacion);
            TextView distancia = (TextView) view.findViewById(R.id.txv_distancia);

            lugar.setText(String.valueOf(lista.getLugar()));
            ubicacion.setText(String.valueOf(lista.getUbicacion()));
            distancia.setText(String.valueOf(lista.getdistancia()) + "m");


            return view;
        }


    }

}

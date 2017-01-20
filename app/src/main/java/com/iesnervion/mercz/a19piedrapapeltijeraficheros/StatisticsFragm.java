package com.iesnervion.mercz.a19piedrapapeltijeraficheros;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mrequena on 18/01/17.
 */

public class StatisticsFragm extends Fragment implements View.OnClickListener {

    public interface OnButtonSelectedListener{

        public void onBorrarClick(View btn);

    }



    final static String ARG_PIEDRA = "PIEDRA";
    final static String ARG_PAPEL = "PAPEL";
    final static String ARG_TIJERAS = "TIJERAS";
    final static String ARG_GANADAS = "GANADAS";
    final static String ARG_PERDIDAS = "PERDIDAS";
    final static String ARG_EMPATADAS = "EMPATADAS";
    int piedra,  papel,  tijeras,  ganadas,  perdidas,  empatadas;

    public static StatisticsFragm newInstance(int piedra, int papel, int tijeras, int ganadas, int perdidas, int empatadas){
    StatisticsFragm fragment = new StatisticsFragm();
        Bundle args = new Bundle();
        args.putInt(ARG_PIEDRA,piedra);
        args.putInt(ARG_PAPEL, papel);
        args.putInt(ARG_TIJERAS, tijeras);
        args.putInt(ARG_GANADAS, ganadas);
        args.putInt(ARG_PERDIDAS, perdidas);
        args.putInt(ARG_EMPATADAS, empatadas);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = null;



        if (getArguments() != null) {
            piedra = getArguments().getInt(ARG_PIEDRA);
            papel = getArguments().getInt(ARG_PAPEL);
            tijeras = getArguments().getInt(ARG_TIJERAS);
            ganadas = getArguments().getInt(ARG_GANADAS);
            perdidas = getArguments().getInt(ARG_PERDIDAS);
            empatadas = getArguments().getInt(ARG_EMPATADAS);
        }

        v = inflater.inflate(R.layout.estadistica_fragment,container,false);

        v.findViewById(R.id.borrarEstadistica).setOnClickListener(this);

        TextView txtPiedraTiradas = (TextView) v.findViewById(R.id.txtPiedraTiradas);
        txtPiedraTiradas.setText(String.valueOf(piedra));
        TextView txtPapelTiradas= (TextView) v.findViewById(R.id.txtPapelTiradas);
        txtPapelTiradas.setText(String.valueOf(papel));
        TextView txtTijerasTiradas = (TextView) v.findViewById(R.id.txtTijerasTiradas);
        txtTijerasTiradas.setText(String.valueOf(tijeras));
        TextView txtGanadas= (TextView) v.findViewById(R.id.txtGanadas);
        txtGanadas.setText(String.valueOf(ganadas));
        TextView txtPerdidas = (TextView) v.findViewById(R.id.txtPerdidas);
        txtPerdidas.setText(String.valueOf(perdidas));
        TextView txtEmpatadas = (TextView) v.findViewById(R.id.txtEmpatadas);
        txtEmpatadas.setText(String.valueOf(empatadas));

        return v;
    }

    @Override
    public void onClick(View view) {
        ((MainActivity) getActivity()).onBorrarClick(view);
    }
}

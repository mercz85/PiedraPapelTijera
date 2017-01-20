package com.iesnervion.mercz.a19piedrapapeltijeraficheros;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by mrequena on 18/01/17.
 */

public class PlayFragm extends Fragment implements View.OnClickListener{
    final static String ARG_BOTON = "Button";
    RelativeLayout layout;
    ImageView imgViewIA;
    Button btnPiedra;
    Button btnPapel;
    Button btnTijeras;

    OnButtonSelectedListener mCallback;

    int btnId;

    public interface OnButtonSelectedListener {
        public void onButtonSelected(View btn);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v= inflater.inflate(R.layout.play_fragment, container,false);

        v.findViewById(R.id.btnPiedra).setOnClickListener(this);
        v.findViewById(R.id.btnPapel).setOnClickListener(this);
        v.findViewById(R.id.btnTijera).setOnClickListener(this);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof PlayFragm.OnButtonSelectedListener) {
            mCallback = (PlayFragm.OnButtonSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnButtonSelectedListener");
        }
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) getActivity()).onButtonSelected(v);
    }

}

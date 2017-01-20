/*
ATENCION!!! ver modificaciones del archivo built.gradle(Module:app)
https://medium.com/@hitherejoe/exploring-the-android-design-support-library-bottom-navigation-drawer-548de699e8e0#.ygsj5abtg
https://github.com/hitherejoe/BottomNavigationViewSample

EJERCICIO 1

Hacer una aplicación que permita jugar al juego de "Piedra, papel, tijeras".
La aplicación debe ser capaz de guardar y consultar estadísticas (partidas ganadas,
 partidas perdidas, veces que se ha elegido "Piedra", etc...), valiéndose para ello
  de SharedPreferences.

  Shared preferences:
  Ganadas
  Perdidas
  Empatadas
  Piedra
  Papel
  Tijera
*/
package com.iesnervion.mercz.a19piedrapapeltijeraficheros;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

//import android.support.annotation.NonNull;

//import static com.iesnervion.mercz.a19piedrapapeltijeraficheros.R.color.colorPrimaryDark;

public class MainActivity extends AppCompatActivity implements PlayFragm.OnButtonSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener, StatisticsFragm.OnButtonSelectedListener {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
   // final static String FILE_SHARED_PREF = "";


    final static String ARG_PIEDRA = "PIEDRA";
    final static String ARG_PAPEL = "PAPEL";
    final static String ARG_TIJERAS = "TIJERAS";
    final static String ARG_GANADAS = "GANADAS";
    final static String ARG_PERDIDAS = "PERDIDAS";
    final static String ARG_EMPATADAS = "EMPATADAS";
    int piedra,  papel,  tijeras,  ganadas,  perdidas,  empatadas;

//Navitagion bottom bar

     BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sharedPref = this.getPreferences(Context.MODE_PRIVATE); //si estuviese en un fragment diria getActivity().getPreferences(...);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        editor = sharedPref.edit();

        //Recupero las estadisticas
        piedra = sharedPref.getInt(ARG_PIEDRA,0);
        papel = sharedPref.getInt(ARG_PAPEL,0);
        tijeras = sharedPref.getInt(ARG_TIJERAS,0);
        ganadas = sharedPref.getInt(ARG_GANADAS,0);
        perdidas = sharedPref.getInt(ARG_PERDIDAS,0);
        empatadas = sharedPref.getInt(ARG_EMPATADAS,0);

/*        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(ARG_PIEDRA, ganadas);
        editor.putInt(ARG_PAPEL, ganadas);
        editor.putInt(ARG_TIJERAS, ganadas);
        editor.putInt(ARG_GANADAS, ganadas);
        editor.putInt(ARG_PERDIDAS, ganadas);
        editor.putInt(ARG_EMPATADAS, ganadas);
        editor.commit();*/

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Guardo las estadisticas
        //editor = sharedPref.edit();
/*      editor.putInt(ARG_PIEDRA, piedra);
        editor.putInt(ARG_PAPEL, papel);
        editor.putInt(ARG_TIJERAS, tijeras);
        editor.putInt(ARG_GANADAS, ganadas);
        editor.putInt(ARG_PERDIDAS, perdidas);
        editor.putInt(ARG_EMPATADAS, empatadas);
        editor.commit();*/

     //   editor.commit();

    }

    public void onButtonSelected(View v) {
        View frame = (View) findViewById(R.id.playFragment);

        //crear imagen aleatoria
        Random r = new Random();
        int tiradaIA = r.nextInt(3);
        ImageView iv = (ImageView)findViewById(R.id.imgTiradaIA);

        if(tiradaIA==0){
            iv.setImageResource(R.drawable.piedra);
        }else if(tiradaIA==1){
            iv.setImageResource(R.drawable.papel);
        }else{ //tiradaIA==2
            iv.setImageResource(R.drawable.tijeras);
        }

        //
        switch (v.getId()){
            case R.id.btnPiedra:
                piedra++;
                if(tiradaIA==0){        //piedra vs piedra
                    empatadas++;
                    frame.setBackgroundColor(getResources().getColor(R.color.amarillo));
                }else if(tiradaIA==1){ //papel vs piedra
                    perdidas++;
                    frame.setBackgroundColor(getResources().getColor(R.color.rojo));
                }else{ //tiradaIA==2   //tijeras vs piedra
                    ganadas++;
                    frame.setBackgroundColor(getResources().getColor(R.color.verde));
                }

                break;
            case R.id.btnPapel:
                papel++;
                if(tiradaIA==0){        //piedra vs papel
                    ganadas++;
                    frame.setBackgroundColor(getResources().getColor(R.color.verde));
                }else if(tiradaIA==1){ //papel vs papel
                    empatadas++;
                    frame.setBackgroundColor(getResources().getColor(R.color.amarillo));
                }else{ //tiradaIA==2   //tijeras vs papel
                    perdidas++;
                    frame.setBackgroundColor(getResources().getColor(R.color.rojo));
                }
                break;
            case R.id.btnTijera:
                    tijeras++;
                if(tiradaIA==0){        //piedra vs tijeras
                    perdidas++;
                    frame.setBackgroundColor(getResources().getColor(R.color.rojo));
                }else if(tiradaIA==1){ //papel vs tijeras
                    ganadas++;
                    frame.setBackgroundColor(getResources().getColor(R.color.verde));
                }else{ //tiradaIA==2   //tijeras vs tijeras
                    empatadas++;
                    frame.setBackgroundColor(getResources().getColor(R.color.amarillo));
                }
                break;
        }

        editor.putInt(ARG_PIEDRA, piedra);
        editor.putInt(ARG_PAPEL, papel);
        editor.putInt(ARG_TIJERAS, tijeras);
        editor.putInt(ARG_GANADAS, ganadas);
        editor.putInt(ARG_PERDIDAS, perdidas);
        editor.putInt(ARG_EMPATADAS, empatadas);

        editor.apply();
       // editor.commit();
    }


    @Override
    public boolean onNavigationItemSelected( MenuItem item) { //(@NonNull MenuItem item)
        switch (item.getItemId()) {
            case R.id.action_favorites:

                    bottomNavigationView.setItemBackgroundResource(R.color.colorAccent);
                    item.setEnabled(true);
                    item.setVisible(true);
                //item.collapseActionView();
                break;
            case R.id.action_schedules:

                    bottomNavigationView.setItemBackgroundResource(R.color.naranja);
                    //bottomNavigationView.setItemTextColor(R.drawable.nav_item_color_state);
                    //bottomNavigationView.setItemIconTintList(R.color.colorAccent);
                    item.setEnabled(true);
                //item.setVisible(true);

                break;
            case R.id.action_music:
                    bottomNavigationView.setItemBackgroundResource(R.color.rosaClaro);
                    item.setEnabled(true);
                //item.collapseActionView();
                break;
            case R.id.action_play:
                bottomNavigationView.setItemBackgroundResource(R.color.azulClaro);

                PlayFragm playFr = new PlayFragm();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, playFr).addToBackStack(null).commit();
                break;
            case R.id.action_estadistica:
                bottomNavigationView.setItemBackgroundResource(R.color.moradoClaro);

                StatisticsFragm statFr = StatisticsFragm.newInstance(piedra, papel, tijeras, ganadas, perdidas, empatadas);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, statFr).addToBackStack(null).commit();
                break;
        }
        return true;
    }

    //INTERFACE DE StstisticsFragment
    @Override
    public void onBorrarClick(View btn) {
     //   editor.remove(ARG_PIEDRA).commit();
        editor.clear();
        editor.apply();

        //Recupero las estadisticas (esto no es necesario, si ya se los valores por defecto que quiero poner)
        piedra = sharedPref.getInt(ARG_PIEDRA,0);
        papel = sharedPref.getInt(ARG_PAPEL,0);
        tijeras = sharedPref.getInt(ARG_TIJERAS,0);
        ganadas = sharedPref.getInt(ARG_GANADAS,0);
        perdidas = sharedPref.getInt(ARG_PERDIDAS,0);
        empatadas = sharedPref.getInt(ARG_EMPATADAS,0);
        /* Podría crear otro frament,pero no es muy buena idea
        StatisticsFragm statFr = StatisticsFragm.newInstance(piedra, papel, tijeras, ganadas, perdidas, empatadas);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, statFr).addToBackStack(null).commit();
        */
        //Aqui podria poner directamente 0, en vez de leer otra vez el fichero arriba
        TextView txtPiedraTiradas = (TextView) findViewById(R.id.txtPiedraTiradas);
        txtPiedraTiradas.setText(String.valueOf(piedra));

        TextView txtPapelTiradas = (TextView) findViewById(R.id.txtPapelTiradas);
        txtPapelTiradas.setText(String.valueOf(papel));

        TextView txtTijerasTiradas = (TextView) findViewById(R.id.txtTijerasTiradas);
        txtTijerasTiradas.setText(String.valueOf(tijeras));

        TextView txtGanadas = (TextView) findViewById(R.id.txtGanadas);
        txtGanadas.setText(String.valueOf(ganadas));

        TextView txtPerdidas = (TextView) findViewById(R.id.txtPerdidas);
        txtPerdidas.setText(String.valueOf(perdidas));

        TextView txtEmpatadas = (TextView) findViewById(R.id.txtEmpatadas);
        txtEmpatadas.setText(String.valueOf(empatadas));

/*      esto me serviria si tuviese DIFERENTES ACTIVIDADES en vez de FRAGMENTS
            finish();
            startActivity(getIntent());*/
    }
}





/*  ASI VENÍA EN EL EJEMPLO, HE SACADO LA INTERFAZ

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
*//*                              textFavorites.setVisibility(View.VISIBLE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.GONE);*//*
                                if(item.isEnabled()){
                                    bottomNavigationView.setItemBackgroundResource(R.color.colorAccent);

                                }
                                break;
                            case R.id.action_schedules:
*//*                                textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.VISIBLE);
                                textMusic.setVisibility(View.GONE);*//*
                                if(item.isEnabled()){
                                    bottomNavigationView.setItemBackgroundResource(R.color.naranja);
                                    //bottomNavigationView.setItemTextColor(R.drawable.nav_item_color_state);
                                    //bottomNavigationView.setItemIconTintList(R.color.colorAccent);
                                }
                                break;
                            case R.id.action_music:
*//*                                textFavorites.setVisibility(View.GONE);
                                textSchedules.setVisibility(View.GONE);
                                textMusic.setVisibility(View.VISIBLE);*//*
                                if(item.isEnabled()){
                                    bottomNavigationView.setItemBackgroundResource(R.color.rosaClaro);
                                }
                                break;
                            case R.id.action_play:
                                bottomNavigationView.setItemBackgroundResource(R.color.azulClaro);

                                PlayFragm playFr = new PlayFragm();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame, playFr).addToBackStack(null).commit();
                                break;
                            case R.id.action_estadistica:
                                bottomNavigationView.setItemBackgroundResource(R.color.moradoClaro);

                                StatisticsFragm statFr = StatisticsFragm.newInstance(piedra, papel, tijeras, ganadas, perdidas, empatadas);
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame, statFr).addToBackStack(null).commit();
                                break;
                        }
                        return false;
                    }
                }
        );*/



/* EJEMPLO AÑADIR BOTONES
bottomNavigationBar
        .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Home"))
        .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "Books"))
        .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "Music"))
        .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "Movies & TV"))
        .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, "Games"))
        .initialise();*/

package paton.laura.velocidaddecalculo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private Fragment fragment;
    private Button btnCambiar;
    private static int tiempo = 0;
    public static TextView pantallaOperaciones, pantallaAciertos, pantallaFallos;
    public static Partida partida;
    public static Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();

        fragment = new NormalGameFragment();
        chronometer = findViewById(R.id.chronometer1);
        btnCambiar = findViewById(R.id.btnCambio);
        pantallaOperaciones = findViewById(R.id.pantallaOperaciones);
        pantallaAciertos = findViewById(R.id.txtAciertos);
        pantallaFallos = findViewById(R.id.txtFallos);
        partida = new Partida();
        pantallaAciertos.setText(getString(R.string.aciertos, partida.getAciertos()));
        pantallaFallos.setText(getString(R.string.fallos, partida.getFallos()));

        Bundle data = this.getIntent().getExtras();
        tiempo = data.getInt("tiempo");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_replace,fragment)
                .commit();

        startChronometer(getApplicationContext());
        pantallaOperaciones.setText(partida.generarOperacion());
    }

    public static void startChronometer(final Context c){
        chronometer.setCountDown(true);
        chronometer.setBase(SystemClock.elapsedRealtime()+(tiempo));
        chronometer.start();

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(Game.chronometer.getText().equals("00:00")){
                    Game.partida.sumarFallo(c);
                    NormalGameFragment.pantallaNumero.setText("");
                    NormalGameFragment.numero = "";
                    NormalGameFragment.signo = "+";
                    nuevaOperacion(c);
                }
            }
        });
    }

    public static void nuevaOperacion(Context c){
        Game.chronometer.stop();
        pantallaOperaciones.setText(partida.generarOperacion());
        startChronometer(c);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Game.chronometer.stop();
        this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        chronometer.stop();
        finish();
    }
}
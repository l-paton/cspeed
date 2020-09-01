package paton.laura.velocidaddecalculo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private Button btnEmpezar;
    public static TextView pantallaOperaciones, pantallaAciertos, pantallaFallos;
    public static Partida partida;
    public static Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        fragment = new NormalGameFragment();
        chronometer = findViewById(R.id.chronometer1);
        btnEmpezar = findViewById(R.id.btnEmpezar);
        pantallaOperaciones = findViewById(R.id.pantallaOperaciones);
        pantallaAciertos = findViewById(R.id.txtAciertos);
        pantallaFallos = findViewById(R.id.txtFallos);
        partida = new Partida();
        pantallaAciertos.setText(getString(R.string.aciertos, partida.getAciertos()));
        pantallaFallos.setText(getString(R.string.fallos, partida.getFallos()));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_replace,fragment)
                .commit();
    }

    public void empezar(View view) {
        startChronometer(getApplicationContext());
        pantallaOperaciones.setText(partida.generarOperacion());
        btnEmpezar.setText("Stop");
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStop();

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        chronometer.stop();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
    }

    public static void startChronometer(final Context c){
        chronometer.setCountDown(true);
        chronometer.setBase(SystemClock.elapsedRealtime()+(10*1000));
        chronometer.start();

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(MainActivity.chronometer.getText().equals("00:00")){
                    MainActivity.partida.sumarFallo(c);
                    NormalGameFragment.pantallaNumero.setText("");
                    NormalGameFragment.numero = "";
                    NormalGameFragment.signo = "+";
                    nuevaOperacion(c);
                }
            }
        });
    }

    public static void nuevaOperacion(Context c){
        MainActivity.chronometer.stop();
        pantallaOperaciones.setText(partida.generarOperacion());
        startChronometer(c);
    }

}
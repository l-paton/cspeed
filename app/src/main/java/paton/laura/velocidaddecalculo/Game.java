package paton.laura.velocidaddecalculo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private Fragment fragment;
    private Button btnCambiar;
    private static int tiempo = 0;
    public static TextView pantallaOperaciones, pantallaAciertos, pantallaFallos, screenTimeLimit;
    public static Partida partida;
    public static Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();

        Bundle data = this.getIntent().getExtras();
        tiempo = data.getInt("tiempo");

        if(data.getString("type").equalsIgnoreCase("normal")){
            fragment = new NormalGameFragment();
        }else{
            fragment = new InverseGameFragment();
        }

        chronometer = findViewById(R.id.chronometer1);
        btnCambiar = findViewById(R.id.btnCambio);
        screenTimeLimit = findViewById(R.id.timeLimit);
        pantallaOperaciones = findViewById(R.id.pantallaOperaciones);
        pantallaAciertos = findViewById(R.id.txtAciertos);
        pantallaFallos = findViewById(R.id.txtFallos);
        partida = new Partida();
        pantallaAciertos.setText(getString(R.string.aciertos, partida.getAciertos()));
        pantallaFallos.setText(getString(R.string.fallos, partida.getFallos()));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmenGameModality,fragment)
                .commit();

        startChronometer(getApplicationContext());
        limitTime();
        pantallaOperaciones.setText(partida.generarOperacion());
    }

    public void limitTime(){
        new CountDownTimer(tiempo*5, 1000){
            @Override
            public void onTick(long millisUntilFinished){
                screenTimeLimit.setText(String.valueOf(millisUntilFinished/1000));
            }
            @Override
            public void onFinish(){
                Game.chronometer.stop();
                showDialog();
                cancel();
            }
        }.start();
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
                    NormalGameFragment.numberScreen.setText("");
                    NormalGameFragment.number = "";
                    NormalGameFragment.symbol = "+";
                    newOperation(c);
                }
            }
        });
    }

    public static void newOperation(Context c){
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
        Game.chronometer.stop();
        finish();
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.save_game_question);
        builder.setCancelable(true);

        builder.setPositiveButton(
                R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton(
                R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
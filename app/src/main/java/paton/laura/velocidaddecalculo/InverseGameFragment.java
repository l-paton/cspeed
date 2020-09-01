package paton.laura.velocidaddecalculo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

public class InverseGameFragment extends Fragment {

    private Button btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4;
    private Button[] botones = new Button[4];

    public InverseGameFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inverse_game, container, false);

        btnOpcion1 = view.findViewById(R.id.btnOpcion1);
        btnOpcion2 = view.findViewById(R.id.btnOpcion2);
        btnOpcion3 = view.findViewById(R.id.btnOpcion3);
        btnOpcion4 = view.findViewById(R.id.btnOpcion4);

        botones[0] = btnOpcion1;
        botones[1] = btnOpcion2;
        botones[2] = btnOpcion3;
        botones[3] = btnOpcion4;

        generar();

        return view;
    }

    private void generar(){
        Random random = new Random();
        int posicion = random.nextInt(4);

        for(int i = 0; i < 4; i++){
            if(posicion == 3){
                botones[posicion].setText(String.valueOf(MainActivity.partida.getResultadoGenerado()));
                botones[posicion].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.partida.sumarAcierto(getContext());
                        generar();
                    }
                });
                posicion = 0;
            }
            else{
                textoBotones(botones[posicion]);
                botones[posicion].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.partida.sumarFallo(getContext());
                        generar();
                    }
                });
                posicion++;
            }
        }
    }

    private void textoBotones(Button b){
        Partida partidaFake = new Partida();
        partidaFake.generarOperacion();
        int resultadoFake = partidaFake.getResultadoGenerado();
        while(MainActivity.partida.getResultadoGenerado() == resultadoFake){
            partidaFake.generarOperacion();
            resultadoFake = partidaFake.getResultadoGenerado();
        }
        b.setText(String.valueOf(resultadoFake));
    }
}
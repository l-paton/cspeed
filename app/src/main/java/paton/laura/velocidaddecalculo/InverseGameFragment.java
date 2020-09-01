package paton.laura.velocidaddecalculo;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InverseGameFragment extends Fragment {

    private Button btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4;
    private Button[] botones = {btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4};

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
        return view;
    }

    private void textoBotones(Button b, String s){
        Partida partidaFake = new Partida();
        String operacion = partidaFake.generarOperacion();
        while(s.equals(operacion)){
            operacion = partidaFake.generarOperacion();
        }
        b.setText(operacion);
    }
}
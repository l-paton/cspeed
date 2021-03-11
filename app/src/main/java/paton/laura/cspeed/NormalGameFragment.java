package paton.laura.cspeed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class NormalGameFragment extends Fragment {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnEnter, btnClear, btnSigno;
    public static TextView numberScreen;
    public static String number = "", symbol = "+";

    public NormalGameFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_normal_game, container, false);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);
        btn7 = view.findViewById(R.id.btn7);
        btn8 = view.findViewById(R.id.btn8);
        btn9 = view.findViewById(R.id.btn9);
        btn0 = view.findViewById(R.id.btn0);
        btnEnter = view.findViewById(R.id.btnEnter);
        btnClear = view.findViewById(R.id.btnClear);
        btnSigno = view.findViewById(R.id.btnSigno);
        numberScreen = view.findViewById(R.id.pantallaNumero);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "1";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "2";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "3";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "4";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "5";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "6";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "7";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "8";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "9";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number += "0";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btnSigno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(symbol.equals("+")) symbol = "-";
                else symbol = "+";
                numberScreen.setText(symbol + String.valueOf(number));
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(symbol.equals("-")){
                    String aux = "-" + number;
                    number = aux;
                }

                try{
                    Game.partida.setResultadoIntroducido(Integer.parseInt(number));
                    if(Game.partida.comprobarResultado()){
                        Game.partida.sumarAcierto(getContext());
                    }else{
                        Game.partida.sumarFallo(getContext());
                    }

                }catch(Exception e){

                }

                number = "";
                numberScreen.setText("");
            }
        });

        return view;
    }
}
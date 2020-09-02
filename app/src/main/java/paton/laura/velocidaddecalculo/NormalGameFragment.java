package paton.laura.velocidaddecalculo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NormalGameFragment extends Fragment {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnEnter, btnClear, btnSigno;
    public static TextView pantallaNumero;
    public static String numero = "", signo = "+";

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
        pantallaNumero = view.findViewById(R.id.pantallaNumero);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "1";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "2";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "3";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "4";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "5";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "6";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "7";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "8";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "9";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero += "0";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numero = "";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btnSigno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signo.equals("+")) signo = "-";
                else signo = "+";
                pantallaNumero.setText(signo + String.valueOf(numero));
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(signo.equals("-")){
                    String aux = "-" + numero;
                    numero = aux;
                }

                try{
                    Game.partida.setResultadoIntroducido(Integer.parseInt(numero));
                    if(Game.partida.comprobarResultado()){
                        Game.partida.sumarAcierto(getContext());
                    }else{
                        Game.partida.sumarFallo(getContext());
                    }

                }catch(Exception e){

                }

                numero = "";
                pantallaNumero.setText("");
            }
        });

        return view;
    }
}
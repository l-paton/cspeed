package paton.laura.velocidaddecalculo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ButtonsFragment extends Fragment {

    private Button botonCambiar;
    private Fragment fragment;

    public ButtonsFragment() {
        fragment = new InverseGameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buttons, container, false);
        botonCambiar = view.findViewById(R.id.btnCambio);

        botonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmenGameModality,fragment)
                        .commit();

                if(fragment instanceof InverseGameFragment){
                    fragment = new NormalGameFragment();
                }else{
                    fragment = new InverseGameFragment();
                }
            }

        });

        return view;
    }
}
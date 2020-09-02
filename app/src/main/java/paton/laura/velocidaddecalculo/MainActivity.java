package paton.laura.velocidaddecalculo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        rg = findViewById(R.id.radioGroup);
    }

    public void Empezar(View view){
        Intent i = new Intent(this, Game.class);

        switch (rg.getCheckedRadioButtonId()){
            case R.id.radioButon1:
                i.putExtra("tiempo", 60000);
                break;
            case R.id.radioButton2:
                i.putExtra("tiempo", 30000);
                break;
            case R.id.radioButton3:
                i.putExtra("tiempo", 10000);
                break;
        }
        startActivity(i);
    }

}
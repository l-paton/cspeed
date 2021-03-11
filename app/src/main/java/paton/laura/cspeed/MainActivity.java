package paton.laura.cspeed;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    SwitchMaterial sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarGradiant(this);
        rg = findViewById(R.id.radioGroup);
        sw = findViewById(R.id.switchInverse);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.gradient);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
    public void Start(View view){
        Intent i = new Intent(this, Game.class);

        if(!sw.isChecked()){
            i.putExtra("type", "normal");
        }else{
            i.putExtra("type", "inverse");
        }
        switch (rg.getCheckedRadioButtonId()){
            case R.id.radio_button_1:
                i.putExtra("tiempo", 60000);
                break;
            case R.id.radio_button_2:
                i.putExtra("tiempo", 30000);
                break;
            case R.id.radio_button_3:
                i.putExtra("tiempo", 10000);
                break;
        }
        startActivity(i);
    }

}
package com.example.View;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends Activity {
    Button button;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
                Intent intent=new Intent(MainActivity.this,Slideshow.class);
                startActivity(intent);
                //startDisplay();
            }
        });
    }
}
    
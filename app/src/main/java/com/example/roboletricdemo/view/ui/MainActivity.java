package com.example.roboletricdemo.view.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.roboletricdemo.R;

public class MainActivity extends AppCompatActivity {

  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button button = (Button) findViewById(R.id.button_simple);
     textView = (TextView) findViewById(R.id.text_view);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
      }
    });

    textView.setText("loading");

  }

  public void updateUI(){
    textView.setText("complete");
  }

  @Override protected void onResume() {
    super.onResume();
    updateUI();
  }
}

package com.example.examen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.examen.R;

public class MainActivity extends AppCompatActivity {

    private EditText EditNom;
    private EditText EditMdp;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditNom = findViewById(R.id.nomClient);
        EditMdp = findViewById(R.id.MdpClient);
        checkBox = findViewById(R.id.NewClient);
        Button login = findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = EditNom.getText().toString();
                String mdp = EditMdp.getText().toString();
                Boolean isNewClient = checkBox.isChecked();
                SocketAsynchrone socketAsynchrone = new SocketAsynchrone(login,mdp,isNewClient);
                socketAsynchrone.execute();
                Intent intent = new Intent(MainActivity.this, ConsultActivity.class);
                startActivity(intent);
            }
        });
    }
}
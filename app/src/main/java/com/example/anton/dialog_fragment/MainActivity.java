package com.example.anton.dialog_fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    Button btnVnes = (Button) findViewById(R.id.btn_vnesiZbor);
    Button btnBaraj = (Button) findViewById(R.id.btn_Baraj);
    EditText zbor = (EditText) findViewById(R.id.edt_Zbor);
    EditText zboRot = (EditText) findViewById(R.id.edt_Opis);
    TextView prikaziZbor = (TextView) findViewById(R.id.txt_View);
    String zborString;
    String Document;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission
                .WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest
                    .permission
                    .WRITE_EXTERNAL_STORAGE},99);
        }
        btnBaraj = (Button) findViewById(R.id.btn_Baraj);
        btnBaraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zbor=(EditText) findViewById(R.id.edt_Zbor);
                String word=zbor.getText().toString();
                String definicija=procitajOpis(word);
                prikaziZbor=(TextView) findViewById(R.id.txt_View);
                prikaziZbor.setText(definicija);
            }
        });
        btnVnes=(Button) findViewById(R.id.btn_Baraj);
        btnVnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                NovFragment novFragmentfragment= new NovFragment();
                novFragmentfragment.show(fragmentManager,"nov_fragment");
                Log.i("TAG","Test");
            }
        });

        btnVnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vneseno = zbor.getText().toString();
                String opishano = zboRot.getText().toString();
                String opis_NaZbor = vneseno + "\t" + opishano;
                if (! Document.equals("") && ! opis_NaZbor.equals(""))
                {
                    zacuvajGoFajlot(opis_NaZbor);
                }}});}

    private String procitajOpis(String word) {
        String string = "";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),zborString);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] pieces = line.split("\t");
                if (pieces[0].equalsIgnoreCase(word.trim())) {
                    string = pieces[1];
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return string;}

    private void zacuvajGoFajlot(String vneseno){
        File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),Document);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream((file),true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(vneseno.toString()+"\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.close();
            Toast.makeText(this,"Документот е успешно зачуван!",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"Документот не е успешно зачуван!!",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"Документот не е успешно зачуван!",Toast.LENGTH_LONG).show();
        }}
    @Override
    public void onRequestPermissionsResult(int rqCode, String[] perm, int[] rqResult) {
        super.onRequestPermissionsResult(rqCode, perm, rqResult);
        switch (rqCode){
            case 9801:
                if (rqResult [0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Дозволено",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,"Недозволено",Toast.LENGTH_SHORT).show();
                    finish();
                }}}}
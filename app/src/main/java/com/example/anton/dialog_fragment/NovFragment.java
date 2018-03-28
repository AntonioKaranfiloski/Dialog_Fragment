package com.example.anton.dialog_fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class NovFragment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_nov, null);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        };

        DialogInterface.OnClickListener onClickListenerVtor = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int interfaces) {
                EditText edtZborot = (EditText) view.findViewById(R.id.edt_Zborot);
                EditText edtOpisot = (EditText) view.findViewById(R.id.edt_Opisot);
                String string = edtZborot.getText().toString() + "\t" + edtOpisot.getText().toString();
                zacuvajGoFajlot(string);
            }
        };
        return new AlertDialog.Builder(getActivity())
                .setTitle("Save to file")
                .setView(view)
                .setPositiveButton(android.R.string.ok, onClickListenerVtor)
                .setNegativeButton(android.R.string.cancel, onClickListener)
                .create();
    }
        private void zacuvajGoFajlot(String vneseno){
            File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"zborovi.txt");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream((file),true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.write(vneseno.toString()+"\n");
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }}}

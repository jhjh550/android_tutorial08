package com.example.c.t18_alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SHOW_DIALOG = 1;
    private static final int CUSTOM_DIALOG = 2;

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case CUSTOM_DIALOG:
                Dialog customDialog = new Dialog(this);
                customDialog.setContentView(R.layout.custom_dialog);
                TextView customText = (TextView) customDialog.findViewById(R.id.cutomText);
                customText.setText("customDialog - onCreated");
                return customDialog;

            case SHOW_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("종료 확인");
                builder.setMessage("어플리케이션을 종료하겠습니까?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,
                                "Destroy cancel", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                return dialog;
        }


        return super.onCreateDialog(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(SHOW_DIALOG);
            }
        });

        Button buttonCutomDialog = (Button)findViewById(R.id.buttonCutomDialog);
        buttonCutomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(CUSTOM_DIALOG);
            }
        });

        Button btnProgress1 = (Button)findViewById(R.id.buttonProgress1);
        btnProgress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = ProgressDialog.show(
                        MainActivity.this,"Title","Now Loading....", true, false);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                }).start();

            }
        });

        Button btnProgress2 = (Button)findViewById(R.id.buttonProgress2);
        btnProgress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Now Loading...");
                dialog.setCancelable(false);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setProgress(0);
                dialog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = 0;
                        while(progress<100){
                            progress++;
                            dialog.setProgress(progress);

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        dialog.dismiss();;
                    }
                }).start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

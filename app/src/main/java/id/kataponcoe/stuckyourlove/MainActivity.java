package id.kataponcoe.stuckyourlove;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun){

            Intent i = new Intent(MainActivity.this, Video.class);
            startActivity(i);
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("firstrun", false)
                .commit();


        findViewById(R.id.txtTentangGO).setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Alya, gw sayang sama Lu!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        if (v.getId() == R.id.txtTentangGO) {
            startActivity(new Intent(MainActivity.this, Video.class));
            this.finish();
        }
    }

    public void onBackPressed() {
        exit();//Pergi ke method exit
    }

    private void exit() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Alya Yakin pengen Keluar Aplikasi").setCancelable(false)
                // tidak bisa tekan tombol back
                // jika pilih yess
                .setPositiveButton("Iya Coe",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                // jika pilih no
                .setNegativeButton("Ngga Coe",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

    }


}


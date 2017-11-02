package id.kataponcoe.stuckyourlove

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstrun = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("firstrun", true)
        if (firstrun) {

            val i = Intent(this@MainActivity, Video::class.java)
            startActivity(i)
        }

        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
                .edit()
                .putBoolean("firstrun", false)
                .commit()


        txtTentangGO.setOnClickListener {
            val intent = Intent(this@MainActivity, Video::class.java)
            startActivity(intent)
        }

        val toolbar = toolbar as Toolbar
        setSupportActionBar(toolbar)

        val fab = fab as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Alya, gw sayang sama Lu!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onClick(v: View) {
        // TODO Auto-generated method stub

        if (v.id == R.id.txtTentangGO) {
            startActivity(Intent(this@MainActivity, Video::class.java))
            this.finish()
        }
    }

    override fun onBackPressed() {
        exit()//Pergi ke method exit
    }

    private fun exit() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Alya Yakin pengen Keluar Aplikasi").setCancelable(false)
                // tidak bisa tekan tombol back
                // jika pilih yess
                .setPositiveButton("Iya Coe"
                ) { dialog, id -> finish() }
                // jika pilih no
                .setNegativeButton("Ngga Coe"
                ) { dialog, id -> dialog.cancel() }.show()

    }


}
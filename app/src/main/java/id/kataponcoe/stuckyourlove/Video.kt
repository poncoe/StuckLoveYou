package id.kataponcoe.stuckyourlove

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.video_view.*

class Video : Activity() {

    private var myVideoView: VideoView? = null
    private var position = 0
    private var progressDialog: ProgressDialog? = null
    private var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Get the layout from video_main.xml
        setContentView(R.layout.video_view)

        if (mediaControls == null) {
            mediaControls = MediaController(this@Video)
        }

        // Find your VideoView in your video_main.xml layout
        val myVideoView = video_view as VideoView

        // Create a progressbar
        progressDialog = ProgressDialog(this@Video)
        // Set progressbar title
        progressDialog!!.setTitle("Stuck Your Love")
        // Set progressbar message
        progressDialog!!.setMessage("Sebentar ya Alya...")

        progressDialog!!.setCancelable(false)
        // Show progressbar
        progressDialog!!.show()

        try {
            myVideoView!!.setMediaController(mediaControls)
            myVideoView!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.gembulkucing))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

        myVideoView!!.requestFocus()
        myVideoView!!.setOnPreparedListener {
            progressDialog!!.dismiss()
            myVideoView!!.seekTo(position)
            if (position == 0) {
                myVideoView!!.start()
            } else {
                myVideoView!!.pause()
            }
        }

        myVideoView!!.setOnCompletionListener {
            val i = Intent(this@Video, MainActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("Position", myVideoView!!.currentPosition)
        myVideoView!!.pause()
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        position = savedInstanceState.getInt("Position")
        myVideoView!!.seekTo(position)
    }

    override fun onBackPressed() {

        val i = Intent(this@Video, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}
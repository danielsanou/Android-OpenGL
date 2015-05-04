package com.daniel.sanou.android.opengl.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class AndroidOpenGLActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Go fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*
            GLSurfaceView view = new GLSurfaceView(this);
            view.setRenderer(new OpenGLRenderer());
            setContentView(view);
        */

        OpenGLSurfaceView mGLView = new OpenGLSurfaceView(this);
        setContentView(mGLView);
    }
}

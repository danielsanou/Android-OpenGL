package com.daniel.sanou.android.opengl.main;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class OpenGLSurfaceView extends GLSurfaceView {

    private final OpenGLRenderer mRenderer;

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    public OpenGLSurfaceView(Context context){
        super(context);

        mRenderer = new OpenGLRenderer(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:{

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }
              //  mRenderer.setAngle( mRenderer.getAngle() + ((dx + dy) * TOUCH_SCALE_FACTOR));
                //mRenderer.setObjectRotation(0.15f);
                requestRender();
                break;
            }

            case MotionEvent.ACTION_DOWN :{

                mRenderer.incrIndice();
                requestRender();
                break;
            }

        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}

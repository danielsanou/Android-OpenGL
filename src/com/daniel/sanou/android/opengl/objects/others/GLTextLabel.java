package com.daniel.sanou.android.opengl.objects.others;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLUtils;
import com.daniel.sanou.android.opengl.objects.common.GLObject;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;


public class GLTextLabel implements GLObject{

    /** Half the width of a single texture target. Each target renders a single character. */
    private static final float HALF_WIDTH = 0.4f;

    /** The characters a text label can contain. Numeric labels were enough for my purposes. */
    private static final String CHARSET = "0123456789.-";
    // 3 floats per vertex; 4 vertices; 4 bytes per float.
    private static final ByteBuffer BYTE_BUFFER = ByteBuffer.allocateDirect(3 * 4 * 4);
    private static final FloatBuffer VERTEX_BUFFER = BYTE_BUFFER.asFloatBuffer();
    /** Stores the texture itself. */
    private static int sTexture = -1;
    /** One FloatBuffer holds the texture info for a single character in CHARSET. */
    private static FloatBuffer[] sTexBuffers;

    static {
        BYTE_BUFFER.order(ByteOrder.nativeOrder());
    }

    static {
        VERTEX_BUFFER.put(new float[]{-HALF_WIDTH, HALF_WIDTH, 0f,
                -HALF_WIDTH, -HALF_WIDTH, 0f,
                HALF_WIDTH, -HALF_WIDTH, 0f,
                HALF_WIDTH, HALF_WIDTH, 0f});
        VERTEX_BUFFER.position(0);
    }

    private String mText;

    public GLTextLabel(String text) {
        setText(text);
    }

    private static void initTexture(GL10 gl) {
        int color = (0x7f << 24) | (0xff << 16) | (0x00 << 8) | 0x00;

        // The numbers may take some tweaking. This creates a 512x32px bitmap,
        // which is enough to hold everything in CHARSET, but may need adjusting
        // if you add characters to CHARSET. Note the dimensions must be powers of two, or the texture won't render.
        Bitmap bitmap = Bitmap.createBitmap(512, 32, Bitmap.Config.ARGB_8888);

        // Fully transparent background.
        //bitmap.eraseColor(GlUtil.RGBA(0xff, 0xff, 0xff, 0x00));
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        // Again you may need to play with the numbers here. These values display
        // each character pretty much centred within its quad.
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        paint.setColor(color);

        // Draw the characters to the texture atlas's canvas.
        for (int i = 0; i < CHARSET.length(); ++i) {
            canvas.drawText("" + CHARSET.charAt(i), 24 * i + 4, 27, paint);
        }

        // Fairly standard OpenGL texture setup code below.
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);

        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        sTexture = textures[0];

        gl.glActiveTexture(GL10.GL_TEXTURE0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        // Clean up.
        bitmap.recycle();
    }

    private static void initTextureBuffers(GL10 gl) {
        // Again, you'll need to play with the numbers a little if you change CHARSET.
        // We need a texture buffer to hold co-ordinates for its character's position within
        // the texture atlas. Texture co-ordinates range from 0.0 - 1.0, with 0.0 being the
        // left-hand edge, and 1.0 being the right-hand edge of the entire atlas.
        final float ratio = 240f / 512f;

        // How large to make each texture buffer. 4 pairs of 2-float co-ordinates * 4 bytes per float.
        final int size = 8 * 4;

        int buffer_count = CHARSET.length();
        sTexBuffers = new FloatBuffer[buffer_count];
        for (int i = 0; i < buffer_count; ++i) {
            ByteBuffer buf = ByteBuffer.allocateDirect(size);
            buf.order(ByteOrder.nativeOrder());
            sTexBuffers[i] = buf.asFloatBuffer();
            float x1 = ratio * (i / buffer_count);
            float x2 = ratio * (i + 1f) / buffer_count;
            sTexBuffers[i].put(new float[]{x1, 0.0f,
                    x1, 1.0f,
                    x2, 1.0f,
                    x2, 0.0f});
            sTexBuffers[i].position(0);
        }
    }

    @Override
    public void draw(GL10 gl) {
        //creates or uses a named texture at index 0
        gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
        //sets the texture environment.
        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_COLOR, GL10.GL_BLEND);

        // setup texture parameters
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR_MIPMAP_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR_MIPMAP_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

        //creates a Paint object
        Paint yellowPaint = new Paint();
        //makes it yellow
        yellowPaint.setColor(Color.YELLOW);
        //sets the anti-aliasing for texts
        yellowPaint.setAntiAlias(true);
        //creates a new mutable bitmap, with 128px of width and height
        Bitmap textBitmap = Bitmap.createBitmap(128, 128, Bitmap.Config.ARGB_8888);
        //creates a new canvas that will draw into a bitmap instead of rendering into the screen
        Canvas bitmapCanvas = new Canvas(textBitmap);

        //draws a text at x=20px and y=20px in the canvas
        bitmapCanvas.drawText("41Post.com", 60, 60, yellowPaint);
        //any other Canvas drawing methods goes here...
        //.
        //.
        //.

        //assigns the OpenGL texture with the Bitmap
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, textBitmap, 0);
        //free memory resources associated with this texture
        textBitmap.recycle();

    }

    public void initialize(GL10 gl) {
        if (sTexture == -1) {
            initTexture(gl);
            initTextureBuffers(gl);
        }
    }

    public void setText(String text) {
        mText = text;
    }
}
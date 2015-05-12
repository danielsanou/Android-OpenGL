package com.daniel.sanou.android.opengl.objects.others.text;

import android.content.res.AssetManager;
import com.daniel.sanou.android.opengl.objects.common.GLObject;
import com.daniel.sanou.android.opengl.objects.others.text.utils.GLFonts;
import com.daniel.sanou.android.opengl.objects.others.text.utils.GLText;

import javax.microedition.khronos.opengles.GL10;

public class GLTextLabel extends GLText implements GLObject{

    private String font;
    private int policeSize;
    private int padding;
    private String labelText;
    private float positionX, positionY;

    private float colorRed;
    private float colorGreen;
    private float colorBlue;
    private float colorAlpha;

    public GLTextLabel(GL10 gl, AssetManager assets) {
        super(gl, assets);
        this.font = GLFonts.ROBOTO_REGULAR;
        this.policeSize = 42;
        this.padding = 2;
        this.labelText  = "Lorem ipsum";
        this.positionX = -2.3f;
        this.positionY = -3f;

        this.colorRed = 0.0f;
        this.colorGreen = 1.0f;
        this.colorBlue = 1.0f;
        this.colorAlpha = 1.0f;

        load(font, policeSize, padding, padding);  // Create Font (Height: 14 Pixels / X+Y Padding 2 Pixels)
    }

    public GLTextLabel(GL10 gl, AssetManager assets,String font, int policeSize,
                       int padding,String labelText, float positionX, float positionY,
                        float colorRed, float colorGreen, float colorBlue, float colorAlpha) {
        super(gl, assets);
        this.font = font;
        this.policeSize = policeSize;
        this.padding = padding;
        this.labelText = labelText;
        this.positionX = positionX;
        this.positionY = positionY;

        this.colorRed = colorRed;
        this.colorGreen = colorGreen;
        this.colorBlue = colorBlue;
        this.colorAlpha = colorAlpha;

        load(this.font, this.policeSize, this.padding, this.padding);  // Create Font (Height: 14 Pixels / X+Y Padding 2 Pixels)
    }

    @Override
    public void draw(GL10 gl){
        // enable texture + alpha blending
        // NOTE: this is required for text rendering! we could incorporate it into
        // the GLText class, but then it would be called multiple times (which impacts performance).
        gl.glEnable(GL10.GL_TEXTURE_2D);              // Enable Texture Mapping
        gl.glEnable(GL10.GL_BLEND);                   // Enable Alpha Blend
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);  // Set Alpha Blend Function

        // TEST: render the entire font texture
        gl.glColor4f( 1.0f, 1.0f, 1.0f, 1.0f );         // Set Color to Use
        drawTexture(1, 1);            // Draw the Entire Texture

        // TEST: render some strings with the font
        begin(colorRed, colorGreen, colorBlue, colorAlpha);         // Begin Text Rendering (Set Color BLUE)
        draw(labelText, positionX, positionY);        // Draw Test String
        //draw("The End.", 0,-3.5f);//50, 150 + getCharHeight());  // Draw Test String
        end();                                   // End Text Rendering

        // disable texture + alpha
        gl.glDisable( GL10.GL_BLEND );                  // Disable Alpha Blend
        gl.glDisable( GL10.GL_TEXTURE_2D );             // Disable Texture Mapping
    }
}

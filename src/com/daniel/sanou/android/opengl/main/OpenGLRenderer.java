package com.daniel.sanou.android.opengl.main;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import com.daniel.sanou.android.opengl.objects.common.GLObject;
import com.daniel.sanou.android.opengl.objects.likeglut.*;
import com.daniel.sanou.android.opengl.objects.others.Roses;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    // Ambient light
    private final float[] mat_ambient = { 0.2f, 0.3f, 0.4f, 1.0f };
    // Parallel incident light
    private final float[] mat_diffuse = { 0.4f, 0.6f, 0.8f, 1.0f };
    // The highlighted area
    private final float[] mat_specular = { 0.2f * 0.4f, 0.2f * 0.6f, 0.2f * 0.8f, 1.0f };
    private float objectRotation = 0.0f;
    private float objRotationXAxis = 1.0f, objRotationYAxis = 1.0f, objRotationZAxis = 1.0f;
    private float objTranslationXAxis = 0.0f, objTranslationYAxis = 0.0f,objTranslationZAxis = -10.0f;
    private GLObject[] mObjects;
    private int indice;
    private FloatBuffer mat_ambient_buf;
    private FloatBuffer mat_diffuse_buf;
    private FloatBuffer mat_specular_buf;

    private float mLightX = 10f, mLightY = 10f, mLightZ = 10f;
    //The position of the light source
    private float[] light_position = {mLightX, mLightY, mLightZ, 0.0f};
    private FloatBuffer mat_posiBuf;

    public OpenGLRenderer(){
        indice = 0;
        initObjectsList();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // On the perspective correction
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        // Background: Black
        gl.glClearColor(0, 0.0f, 0.0f, 0.0f);
        // Start the smooth shading
        gl.glShadeModel(GL10.GL_SMOOTH);
        // Reset the depth buffer
        gl.glClearDepthf(1.0f);
        // Start the depth test
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // Type the depth test
        gl.glDepthFunc(GL10.GL_LEQUAL);

        initBuffers();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);

        // Texture of material
        gl.glEnable(GL10.GL_COLOR_MATERIAL);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambient_buf);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_diffuse_buf);
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat_specular_buf);
        // Specular exponent 0~128 less rough
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 96.0f);
        //The position of the light source
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, mat_posiBuf);

        /*================================== Objects draw logic ========================================*/
        gl.glTranslatef(objTranslationXAxis, objTranslationYAxis, objTranslationZAxis);
        gl.glRotatef(objectRotation, objRotationXAxis, objRotationYAxis, objRotationZAxis);
        //Objects color
        gl.glColor4f(1, 0, 0, 1);
        mObjects[indice].draw(gl);
        objectRotation -= 0.65f;
        /*================================== Objects draw logic ========================================*/
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    private void initBuffers() {
        ByteBuffer bufTemp = ByteBuffer.allocateDirect(mat_ambient.length * 4).order(ByteOrder.nativeOrder());
        mat_ambient_buf = bufTemp.asFloatBuffer(); mat_ambient_buf.put(mat_ambient);
        mat_ambient_buf.position(0);

        bufTemp = ByteBuffer.allocateDirect(mat_diffuse.length * 4).order(ByteOrder.nativeOrder());
        mat_diffuse_buf = bufTemp.asFloatBuffer(); mat_diffuse_buf.put(mat_diffuse);
        mat_diffuse_buf.position(0);

        bufTemp = ByteBuffer.allocateDirect(mat_specular.length * 4).order(ByteOrder.nativeOrder());
        mat_specular_buf = bufTemp.asFloatBuffer(); mat_specular_buf.put(mat_specular);
        mat_specular_buf.position(0);

        bufTemp = ByteBuffer.allocateDirect(light_position.length * 4).order(ByteOrder.nativeOrder());
        mat_posiBuf = bufTemp.asFloatBuffer();
        mat_posiBuf.put(light_position);
        mat_posiBuf.position(0);
    }

    private void initObjectsList() {
        this.mObjects = new GLObject[]{
                new Disk(),
                new Cone(),
                new Cylinder(),
                new Tetrahedron(),
                new Cube(),
                new Octahedron(),
                new Dodecahedron(),
                new Icosahedron(),
                new Sphere(),
                new Torus(),
                new Roses()
        };
    }

    public float getObjectRotation() {
        return objectRotation;
    }

    public void setObjectRotation(float objectRotation) {
        this.objectRotation = objectRotation;
    }

    public float getObjRotationXAxis() {
        return objRotationXAxis;
    }

    public void setObjRotationXAxis(float objRotationXAxis) {
        this.objRotationXAxis = objRotationXAxis;
    }

    public float getObjRotationYAxis() {
        return objRotationYAxis;
    }

    public void setObjRotationYAxis(float objRotationYAxis) {
        this.objRotationYAxis = objRotationYAxis;
    }

    public float getObjRotationZAxis() {
        return objRotationZAxis;
    }

    public void setObjRotationZAxis(float objRotationZAxis) {
        this.objRotationZAxis = objRotationZAxis;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = ((indice > 10) || (indice < 0 )) ? 0 : indice;
    }

    public void incrIndice(){
       indice = (indice+1) % 11;
    }
}

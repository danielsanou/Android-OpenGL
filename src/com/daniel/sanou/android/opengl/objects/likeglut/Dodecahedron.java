package com.daniel.sanou.android.opengl.objects.likeglut;

public class Dodecahedron extends ObjectLikeGLUT {

    float radius;
    float[][] vertex;

    private static final int indices[] = {
            0,8,9, 0,9,4, 0,4,16, 0,12,13,
            0,13,1, 0,1,8, 0,16,17, 0,17,2,
            0,2,12, 8,1,18, 8,18,5, 8,5,9,
            12,2,10, 12,10,3, 12,3,13, 16,4,14,
            16,14,6, 16,6,17, 9,5,15, 9,15,14,
            9,14,4, 6,11,10, 6,10,2, 6,2,17,
            3,19,18, 3,18,1, 3,1,13, 7,15,5,
            7,5,18, 7,18,19, 7,11,6, 7,6,14,
            7,14,15, 7,19,3, 7,3,10, 7,10,11
    };

    public Dodecahedron(){
        super();
        radius = 1.5f;
        initBuffAndPutV();
    }

    public Dodecahedron(float radius){
        super();
        this.radius = radius > 0.0f ? radius : 0.1f;
        initBuffAndPutV();
    }

    private void initBuffAndPutV() {
        initVertex();
        vertexNumber = indices.length;
        bufferSize = indices.length * 3 * 4;
        initBuffer();
        putVertex();
    }

    @Override
    public void putVertex() {
        for (int i=0; i<indices.length; i++) {
            vertexBuffer.put(vertex[indices[i]]);
        }
    }

    public void initVertex(){

        float a = (float) (1.0f/Math.sqrt(3.0f))* radius;
        float b = (float) Math.sqrt((3.0f-Math.sqrt(5.0f))/6.0f)* radius;
        float c = (float) Math.sqrt((3.0f+Math.sqrt(5.0f))/6.0f)* radius;

        vertex = new float[][] {
                {a,a,a}, {a,a,-a}, {a,-a,a}, {a,-a,-a},
                {-a,a,a}, {-a,a,-a}, {-a,-a,a}, {-a,-a,-a},
                {b,c,0.0f}, {-b,c,0.0f}, {b,-c,0.0f}, {-b,-c,0.0f},
                {c,0.0f,b}, {c,0.0f,-b}, {-c,0.0f,b}, {-c,0.0f,-b},
                {0.0f,b,c}, {0.0f,-b,c}, {0.0f,b,-c}, {0.0f,-b,-c}
        };
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius > 0.0f ? radius :0.1f;
        initBuffAndPutV();
    }
}

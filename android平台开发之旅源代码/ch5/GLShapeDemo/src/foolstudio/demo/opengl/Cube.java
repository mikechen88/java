package foolstudio.demo.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube {

	private static final int one = 0x10000;

	private static final int vertices[] = {
		-one, -one, -one,
		one, -one, -one,
		one,  one, -one,
		-one,  one, -one,
		-one, -one,  one,
		one, -one,  one,
		one,  one,  one,
		-one,  one,  one,
	};

	private static final int colors[] = {
		0,    0,    0,  one,
		one,    0,    0,  one,
		one,  one,    0,  one,
		0,  one,    0,  one,
		0,    0,  one,  one,
		one,    0,  one,  one,
		one,  one,  one,  one,
		0,  one,  one,  one,
	};

	private static final byte indices[] = {
		0, 4, 5,    0, 5, 1,
		1, 5, 6,    1, 6, 2,
		2, 6, 7,    2, 7, 3,
		3, 7, 4,    3, 4, 0,
		4, 7, 6,    4, 6, 5,
		3, 0, 1,    3, 1, 2
	};

    private IntBuffer mVertexBuffer = null;
    private IntBuffer mColorBuffer = null;
    private ByteBuffer mIndexBuffer = null;

    //初始化立方体
    public Cube() {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asIntBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asIntBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    //绘制方法
    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, 
        				  mIndexBuffer);
    }
};

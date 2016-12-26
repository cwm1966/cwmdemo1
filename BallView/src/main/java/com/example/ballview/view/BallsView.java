package com.example.ballview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class BallsView extends View {

	private static final String TAG = "BallsView";
	private int height;
	private int width;
	private float x;
	private float y;
	private float radius = 40;
	private boolean onBall;

	public BallsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// д�߼�������
		initView();
	}

	/**
	 * xml��ʹ�õ�ʱ��
	 * 
	 * @param context
	 */
	public BallsView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	// ������new BallsView();
	public BallsView(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// ��ȡ��ǰ�ؼ��ĸ߶�
		height = this.getHeight();
		// ��ȡ��ǰ�ؼ��Ŀ��
		width = this.getWidth();

		x = width / 2;
		y = height / 2;

	}

	boolean flag = true;
	// canvas ����
	private Bitmap bitmap;
	private int bh;
	private int bw;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
/*
		if (flag) {
			bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.a17);
			bh = bitmap.getHeight() / 2;
			bw = bitmap.getWidth() / 2;
			flag = false;
		}*/

		// ��������
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		canvas.drawCircle(x, y, radius, paint);
		//canvas.drawBitmap(bitmap, x - bw, y - bh, null);
		//Log.i(TAG, "onDraw......." + "x" + x + "y" + y);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ��ȡ����ǰλ��
			float downX = event.getX();
			float downY = event.getY();
			onBall = isOnBall(downX, downY);

			Toast.makeText(getContext(), onBall + "", Toast.LENGTH_SHORT).show();

			break;
		case MotionEvent.ACTION_MOVE:
			if (onBall) {
				x = event.getX();
				y = event.getY();
				// ���õ�onDraw
				// invalidate();
				postInvalidate();
			}

			break;
		case MotionEvent.ACTION_UP:

			break;
		case MotionEvent.ACTION_CANCEL:

			break;

		default:
			break;
		}

		return true;
	}

	private void initView() {

	}

	/**
	 * ���㵱ǰ��ָ�Ƿ�����С����
	 * 
	 * @param downX
	 * @param downY
	 * @return
	 */
	public boolean isOnBall(float downX, float downY) {

		double sqrt = Math.sqrt((downX - x) * (downX - x) + (downY - y)
				* (downY - y));
		if (sqrt <= radius) {
			return true;
		}
		return false;
	}
}

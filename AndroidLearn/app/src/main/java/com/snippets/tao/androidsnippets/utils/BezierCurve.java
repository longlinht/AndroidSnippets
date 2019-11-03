package com.snippets.tao.androidsnippets.utils;

import android.support.annotation.NonNull;

/**
 * Created by Tao He on 16-7-13.
 * Email: hetaoof@gmail.com
 */
public class BezierCurve {
    private float[] mSamplePt;

    public BezierCurve(@NonNull float[] controlPoints, int sampleCount) {
        mSamplePt = new float[sampleCount * 2];
        Init(controlPoints);
    }

    public float valueX(float v) {
        int ptNum = mSamplePt.length / 2;
        int i = (int) (v * ptNum + 0.5);

        if (i < 0)
            i = 0;
        else if (i >= ptNum)
            i = ptNum - 1;

        return mSamplePt[i * 2];
    }

    public float valueY(float v) {
        int ptNum = mSamplePt.length / 2;
        int i = (int) (v * ptNum + 0.5);

        if (i < 0)
            i = 0;
        else if (i >= ptNum)
            i = ptNum - 1;
        return mSamplePt[i * 2 + 1];
    }

    private void Init(float[] controlPoints) {
        final int length = controlPoints.length;
        if (length % 2 != 0 || length < 2)
            throw new IllegalArgumentException("Illegal argument count!");

        final int count = length / 2;
        float[] px = new float[count];
        float[] py = new float[count];
        for (int i = 0, j = 0; i < length; i += 2, j++) {
            px[j] = controlPoints[i];
            py[j] = controlPoints[i + 1];
        }

        float[] coefficientX = px;
        initCoefficient(px, coefficientX, count);
        float[] coefficientY = py;
        initCoefficient(py, coefficientY, count);

        final float[] samplePt = this.mSamplePt;
        samplePt[0] = coefficientX[0];
        samplePt[1] = coefficientY[0];

        float[] a = new float[count];
        final int samplePtLength = samplePt.length;
        final float samplePtLengthF = samplePtLength;
        for (int i = 2; i < samplePtLength; i += 2) {
            float v = i / samplePtLengthF;

            final int n = count - 1;
            float v1 = v / (1 - v);
            a[0] = (float) Math.pow(1 - v, n);
            for (int j = 1; j < count; j++) {
                a[j] = a[j - 1] * v1;
            }
            samplePt[i] = sumProduct(coefficientX, a, count);
            samplePt[i + 1] = sumProduct(coefficientY, a, count);
        }

    }

    public static void initCoefficient(float[] controlPoints, float[] coefficient, final int length) {
        coefficient[0] = controlPoints[0];
        for (int i = 1, j = 1; i < length; i++) {
            j = j * (length - i) / i;
            coefficient[i] = controlPoints[i] * j;
        }
    }

    public static float sumProduct(float[] a, float[] b, final int count) {
        float ret = 0;
        for (int i = 0; i < count; i++) {
            ret += a[i] * b[i];
        }
        return ret;
    }
}

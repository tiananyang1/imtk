package com.lwansbrough.RCTCamera;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.facebook.react.bridge.ReactApplicationContext;

/* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/RCTSensorOrientationChecker.class */
public class RCTSensorOrientationChecker {
    private SensorManager mSensorManager;
    int mOrientation = 0;
    private RCTSensorOrientationListener mListener = null;
    private SensorEventListener mSensorEventListener = new Listener();

    /* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/RCTSensorOrientationChecker$Listener.class */
    private class Listener implements SensorEventListener {
        private Listener() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            if (f < 5.0f && f > -5.0f && f2 > 5.0f) {
                RCTSensorOrientationChecker.this.mOrientation = 0;
            } else if (f < -5.0f && f2 < 5.0f && f2 > -5.0f) {
                RCTSensorOrientationChecker.this.mOrientation = 3;
            } else if (f < 5.0f && f > -5.0f && f2 < -5.0f) {
                RCTSensorOrientationChecker.this.mOrientation = 2;
            } else if (f > 5.0f && f2 < 5.0f && f2 > -5.0f) {
                RCTSensorOrientationChecker.this.mOrientation = 1;
            }
            if (RCTSensorOrientationChecker.this.mListener != null) {
                RCTSensorOrientationChecker.this.mListener.orientationEvent();
            }
        }
    }

    public RCTSensorOrientationChecker(ReactApplicationContext reactApplicationContext) {
        this.mSensorManager = (SensorManager) reactApplicationContext.getSystemService("sensor");
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void onPause() {
        this.mSensorManager.unregisterListener(this.mSensorEventListener);
    }

    public void onResume() {
        SensorManager sensorManager = this.mSensorManager;
        sensorManager.registerListener(this.mSensorEventListener, sensorManager.getDefaultSensor(1), 3);
    }

    public void registerOrientationListener(RCTSensorOrientationListener rCTSensorOrientationListener) {
        this.mListener = rCTSensorOrientationListener;
    }

    public void unregisterOrientationListener() {
        this.mListener = null;
    }
}

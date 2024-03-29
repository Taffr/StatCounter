package taffer.statcounter.Model;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

/**
 * Detects if the phone is shaking.
 */
public class ShakeDetector implements Detector {

    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private long mShakeTimestamp = 0;

    /**
     * Handles and evaluates an event.
     * @param event, SensorEvent.
     * @return int, SUCCESS or FAIL.
     */
    @Override
    public int detectEvent(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                final long now = System.currentTimeMillis();
                // ignore shake events too close to each other (500ms)
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return FAIL;
                }else{
                    mShakeTimestamp = now;
                    return SUCCESS;
                }
            }
        }
        return FAIL;
    }
}

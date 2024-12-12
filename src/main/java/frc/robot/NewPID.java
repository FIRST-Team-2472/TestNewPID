package frc.robot;



public class NewPID {
    
    private double Kp, Ki,I, Kd, error, lasterror, timeIncrement, time, Dtime, timeremaining, lastSensorRead, decreaseSpeed,   predictionPoint, speedNeeded;

    public NewPID(double Kp, double Ki, double Kd, double time, double Dtime, double lastSensorRead){
    this.Kd = Kd;
    this.Ki = Ki;
    this.Kp = Kp;
    this.time = time;
    this.Dtime = Dtime;
    this.lastSensorRead = lastSensorRead;
    timeremaining = time*50;
    I = 0;
    error = 0;
    timeIncrement = 1/time/50; // 50 = 1/code refresh rate (per second)
}
    public double calculatePID(double setpoint, double sensorRead){
        lasterror = error;
        error = setpoint - sensorRead;
        if (error != 0 && I<1 && I>-1){
            if (error < 0) 
            I += -timeIncrement;
            else 
            I += timeIncrement;
        };
        if (Math.abs((sensorRead - lastSensorRead)*Dtime) > Math.abs(setpoint)){
            predictionPoint = setpoint - ((sensorRead - lastSensorRead)*Dtime);
        } else predictionPoint = 0;
        timeremaining -= timeIncrement;
        speedNeeded = Math.min(1, Math.max(-1, ((error * Kp) + Ki*I) + (predictionPoint*Kd)));
        //if (speedNeeded < .017 && speedNeeded > 0) speedNeeded =.017;
        //if (speedNeeded > -.017 && speedNeeded < 0) speedNeeded = -.017;
        return speedNeeded;
    }
}
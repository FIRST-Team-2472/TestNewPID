package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class ExampleSubsystem extends SubsystemBase {
    private CANSparkMax pushMotor = new CANSparkMax(PushMotor.kPushMotorId, MotorType.kBrushless);
    public ExampleSubsystem() {

        // make sure all of them have the same settings in case we grabbed one with presets
        pushMotor.restoreFactoryDefaults();
        pushMotor.setIdleMode(com.revrobotics.CANSparkBase.IdleMode.kBrake);
    }

    @Override
    public void periodic() {}

    public void runPushMotor(double motorSpeed) {
        pushMotor.set(motorSpeed);
    }
    public double getMotorSpeed(){
      return pushMotor.getEncoder().getVelocity();
    }
}

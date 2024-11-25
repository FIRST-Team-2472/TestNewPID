package frc.robot.commands;


import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;
import frc.robot.NewPID;

public class ExampleCommand extends Command { 

    private NewPID newPID;
    private ExampleSubsystem exampleSubsystem;
    private double motorSpeed, truespeed, wantedspeed;
    private Supplier<Double> speed;
  



    public ExampleCommand(ExampleSubsystem exampleSubsystem, Supplier<Double> speed) {
        
        this.exampleSubsystem = exampleSubsystem;
        this.speed = speed;

        addRequirements(exampleSubsystem);
    }

    @Override
    public void initialize() {
      super.initialize();
        newPID = new NewPID(.000, .05, 0, .2, exampleSubsystem.getMotorSpeed());
    }

    @Override
    public void execute() {
        truespeed = exampleSubsystem.getMotorSpeed();
        System.out.println("Joystick value" + speed.get());
        //wantedspeed = Math.abs(speed.get()) > .1 ? speed.get()*-200 : 0;
        wantedspeed = 48;
        motorSpeed = newPID.calculatePID(wantedspeed, truespeed);
        System.out.println("Desired motor speed" + motorSpeed);
        System.out.println("wanted speed" + wantedspeed);
        exampleSubsystem.runPushMotor(motorSpeed);
        super.execute();
        }
    

    @Override
    public void end(boolean interrupted) {
       
       exampleSubsystem.runPushMotor(0);
       super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

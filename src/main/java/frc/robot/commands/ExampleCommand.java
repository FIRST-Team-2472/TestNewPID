package frc.robot.commands;


import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;
import frc.robot.NewPID;

public class ExampleCommand extends Command { 

    private NewPID newPID;
    private PIDController pidController = new PIDController(.0000, .0001, .000);
    private ExampleSubsystem exampleSubsystem;
    private double motorSpeed, truespeed, wantedspeed;
    private Supplier<Double> speed;
  



    public ExampleCommand(ExampleSubsystem exampleSubsystem, Supplier<Double> speed) {
        
        this.exampleSubsystem = exampleSubsystem;
        this.speed = speed;
        motorSpeed = 0;
        addRequirements(exampleSubsystem);
    }

    @Override
    public void initialize() {
      super.initialize(); // KI = .02 & TIME = .2
        newPID = new NewPID(.00001, .02, 0.001, 60, 0.01, exampleSubsystem.getMotorSpeed());
    }

    @Override
    public void execute() {
        truespeed = exampleSubsystem.getMotorSpeed();
        motorSpeed = pidController.calculate(truespeed, 48);
        /*System.out.println("Joystick value" + speed.get());
        //wantedspeed = Math.abs(speed.get()) > .1 ? speed.get()*-200 : 0;
        wantedspeed = 48;
        motorSpeed += (newPID.calculatePID(wantedspeed, truespeed)*.1);
        System.out.println("Desired motor speed" + motorSpeed);
        System.out.println("wanted speed" + wantedspeed); */
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

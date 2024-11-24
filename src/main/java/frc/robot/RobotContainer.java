package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
  XboxController xbox = new XboxController(OperatorConstants.kXboxControllerPort);
  
  
  public RobotContainer() {
    exampleSubsystem.setDefaultCommand(new ExampleCommand(exampleSubsystem, () -> xbox.getLeftY()));
   
    configureBindings();
  }

  private void configureBindings() {
  
  }

  
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class GyroTurn extends CommandBase {
  /**
   * Creates a new GyroTurn.
   */
  DriveBase m_DriveBase;
  double target;
  double error;
  double now;
  boolean direction;
  double dirdir = 1;
  double speed;
  public GyroTurn(DriveBase driveBase,double tar,boolean dir) {
    /*
      if dir is equal to false its to the left direction
    */
    m_DriveBase = driveBase;
    target = tar;
    direction = dir;
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_DriveBase.resetGyro();
    //target = Math.abs(target);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    now = Math.abs(m_DriveBase.getGyroAngle());
    System.out.println(now);
    error = target-now;
    //speed = (error/target)*0.2+0.1;

    /*if(target>45 && target<100){
      error = target-now;
      speed = (error/target)*0.2+0.1;  
    }else if(target>0 && target<25){
      error = target-now;
      speed = (error/target)*0.2;  
    }else{
      error = target-now;
      speed = (error/target)*0.2+0.2;  
    }*/
    if(error > 0){
      speed = (error/target)*0.2+0.098; 
    }else{
      speed = (error/target)*-0.2+0.098; 
    }

   if(!direction){
      speed=-speed;//to left if dir is false
    }

    m_DriveBase.setRight(speed);
    m_DriveBase.setLeft(-speed);

  }

  public void brake(){
    if(direction){
    m_DriveBase.setRight(-0.07);
    m_DriveBase.setLeft(0.07);
    }else{
      
    m_DriveBase.setRight(0.07);
    m_DriveBase.setLeft(-0.07);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    brake();
    Timer.delay(0.05);

    m_DriveBase.setLeft(0.0);
    m_DriveBase.setRight(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(error)<2){
      return true;
    }
    return false;
  }
}

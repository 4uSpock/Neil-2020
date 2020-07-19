/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Roulette;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Roulette;

public class RouletteOpen extends CommandBase {
  /**
   * Creates a new RouletteOpen.
   */

  Roulette m_Roulette;
  Timer time;
  public RouletteOpen(Roulette RU) {
    m_Roulette = RU;
    time = new Timer();
    addRequirements(RU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.reset();
    time.stop();
    time.reset();
    time.start();
    m_Roulette.setRoulettespeed(0.4);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("0.4");
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   m_Roulette.setRoulettespeed(0.15);
   System.out.println("end");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get()>1){
      System.out.println("done");
      return true;
    }
    return false;
  }
}

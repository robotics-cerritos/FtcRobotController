package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "Outtake Driver")
public class outtake_drive_code extends LinearOpMode {
    //Declare motor variables
    private DcMotor leftM; //Left Motor
    private DcMotor rightM; //Right Motor
    @Override
    public void runOpMode() throws InterruptedException{
//Connect motor variables to motors on hub
        leftM = hardwareMap.dcMotor.get("leftMotor");
        rightM = hardwareMap.dcMotor.get("rightMotor");
//Reverse direction of one motor (will change later)
        leftM.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive()){
            leftM.setPower(gamepad1.right_trigger);
            rightM.setPower(gamepad1.right_trigger);
        }
    }
}
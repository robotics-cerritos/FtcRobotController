package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Test Code")
public class testCode extends LinearOpMode {

    private DcMotor motor1;
    private DcMotor motor2;
    private Servo servo1;
    private Servo servo2;

    @Override
    public void runOpMode() throws InterruptedException{
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        servo1 = hardwareMap.servo.get("servo1");
        servo2 = hardwareMap.servo.get("servo2");

        while(opModeIsActive()){
            motor1.setPower(gamepad1.right_stick_y);
            motor2.setPower(gamepad1.left_stick_y);
            if(gamepad1.x){
                servo1.setPosition(0.2);
            }
            else if(gamepad1.b){
                servo1.setPosition(0.8);
            }

            if(gamepad1.dpad_left){
                servo2.setPosition(0.2);
            }
            else if(gamepad1.dpad_right){
                servo2.setPosition(0.8);
            }
            idle();
        }
    }
}

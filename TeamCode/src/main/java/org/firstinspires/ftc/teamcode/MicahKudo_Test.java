package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MicahKudo Test")
public class MicahKudo_Test extends LinearOpMode {
    private DcMotor motor1;
    private DcMotor motor2;
    private Servo servo;

    private static final double RAISE_MAX = 1000;
    private static final double RAISE_MIN= 10;
    @Override
 public void runOpMode() throws InterruptedException{
        motor1 = hardwareMap.dcMotor.get("m1");
        motor2 = hardwareMap.dcMotor.get("m2");
        servo = hardwareMap.servo.get("servo");

        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while(opModeIsActive()){
            motor1.setPower(gamepad1.right_stick_y);

            if(gamepad1.dpad_left){
                servo.setPosition(0.2); //Corresponds to 36 degrees
            }
            else if(gamepad1.dpad_right){
                servo.setPosition(0.8); //Corresponds to 144 degrees
            }
        }
            else if(gamepad1.dpad_up){
                servo.setPosition(0.5); //Corresponds to 90 degrees
            }
            if(motor2.getCurrentPosition() > RAISE_MAX || motor2.getCurrentPosition() < RAISE_MIN){
                motor2.setPower(0);
            }
            else{
                motor2.setPower(gamepad1.left_stick_y);
            }


            idle();
    }
}

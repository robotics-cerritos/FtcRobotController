package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Special Systems Driver")
public class special_systems_driver_code extends LinearOpMode {
    //Declare motor variables
    private DcMotor grabberM; //Grabber Motor
    private DcMotor raiseM; //Raising Motor

    //Declaring motor encoder limits (Definitely needs to be changed based on tests)
    private static final double GRABBER_MAX = 1000;
    private static final double GRABBER_MIN = 10;
    private static final double RAISE_MAX = 1000;
    private static final double RAISE_MIN = 10;
    private static final double ROTATE_MIN = 800; //The minimum amount the raise motor should be at for the arm to be above the robot and ready to rotate

    //Declare servo variables
    private Servo rotateS; //Rotating servo

    //Declaring servo positions
    private static final double FACE_SIDE_CHASSIS = 0.2;
    private static final double FACE_BACK_CHASSIS = 0.8;

    @Override
    public void runOpMode() throws InterruptedException{
        //Connect motor variables to motors on hub
        grabberM = hardwareMap.dcMotor.get("grabberMotor");
        raiseM = hardwareMap.dcMotor.get("raiseMotor");

        //Reverse directions of motors (test to figure out which motors must be reversed)
        grabberM.setDirection(DcMotorSimple.Direction.REVERSE);
        raiseM.setDirection(DcMotorSimple.Direction.REVERSE);

        //Activate encoders
        grabberM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        raiseM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Connect servo variable to servo on hub
        rotateS = hardwareMap.servo.get("rotateServo");

        waitForStart();

        while(opModeIsActive()){

            if(raiseM.getCurrentPosition() > RAISE_MAX || raiseM.getCurrentPosition() < RAISE_MIN){
                raiseM.setPower(0);
            }
            else{
                raiseM.setPower(-gamepad2.right_stick_y);
            }

            if(grabberM.getCurrentPosition() > GRABBER_MAX || grabberM.getCurrentPosition() < GRABBER_MIN){
                grabberM.setPower(0);
            }
            else{
                grabberM.setPower(gamepad2.right_trigger-gamepad2.left_trigger);
            }

            if(raiseM.getCurrentPosition() > ROTATE_MIN){
                if(gamepad2.dpad_left){
                    rotateS.setPosition(FACE_SIDE_CHASSIS);
                }
                else if(gamepad2.dpad_down){
                    rotateS.setPosition(FACE_BACK_CHASSIS);
                }
            }

            idle();
        }
    }
}

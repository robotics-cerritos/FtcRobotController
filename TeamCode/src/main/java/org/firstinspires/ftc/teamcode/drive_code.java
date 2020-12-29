package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class drive_code extends LinearOpMode {
    //Drive Variables-------------------------------------------------------------------------------
    //Drive Motors
    private DcMotor m2;
    private DcMotor m4;
    private DcMotor m1;
    private DcMotor m3;

    //Drive Power
    private double power = 1.0;

    //Outtake Variables-----------------------------------------------------------------------------
    //Outtake Motors
    private DcMotor leftOM; //Left Outtake Motor
    private DcMotor rightOM; //Right Outtake Motor

    //Special System Variables----------------------------------------------------------------------
    //Special System Motors
    private DcMotor grabberM; //Grabber Motor
    private DcMotor raiseM; //Raising Motor

    //Special System Motor Encoder Limits (Definitely needs to be changed based on tests)
    private static final double GRABBER_MAX = 1000;
    private static final double GRABBER_MIN = 10;
    private static final double RAISE_MAX = 1000;
    private static final double RAISE_MIN = 10;
    private static final double ROTATE_MIN = 800; //The minimum amount the raise motor can be at with the arm still above the robot and ready to rotate

    //Special System Servo
    private Servo rotateS; //Rotating servo

    //Special System Servo Positions
    private static final double FACE_SIDE_CHASSIS = 0.2;
    private static final double FACE_BACK_CHASSIS = 0.8;

    //Intake Variables------------------------------------------------------------------------------
    //Intake Motor
    private DcMotor intakeMotor;

    //Intake Servo
    private Servo intakeServo;


    @Override
    public void runOpMode() throws InterruptedException{
        //Drive Setup-------------------------------------------------------------------------------
        m2 = hardwareMap.dcMotor.get("m2");
        m4 = hardwareMap.dcMotor.get("m4");
        m1 = hardwareMap.dcMotor.get("m1");
        m3 = hardwareMap.dcMotor.get("m3");

        // Reverse some of the drive motors
        m2.setDirection(DcMotor.Direction.REVERSE);
        m4.setDirection(DcMotor.Direction.REVERSE);

        //Outtake Setup-----------------------------------------------------------------------------
        leftOM = hardwareMap.dcMotor.get("leftMotor");
        rightOM = hardwareMap.dcMotor.get("rightMotor");
        //Reverse direction of one motor (will change later)
        leftOM.setDirection(DcMotorSimple.Direction.REVERSE);

        //Special System Setup----------------------------------------------------------------------
        grabberM = hardwareMap.dcMotor.get("grabberMotor");
        raiseM = hardwareMap.dcMotor.get("raiseMotor");

        //Reverse directions of motors (test to figure out which motors must be reversed)
        grabberM.setDirection(DcMotorSimple.Direction.REVERSE);
        raiseM.setDirection(DcMotorSimple.Direction.REVERSE);

        //Activate encoders
        grabberM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        raiseM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Servo Setup
        rotateS = hardwareMap.servo.get("rotateServo");

        //Intake Setup------------------------------------------------------------------------------
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        intakeServo = hardwareMap.servo.get("intakeServo");

        waitForStart();
        while(opModeIsActive()){
            //Drive Code----------------------------------------------------------------------------
            if(gamepad1.b){
                power = 0.4;
            }
            else if(gamepad1.a){
                power = 1.0;
            }
            double drive = -power * gamepad1.left_stick_y;
            double strafe = power * gamepad1.left_stick_x;
            double rotate = power * gamepad1.right_stick_x;

            m1.setPower(drive + strafe + rotate);
            m2.setPower(drive - strafe - rotate);
            m3.setPower(drive - strafe + rotate);
            m4.setPower(drive + strafe - rotate);

            //Outtake Code--------------------------------------------------------------------------
            leftOM.setPower(gamepad1.right_trigger);
            rightOM.setPower(gamepad1.right_trigger);

            //Special System Code-------------------------------------------------------------------
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

            //Intake Code---------------------------------------------------------------------------
            if (gamepad2.right_bumper) {
                intakeMotor.setPower(1);
            }
            else if (gamepad2.left_bumper) {
                intakeMotor.setPower(-1);
            }
            else {
                intakeMotor.setPower(0);
            }

            if(gamepad2.a){
                intakeServo.setPosition(0.2);
            }
            else if(gamepad2.b){
                intakeServo.setPosition(0.8);
            }

            //Special System Encoders Telemetry (Testing Purposes)
            telemetry.addData("Raise Encoder", raiseM.getCurrentPosition());
            telemetry.addData("Grabber Encoder", grabberM.getCurrentPosition());

            idle();
        }
    }

}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "Drive Train", group = "")
public class driveTrain extends LinearOpMode
{

    private DcMotor m2;
    private DcMotor m4;
    private DcMotor m1;
    private DcMotor m3;

    private double power = 1.0;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() throws InterruptedException{
        m2 = hardwareMap.dcMotor.get("m2");
        m4 = hardwareMap.dcMotor.get("m4");
        m1 = hardwareMap.dcMotor.get("m1");
        m3 = hardwareMap.dcMotor.get("m3");

        // Reverse some of the drive motors.
        waitForStart();
        m2.setDirection(DcMotor.Direction.REVERSE);
        m4.setDirection(DcMotor.Direction.REVERSE);



        while (opModeIsActive()) {
            // Put loop blocks here.

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

            telemetry.addData("M1", m1.getPower());
            telemetry.addData("M2", m2.getPower());
            telemetry.addData("M4", m4.getPower());
            telemetry.addData("M3", this.m3.getPower());

            telemetry.update();
        }

    }
}



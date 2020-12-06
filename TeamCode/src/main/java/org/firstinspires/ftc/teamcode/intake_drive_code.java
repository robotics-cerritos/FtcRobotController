package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Intake Drive Code")
public class intake_drive_code extends LinearOpMode {
    private DcMotor intakeMotor;

    @Override
    public void runOpMode() throws InterruptedException{
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        
    }

}

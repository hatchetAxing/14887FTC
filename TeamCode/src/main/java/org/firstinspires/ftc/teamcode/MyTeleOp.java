package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.io.BufferedInputStream;

@TeleOp(name = "MyTeleOp")
public class MyTeleOp extends OpMode {

    //creates

    //motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor armMotor = null;

    //servos
    Servo servo = null;
    //misc
    HardwareMap hardwareMap = null;
    ElapsedTime runTime = new ElapsedTime();



    @Override
    public void init() {
        servo.setPosition(1);

        //init motors and servos to name on phone config
        leftMotor = hardwareMap.get(DcMotor.class, "motor1");
        rightMotor = hardwareMap.get(DcMotor.class, "motor2");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");

        servo = hardwareMap.get(Servo.class, "servo");

        //set motor direction
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //motor mode (encoder stuff + track position)
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //include arm motor
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //give zero pwr func
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runTime.reset();
    }

    @Override
    public void loop() {
        double leftPower;
        double rightPower;

        // POV Mode uses left stick to go forward, and right stick to turn.
        // uses basic math to combine motions and is easier to drive straight.
        // NOT Tank Drive (where one stick controls one wheel)
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower = Range.clip(drive - turn, -1.0, 1.0) ;

        // Send calculated power to wheels
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);

        // show elapsed game time and wheel power
        telemetry.addData("Status", "Run Time: " + runTime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);

    }

    @Override
    public void stop(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}

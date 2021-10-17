package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
//***
//
//
//  NOT BEING USED
//
//
//***


//named Hardware instead of HardwareMap in order to use qualcomm's HardwareMap (name already taken)
public class Hardware {
    //creates

    //motors
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;
    public DcMotor armMotor = null;

    //servos
    public Servo servo = null;

    //misc
    HardwareMap hardwareMap = null;
    public ElapsedTime runTime = new ElapsedTime();

    public Hardware(HardwareMap hwMap){
        init(hwMap);
    }

    private void init(HardwareMap hwMap){
        hardwareMap = hwMap;

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
    }

}

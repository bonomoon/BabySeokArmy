#include "I2Cdev.h"
#include "MPU6050_6Axis_MotionApps20.h"
#include "math.h"
#if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
#include "Wire.h"
#endif
MPU6050 mpu;
#define OUTPUT_READABLE_QUATERNION
#define INTERRUPT_PIN 2  // use pin 2 on Arduino Uno & most boards
#define LED_PIN 13 // (Arduino is 13, Teensy is 11, Teensy++ is 6)
bool blinkState = false;

// MPU control/status vars
bool dmpReady = false;  // set true if DMP init was successful
uint8_t mpuIntStatus;   // holds actual interrupt status byte from MPU
uint8_t devStatus;      // return status after each device operation (0 = success, !0 = error)
uint16_t packetSize;    // expected DMP packet size (default is 42 bytes)
uint16_t fifoCount;     // count of all bytes currently in FIFO
uint8_t fifoBuffer[64]; // FIFO storage buffer

Quaternion q;           // [w, x, y, z]         quaternion container
uint8_t teapotPacket[14] = { '$', 0x02, 0, 0, 0, 0, 0, 0, 0, 0, 0x00, 0x00, '\r', '\n' };

volatile bool mpuInterrupt = false;     // indicates whether MPU interrupt pin has gone high

void dmpDataReady() {
  mpuInterrupt = true;
}

typedef struct {
  double w;
  double x;
  double y;
  double z;
} pp;
pp q1, q2;
int cnt = 0;
int buttonpin = 3;
int initcount = 5000;
double w, x, y, z, s, qx, qy, qz, degree;
bool flag = false;
#include <SoftwareSerial.h>

SoftwareSerial BTSerial(5, 4);

void setup() {
  // put your setup code here, to run once:
  BTSerial.begin(9600);

#if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
  Wire.begin();
  Wire.setClock(400000); // 400kHz I2C clock. Comment this line if having compilation difficulties
#elif I2CDEV_IMPLEMENTATION == I2CDEV_BUILTIN_FASTWIRE
  Fastwire::setup(400, true);
#endif
  Serial.begin(9600);

  while (!Serial);

  mpu.initialize();
  pinMode(INTERRUPT_PIN, INPUT);
  pinMode(3, INPUT_PULLUP);

  //
  //  while (Serial.available() && Serial.read()); // empty buffer
  //  while (!Serial.available());                 // wait for data
  //  while (Serial.available() && Serial.read()); // empty buffer again

  Serial.println(F("Initializing DMP..."));
  devStatus = mpu.dmpInitialize();
  mpu.setXGyroOffset(220);
  mpu.setYGyroOffset(76);
  mpu.setZGyroOffset(-85);
  mpu.setZAccelOffset(1788); // 1688 factory default for my test chip
  mpu.setDMPEnabled(true);
  attachInterrupt(digitalPinToInterrupt(3), startInit, FALLING);
  attachInterrupt(digitalPinToInterrupt(INTERRUPT_PIN), dmpDataReady, RISING);
  mpuIntStatus = mpu.getIntStatus();
  dmpReady = true;
  packetSize = mpu.dmpGetFIFOPacketSize();
  mpuInterrupt = true;
  startInit();

}

void loop() {
  // put your main code here, to run repeatedly:
  cnt += 1;
  if (initcount < 5000) {
    initcount ++;
    cnt = 0;
  }
  else if (initcount == 5000) {
    initcount ++;
    initq1();
    delay(500);
    digitalWrite(12, LOW);
  }

  if (cnt >= 1000) {
    cnt = 0;
    Serial.print("test \n");
    gettmp();

  }
  delay(1);

  dmpDataReady();
  while (!mpuInterrupt && fifoCount < packetSize) {
    if (mpuInterrupt && fifoCount < packetSize) {
      // try to get out of the infinite loop
      fifoCount = mpu.getFIFOCount();
    }
  }

  mpuInterrupt = false;
  mpuIntStatus = mpu.getIntStatus();

  fifoCount = mpu.getFIFOCount();

  // check for overflow (this should never happen unless our code is too inefficient)
  if ((mpuIntStatus & _BV(MPU6050_INTERRUPT_FIFO_OFLOW_BIT)) || fifoCount >= 1024) {
    // reset so we can continue cleanly
    mpu.resetFIFO();
    fifoCount = mpu.getFIFOCount();
    getnow();
    // otherwise, check for DMP data ready interrupt (this should happen frequently)
  } else if (mpuIntStatus & _BV(MPU6050_INTERRUPT_DMP_INT_BIT)) {
    // wait for correct available data length, should be a VERY short wait
    while (fifoCount < packetSize) fifoCount = mpu.getFIFOCount();

    // read a packet from FIFO
    mpu.getFIFOBytes(fifoBuffer, packetSize);

    // track FIFO count here in case there is > 1 packet available
    // (this lets us immediately read more without waiting for an interrupt)
    fifoCount -= packetSize;

#ifdef OUTPUT_READABLE_QUATERNION
    // display quaternion values in easy matrix form: w x y z
    mpu.dmpGetQuaternion(&q, fifoBuffer);
    //    Serial.print("quat\t");
    //    Serial.print(q.w);
    //    Serial.print("\t");
    //    Serial.print(q.x);
    //    Serial.print("\t");
    //    Serial.print(q.y);
    //    Serial.print("\t");
    //    Serial.println(q.z);
#endif

  }
}

void startInit() {
  digitalWrite(12, HIGH);
  initcount = 0;
}

void initq1() {
  q1.w = q.w;
  q1.x = q.x;
  q1.y = q.y;
  q1.z = q.y;
  Serial.print("Start \n");
}

void getnow() {
  q1.w = q.w;
  q1.x = q.x;
  q1.y = q.y;
  q1.z = q.y;
  Serial.print("Check \n");
}

void gettmp() {
  q2.w = q.w;
  q2.x = q.x;
  q2.y = q.y;
  q2.z = q.y;
  Serial.print("Check \n");
  check();

}

void check() {
  w = q1.w * q2.w - q1.x * q2.x - q1.y * q2.y - q1.z * q1.z;
  qx = q1.w * q2.x + q1.x * q2.w + q1.y * q2.z - q1.z * q2.y;
  qy = q1.w * q2.y + q1.y * q2.w + q1.z * q2.x - q1.x * q2.z;
  qz = q1.w * q2.z + q1.z * q2.w + q1.x * q2.y - q1.y * q2.x;

  degree = 2 * acos(w);
  Serial.print(degree);
  Serial.print("\n");
  if (degree < 5) {
    if (flag == false) {
      BTSerial.print("F");
      Serial.print("F");
    }
    flag = true;
    delay(500);
  }
  else if (isnan(degree) || degree >= 5) {
    if (flag == true) {
      BTSerial.print("R");
      Serial.print("R");
      delay(500);
      flag = false;
    }
  }
}

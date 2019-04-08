# Sensor Network
Sensors, Smart Nodes, and Cluster Nodes setup and configuration

## Hardware Used
   Raspberry pi3   x 1  
   Arduino MKR1000 x 1  
   DHT sensor      x 1 (can add more if you like)  
   2x16 LCDdisplay x 1  

## Raspberry Pi3(RBP) setup
1.Setup RBP as an Access Point
Base on [this](https://www.raspberrypi.org/documentation/configuration/wireless/access-point.md) guide we can set the RBP as an AP. (No bridge needed in this project)  
notice: the static ip_address has to be same as the one in dhcpcd.conf  
2.Install MQTT Broker
By following [this](https://www.switchdoc.com/2018/02/tutorial-installing-and-testing-mosquitto-mqtt-on-raspberry-pi/) guide we can install the MQTT on RBP

### How to use
1.Turn on the RBP
2.Open the Terminal
3.Type 'mosquitto' to start the MQTT broker
4.Type 'node-red-start' to start a node-red base MQTT client

## Arduino setup
### Libraries used
   1.MQTT client library: [PubSubClient.h](https://pubsubclient.knolleary.net/)  
   2.Serial Port library: [SPI.h](https://www.arduino.cc/en/Reference/SPI)  
   3.WiFi module library: [WiFi101.h](https://www.arduino.cc/en/Reference/WiFi101)  
   4.DHT11 sensor library: [dht11.h](https://github.com/adidax/dht11)  
   5.LCD display library: [LiquidCrystal.h](https://www.arduino.cc/en/Reference/LiquidCrystal)  
### Schematic
   in progress...  
### How to use
   Just power on.

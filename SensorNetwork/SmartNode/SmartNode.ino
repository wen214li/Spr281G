#include <PubSubClient.h>
#include <SPI.h>
#include <WiFi101.h>
#include <dht11.h>
#include <LiquidCrystal.h>
 
char ssid[] = "ClusterNode";       // your network SSID (name)
char pass[] = "raspberry";         // your network password
const char* mqtt_server = "192.168.4.1";   // yout MQTT brocker IP address
int keyIndex = 0;                  // your network key Index number (needed only for WEP)
int ledpin = 6;
bool val = true;
long lastMsg = 0;
int value = 0;
char temperature[50];
char humidity[50];
String temp_str;
String hum_str;
 
int status = WL_IDLE_STATUS;
WiFiClient wlClient;
PubSubClient client(wlClient);

dht11 DHT11;
#define DHT11PIN 7
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

// callback function handle received message
void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("] ");
  for (int i=0;i<length;i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();
}

// connect function handel MQTT connection, Publish, and subscribe
void reconnect() {
  char* message_buff;
  
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    if (client.connect("arduinoClient")) {
      Serial.println("connected");
      // Once connected, publish an announcement...
      client.publish("SNode/DHTsensor0/status","Connected");
      // ... and resubscribe
      client.subscribe("SNode/DHTsensor0/ctrl");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}

void lcdDisplay(dht11 DHT11) {
  lcd.setCursor(0, 0);
  lcd.print("Temp: ");
  lcd.print((float)DHT11.temperature, 2);
  lcd.print(" C");
  
  lcd.setCursor(0, 1);
  lcd.print("RelF: ");
  lcd.print((float)DHT11.humidity, 2);
  lcd.print(" %");
}
 
void setup() {
  Serial.begin(9600);      // initialize serial communication
  client.setServer(mqtt_server,1883);
  client.setCallback(callback);
  
  Serial.print("Start Serial ");
  pinMode(ledpin, OUTPUT);      // set the LED pin mode
  
  // attempt to connect to Wifi network:
  while ( status != WL_CONNECTED) {
    digitalWrite(ledpin, LOW);
    Serial.print("Attempting to connect to Network named: ");
    Serial.println(ssid);              // print the network name (SSID);
    digitalWrite(ledpin, HIGH);
    // Connect to WPA/WPA2 network. Change this line if using open or WEP network:
    status = WiFi.begin(ssid, pass);
    // wait 5 seconds for connection:
    delay(5000);
  }

  lcd.begin(16, 2);
  lcd.print("Initialisierung...");
  lcd.clear();
  
  printWifiStatus();                   
  digitalWrite(ledpin, HIGH);
}

void loop() {
  DHT11.read(DHT11PIN);
  lcdDisplay(DHT11);
  
  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  float ft = DHT11.temperature;
  float fh = DHT11.humidity;

  //counter for the messages, see if I am missing any on the Mqtt broker 
  long now = millis(); 
  if (now - lastMsg > 5000) {
    lastMsg = now;
    ++value;
    
    //Preparing for mqtt send 
    temp_str = String(ft); //converting ftemp (the float variable above) to a string 
    temp_str.toCharArray(temperature, temp_str.length() + 1); //packaging up the data to publish to mqtt
    hum_str = String(fh); //converting Humidity (the float variable above) to a string
    hum_str.toCharArray(humidity, hum_str.length() + 1); //packaging up the data to publish to mqtt

    client.publish("SNode/DHTsensor0/temperature",temperature);
    client.publish("SNode/DHTsensor0/humidity",humidity);
  }
}


// display WIFI status
void printWifiStatus() {
  // print the SSID of the network you're attached to:
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());
 
  // print your WiFi shield's IP address:
  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);
 
  // print the received signal strength:
  long rssi = WiFi.RSSI();
  Serial.print("signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
  // print where to go in a browser:
  Serial.print("To see this page in action, open a browser to http://");
  Serial.println(ip);
}

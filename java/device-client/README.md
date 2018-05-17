# device-client
Java Azure IoT Device Client

Client application that exposes direct method

<br>
<ul>
<li>Sets up a device application that registers with the Azure IoT hub as a Azure IoT Device</li>
<li>Exposes a method that can be invoked as a direct method call and returns a random number representing the temperature to the calling application.</li>
</ul>

<b>Instructions</b>
(You can either work on your laptop and build the solution then deploy to you Raspberry PI or you can develop on the Rapberry PI. The PI will contain JDK and Maven and Git so you can easily clone the repository as needed.)

1. Ensure you have JDK and Maven installed on your machine
2. Download and unpack zipped file of application or clone respository using <code>git clone https://github.com/spock75/device-client.git</code> from a terminal window on your pi or laptop in the folder you wish to house your app.
3. Open terminal window to the location where the project files are located
4. Edit the App.java file and update the connection string to the Azure Device and Device ID with the provided connection string/DeviceID in Hackathon instructions
5. Type <code>mvn clean package</code> in the terminal
6. Fix any build errors
7. For linux type <code>java -jar target/device-client-1.0-SNAPSHOT.jar</code> to run application<br/>For windows type <code>java -jar target\device-client-1.0-SNAPSHOT.jar</code> to run application


# Node-iot-invoke-direct-method
Simple node IOT device client
<br>
<ul>
<li>Sets up a calling application that registers with the Azure IoT hub and makees direct method call to a method called getTempFromDevice()</li>
<li>Takes returned payload from device method which contains a temperature value then adds this value to an array.</li>
<li>Writes out average temperature from the array on each interation</li>
</ul>


Instructions  
1. Ensure you have node and npm installed on your machine  https://nodejs.org/en/ (When you install node you will get both node and npm)
2. Ensure client application is installed and started that is located in the https://github.com/spock75/node-iot-client-dm.git repository, if you try to run this out of the box with no client running, you will get errors indicating such.
3. Download and unpack zipped file of application or clone respository using <code>git clone https://github.com/spock75/node-iot-invoke-direct-method.git</code> from the terminal on your device or laptop in the folder where your wish to house this app.
4. Open terminal window to the location where the project files are located
5. Edit the CallMethodOnDevice.js and update the connection string to the IoT Hub and Device ID with the provided connection string/DeviceID in Hackathon instructions
6. Type <code>npm install</code> to install all required packages
7. After all packages are installed type <code>node CallMethodOnDevice.js</code> to run application

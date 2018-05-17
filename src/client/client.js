console.log("@ Client Start");

const Mqtt = require('azure-iot-device-mqtt').Mqtt;
const DeviceClient = require('azure-iot-device').Client;

const connectionString = 'HostName=team31hub.azure-devices.net;DeviceId=team31device4;SharedAccessKey=a3XXoYhhTSMwk3o48JNSHZ5blA5kr/gyTf0avo4hipw=';
const client = DeviceClient.fromConnectionString(connectionString, Mqtt);

function onGetTemperature(request, response) {

    const temperature = Math.floor(Math.random() * 100) + 1;

    const payload = {
        temperature: temperature
    };

    console.log('@ Payload', payload);

    response.send(200, JSON.stringify(payload), function (err) {
        if (err) {
            console.error('An error occurred when sending a method response:\n' + err.toString());
        } else {
            console.log('Response to method \'' + request.methodName + '\' sent successfully.');
        }
    });
}

client.open(function (err) {
    if (err) {
        console.error('@ Error, could not open IotHub client');
    } else {
        console.log('@ Client opened');
        client.onDeviceMethod('getTemperature', onGetTemperature);
    }
});

console.log("@ Client End");
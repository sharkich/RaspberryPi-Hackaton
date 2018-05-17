package com.mycompany.app;

/**
 * Device Client
 */
 
import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.DeviceMethodData;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.DeviceMethodCallback;
import java.util.Arrays; 
import com.google.gson.Gson;
import java.util.Random;	 

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

 
public class App
{
	private static String connString = "HostName=team31hub.azure-devices.net;DeviceId=team31device4;SharedAccessKey=a3XXoYhhTSMwk3o48JNSHZ5blA5kr/gyTf0avo4hipw="; // { azure device connection string }
	private static IotHubClientProtocol protocol = IotHubClientProtocol.MQTT;
	private static String deviceId = "team31device4"; // { device ID }
	private static final int METHOD_SUCCESS = 200;
	private static final int METHOD_NOT_DEFINED = 404;
	
  
	public static void main(String[] args) throws IOException, URISyntaxException
	{
	  System.out.println("Starting device sample...");

	  DeviceClient client = new DeviceClient(connString, protocol);
	  try
	  {
		client.open();
		client.subscribeToDeviceMethod(new DirectMethodCallback(), null, new DirectMethodStatusCallback(), null);
		System.out.println("Subscribed to direct methods. Waiting...");
	  }
	  catch (Exception e)
	  {
		System.out.println("On exception, shutting down \n" + " Cause: " + e.getCause() + " \n" +  e.getMessage());
		client.close();
		System.out.println("Shutting down...");
	  }

	  System.out.println("Press any key to exit...");
	  Scanner scanner = new Scanner(System.in);
	  scanner.nextLine();
	  scanner.close();
	  client.close();
	  System.out.println("Shutting down...");
	}

	
	public static class Payload
	{
		public int temp;
		public Payload() {};
		
		public int getTemp()
		{
			return this.temp;
		}
		
		public void setTemp(int temperature)
		{
			this.temp = temperature;
		}
	}
	
	
	protected static class DirectMethodStatusCallback implements IotHubEventCallback
	{
	  public void execute(IotHubStatusCode status, Object context)
	  {
		System.out.println("IoT Hub responded to device method operation with status " + status.name());
	  }
	}


	protected static class DirectMethodCallback implements DeviceMethodCallback
	{
		Gson gson = new Gson(); 
		Random rand = new Random();
		
		@Override
		public DeviceMethodData call(String methodName, Object methodData, Object context)
		{
			DeviceMethodData deviceMethodData;
			Payload payloadAsObject = new Payload();
			// returns random number to represnet a temperature and initializes member of Payload class
			payloadAsObject.setTemp(rand.nextInt(100) + 1);
			
			switch (methodName)
			{
				case "writeLine" :
				{
					int status = METHOD_SUCCESS;
					System.out.println("Sent: " + gson.toJson(payloadAsObject));
					deviceMethodData = new DeviceMethodData(status, gson.toJson(payloadAsObject));
					break;
				}
				default:
				{
					int status = METHOD_NOT_DEFINED;
					deviceMethodData = new DeviceMethodData(status, "Not defined direct method " + methodName);
				}
			}
			return deviceMethodData;
		}
	}

}

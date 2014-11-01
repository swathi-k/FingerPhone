import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class FingerciseServer extends Thread {
	
	private static final int PORT=7890;
	private static final int NUM_CONNECT=1;
	GamesScore gscore;
	private FingerciseServer() {}
	
	public static void main(String args[])
	{
		FingerciseServer myServer = new FingerciseServer();
		
		if(myServer !=null) {
			myServer.gscore = new GamesScore();
			System.out.println("Starting thread");
			myServer.start();
		}
	}
	
	public void run()
	{
		try
		{
			ServerSocket server = new ServerSocket(PORT, NUM_CONNECT);
			System.out.println("Starting run method");
			Socket client = server.accept();
			
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(client.getOutputStream()));
			
			// do some reading and writing
			//writer.write("Fingercise Server");
			String message = "";
			String line;
			while ((line = reader.readLine()) != null) {
				message =line;
				String[] action = message.split(":");
			    String output = "";
			    if(action[0].equals("register"))
			    {
			    	output = register(action[1]);
			    }
			    else if(action[0].equals("results"))
			    {
			    	output = results(action[1]);
			    }
			    else if(action[0].equals("statistics"))
			    {
			    	output = statistics(action[1]);
			    }
			    writer.write(output);
			    writer.newLine(); //HERE!!!!!!
			    writer.flush();
		    }
		    
		    
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		
	}

	private String register(String name) {
		
		if (gscore.addUser(name))
			return "Okay\n";
		else
			return "Sorry\n";
	}

	private String results(String args) {
		// TODO Auto-generated method stub
		
		String[] arguments = args.split(",");
		if(arguments.length < 3)
			return "Incorrect message format";
		
		gscore.update(arguments[0], arguments[1], Integer.parseInt(arguments[2]));
			
		return "";
	}

	private String statistics(String name) {
		
		return gscore.getStats(name);
		
	}
}
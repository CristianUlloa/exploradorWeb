package com.cristhian.lab12rina;

import java.io.*;
import java.net.*;

import com.sun.net.httpserver.*;

public class MainClass {
	
	private static final int PORT = 8500;
	
	public static void main(String[] args) throws IOException
	{
		try
		{
			 HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
		        server.createContext("/_explorador", Handlers::exploradorCatch);
		        server.createContext("/", Handlers::rootHadler);
		        server.createContext("/binary_res", Handlers::binary_res);
		        server.createContext("/binary_file", Handlers::binary_file);
		        
		        server.start();
		        
		}catch(Exception e)
		{
			
		}
		
	}
/*
	private static void holaMundo(HttpExchange exchange) throws IOException {
		 OutputStream out = exchange.getResponseBody();
		try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html")) {
			exchange.sendResponseHeaders(200, in.available());
			copyStream(in, out, true);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 private static void handleRequest(HttpExchange exchange) throws IOException {
		 try {
			 URI uri = exchange.getRequestURI();
					Map<String, List<String>> q = Utils.splitQuery(uri);
					
					String p = uri.getPath() + " <br/>";
					p += q.toString();
					exchange.sendResponseHeaders(200, p.getBytes().length);//response code and length
			      OutputStream os = exchange.getResponseBody();
			      os.write(p.getBytes());
			      os.close();
		 }catch (Exception e) {
			e.printStackTrace();
		}
	  }
	  */
}

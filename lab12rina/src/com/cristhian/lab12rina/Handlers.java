package com.cristhian.lab12rina;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cristhian.lab12rina.gson.FileData;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

public class Handlers {

	private static Gson gson = new Gson();

	public static void rootHadler(HttpExchange exchange) {
		URI uri = exchange.getRequestURI();
		File file = new File(uri.getPath().substring(1));
		try {
			if (file.isDirectory())
				dir(exchange);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void binary_res(HttpExchange exchange) {
		try {
			Map<String, List<String>> map = Utils.splitQuery(exchange.getRequestURI());
			FileInputStream fis = new FileInputStream(new File(Apps.getProjectDir(), map.get("q").get(0)));
			exchange.sendResponseHeaders(200, fis.available());// response code and length

			OutputStream os = exchange.getResponseBody();

			Utils.copyStream(fis, os, true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				exchange.sendResponseHeaders(400, -1);
			} catch (Exception e1) {
			}
		}

	}

	public static void binary_file(HttpExchange exchange) {
		try {
			Map<String, List<String>> map = Utils.splitQuery(exchange.getRequestURI());
			FileInputStream fis = new FileInputStream(new File(map.get("q").get(0)));
			exchange.sendResponseHeaders(200, fis.available());// response code and length

			OutputStream os = exchange.getResponseBody();

			Utils.copyStream(fis, os, true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				exchange.sendResponseHeaders(400, -1);
			} catch (Exception e1) {
			}
		}

	}
	
	private static void dir(HttpExchange exchange) throws IOException {
		URI uri = exchange.getRequestURI();
		File file = new File(uri.getPath().substring(1));

		if (file.isDirectory()) {

			String content = Utils.readFile(Apps.getExplorerIndex());

			content = content.replace("{java.impl.q}", file.getAbsolutePath().replace("\\", "/"));

			exchange.sendResponseHeaders(200, content.getBytes().length);// response code and length
			OutputStream os = exchange.getResponseBody();
			os.write(content.getBytes());
			os.close();

		}

	}

	public static void exploradorCatch(HttpExchange exchange) {
		try {
			explorador(exchange);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				exchange.sendResponseHeaders(400, -1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void explorador(HttpExchange exchange) throws IOException {
		URI uri = exchange.getRequestURI();
		String q = Utils.splitQuery(uri).get("q").get(0);
		File dir = new File(q);

		List<FileData> list = new LinkedList<FileData>();
		try {
			list.add(new FileData(dir.getParentFile(), ".."));

		} catch(NullPointerException e)
		{
		}
		for (File file : dir.listFiles()) {
			list.add(new FileData(file));
		}

		String json = gson.toJson(list);

		exchange.sendResponseHeaders(200, json.getBytes().length);// response code and length
		OutputStream os = exchange.getResponseBody();
		os.write(json.getBytes());
		os.close();
	}

}

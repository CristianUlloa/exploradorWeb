package com.cristhian.lab12rina;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
	
	public static String readFile(File file)
	{
		String fileContent;
		try {

		    byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		    fileContent = new String (bytes);
		} catch (IOException e) {
			e.printStackTrace();
			fileContent = null;
		}
		return fileContent;
	}
	
	public static void copyStream(InputStream in, OutputStream out, boolean close)
		    throws IOException
	{
	    final byte[] buff = new byte[1024];
	    int index;
	    while((index = in.read(buff)) > -1)
	    {
	    	out.write(buff, 0, index);
	    }
	    if(close)
	    {
	    	out.close();
			in.close();
	    }
	}
	
	public static Map<String, List<String>> splitQuery(URI url) {
	    if (isNullOrBlank(url.getQuery())) {
	        return Collections.emptyMap();
	    }
	    return Arrays.stream(url.getQuery().split("&"))
	            .map(Utils::splitQueryParameter)
	            .collect(Collectors.groupingBy(SimpleImmutableEntry::getKey, LinkedHashMap::new, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
	}

	private static SimpleImmutableEntry<String, String> splitQueryParameter(String it) {
	    final int idx = it.indexOf("=");
	    final String key = idx > 0 ? it.substring(0, idx) : it;
	    final String value = idx > 0 && it.length() > idx + 1 ? it.substring(idx + 1) : null;
	    
	    return new SimpleImmutableEntry<>(
	        decode(key),
	        decode(value)
	    );
	}

	private static String decode(final String key) {
		try {
			return URLDecoder.decode(key, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isNullOrBlank(String param) { 
	    return param == null || param.trim().length() == 0;
	}
	
}

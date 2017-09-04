package com.paper.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.github.axet.wget.WGet;

public class WgetUtil {
	public static void wget(String inputUrl, String outPath) {
		try {
			URL url = new URL(inputUrl);
			File output = new File(outPath);
			WGet w = new WGet(url, output);
			w.download();
			
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
	}
}

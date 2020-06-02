package de.presti.opsource.altgen.utils;

import java.util.ArrayList;
import java.util.Random;

public class AltList {

	public static ArrayList<String> alts = new ArrayList<String>();
	
	public static int size() {
		return alts.size();
	}
	
	public static String getAlt() {
		String alt = "Fehler";
		
		Random r = new Random();
		
		int i = r.nextInt(size());
		
		alt = alts.get(i);
		
		alts.remove(alt);
		
		return alt;
	}
	
	public static void addAlt(String alt) {
		if(!alts.contains(alt)) {
			alts.add(alt);
		}
	}
	
}

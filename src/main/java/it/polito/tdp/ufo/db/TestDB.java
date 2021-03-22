package it.polito.tdp.ufo.db;

import java.util.ArrayList;
import java.util.List;


public class TestDB {

	public static void main(String[] args) {
		
		SightingDAO dao = new SightingDAO();
		
		List<String> formeUFO = dao.readShapes();
		
		for(String forma : formeUFO) {
			int c = dao.countByShape(forma);
			System.out.println("UFO di forma " + forma + " sono: " + c);
		}
		
	}

}

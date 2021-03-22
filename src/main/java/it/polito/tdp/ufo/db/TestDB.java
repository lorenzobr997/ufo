package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class TestDB {

	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
		try {
			Connection connection = DriverManager.getConnection(jdbcURL);
			
			Statement statement = connection.createStatement();
			
			String sql = "SELECT DISTINCT shape " 
					+ "FROM sighting "
					+ "WHERE shape <> '' "
					+ "ORDER BY shape ASC ";
			
			ResultSet result = statement.executeQuery(sql);
			
			List<String> formeUFO = new ArrayList<>();
			while(result.next()) {
				String forma = result.getString("shape");
				formeUFO.add(forma);
			}
			
			System.out.println(formeUFO);
			
			String sql2 ="SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ?";
			String shapeScelta = "circle";
			
			PreparedStatement st2 = connection.prepareStatement(sql2);
			st2.setString(1, shapeScelta);
			ResultSet res2 =st2.executeQuery();
			res2.first();
			int count = res2.getInt("cnt");
			st2.close();
			
			System.out.println("UFO di forma " + shapeScelta + " sono: " + count);
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

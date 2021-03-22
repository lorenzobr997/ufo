package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {
	
	
	
	public List<String> readShapes(){
		try {
		Connection connection = DBConnect.getConnection();
		
		//Statement statement = connection.createStatement();
		
		String sql = "SELECT DISTINCT shape " 
				+ "FROM sighting "
				+ "WHERE shape <> '' "
				+ "ORDER BY shape ASC ";
		
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet result = st.executeQuery();
		
		List<String> formeUFO = new ArrayList<>();
		while(result.next()) {
			String forma = result.getString("shape");
			formeUFO.add(forma);
		}
		
		st.close();
		connection.close();
		
		return formeUFO;
		
		}catch (SQLException e ) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in readShape",e);
		}
	}
	
	public int countByShape(String shape) {
		try {String sql2 ="SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ?";
		String shapeScelta = "circle";
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
		
		Connection connection = DriverManager.getConnection(jdbcURL);
		
		PreparedStatement st2 = connection.prepareStatement(sql2);
		st2.setString(1, shapeScelta);
		ResultSet res2 =st2.executeQuery();
		res2.first();
		int count = res2.getInt("cnt");
		st2.close();
		
		connection.close();
		
		return count;
			
		}catch(SQLException e) {
			throw new RuntimeException("ERROR in countByShape", e);
		}
		
	}

}

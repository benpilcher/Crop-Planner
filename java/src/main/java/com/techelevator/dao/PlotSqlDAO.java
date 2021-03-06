package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import com.techelevator.model.Crop;
import com.techelevator.model.Note;
import com.techelevator.model.Plot;


@Component
public class PlotSqlDAO implements PlotDAO {

	private JdbcTemplate jdbc;
	
	public PlotSqlDAO(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Plot> listAllForUser(int userId) {
		List<Plot> plots = new ArrayList<>();
		String sqlPlot =  "SELECT plot_id, name, length, width, active "
					+ "FROM plot "
					+ "JOIN users_plot USING(plot_id) "
					+ "JOIN users USING (user_id) "
					+ "WHERE user_id = ?";
		
		String sqlTopCrop = "SELECT crops.name AS name, COUNT (coords_id) AS amount "
						  + "FROM crops JOIN plot_coords USING (crop_id) "
						  + "JOIN plot USING (plot_id) "
						  + "JOIN users_plot USING (plot_id) "
						  + "WHERE plot_id = ? "
						  + "GROUP BY crops.name, yield_lbs_per_square_foot "
						  + "ORDER BY amount DESC "
						  + "LIMIT 1";
		
		SqlRowSet plotResults = jdbc.queryForRowSet(sqlPlot, userId);
		
		while(plotResults.next()) {
			Plot plotResult = mapRowToPlot(plotResults);
			
			SqlRowSet topCropResult = jdbc.queryForRowSet(sqlTopCrop, plotResult.getId());
			while(topCropResult.next()) {
				plotResult.setTopCrop(topCropResult.getString("name").toLowerCase());
			}
			plots.add(plotResult);
		}
		return plots;
	}

	@Override
	public int create(Plot plot) {
		String sqlCreate = "INSERT INTO plot(name, length, width, active) "
				   + "VALUES(?, ?, ?, true) "
				   + "RETURNING plot_id";
		Long plotIdLong = jdbc.queryForObject(sqlCreate, Long.class, plot.getName(), plot.getLength(), plot.getWidth());
		return plotIdLong.intValue();
		
	}
	
	@Override
	public void userPlot(int userId, int plotId) {
		String sql = "INSERT INTO users_plot(user_id, plot_id) "
				   + "VALUES(?, ?)";
		jdbc.update(sql, userId, plotId);
	}
	
	@Override
	public Plot plotById(int plotId) {
		Plot plotResult = new Plot();
		String sql =  "SELECT plot_id, name, length, width, active "
					+ "FROM plot "
					+ "WHERE plot_id = ?";
		
		SqlRowSet results = jdbc.queryForRowSet(sql, plotId);
		
		if(results.next()) {
			plotResult = mapRowToPlot(results);
		}
	
		return plotResult;
	}


	private Plot mapRowToPlot(SqlRowSet results) {
		Plot p = new Plot();
		p.setId(results.getInt("plot_id"));
		p.setName(results.getString("name"));
		p.setLength(results.getInt("length"));
		p.setWidth(results.getInt("width"));
		p.setActive(results.getBoolean("active"));
		
		return p;
	}

	@Override
	public List<Note> getAllNotesForUser(int userId) {
		List<Note> notesForPlot = new ArrayList<>();
		String sql = "SELECT note_id, plot_id, note, date "
				+ "FROM notes "
				+ "JOIN users_plot USING(plot_id) "
				+ "WHERE user_id = ?";
		SqlRowSet results = jdbc.queryForRowSet(sql, userId);
		
		while(results.next()) {
			Note n = mapRowToNote(results);
			notesForPlot.add(n);
		}
		
		return notesForPlot;
	}

	@Override
	public void addNewNote(Note newNote) {
		String sql = "INSERT INTO notes (plot_id, note) "
					+"VALUES(?, ?)";
		jdbc.update(sql, newNote.getPlot_id(), newNote.getNote());
		
	}
	
	@Override
	public void updateNote(Note updatedNote, int id) {
		String sql = "DELETE FROM notes SET note = ? WHERE note_id = ?";
		
		jdbc.update(sql, updatedNote.getNote(), id);
	}
	
	@Override
	public void deleteNote(int id) {
		String sql = "DELETE FROM notes WHERE note_id = ?";
		
		jdbc.update(sql, id);
	}
	
	private Note mapRowToNote(SqlRowSet results) {
		Note n = new Note();
		
		n.setNote(results.getString("note"));
		n.setNote_id(results.getInt("note_id"));
		n.setPlot_id(results.getInt("plot_id"));
		n.setDate(results.getDate("date"));
		return n;
	}
	
	@Override
	public void abandonPlot(int plotId) {
		String sql = "UPDATE plot SET active = false WHERE plot_id = ?";
		jdbc.update(sql, plotId);
	}
	
	@Override
	public void activatePlot(int plotId) {
		String sql = "UPDATE plot SET active = true WHERE plot_id = ?";
		jdbc.update(sql, plotId);
	}

}

package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Crop;

public interface CropDAO {

	List<Crop> listAll();
	
	List<Crop> listAllCropsInActivePlots(int plotId);
	
	Crop getCropByName(String name);
	
	Crop getCropById(int id);

	List<Crop> listAllCropsForUser(int userId);

	List<Crop> listCropsForOnePlot(int plotId);
	
	List<Crop> listCropCoordinatesForOnePlot(int plotId);
	 
	Crop getTopCropForPlot(int plotId);
	
	void updateCoordinateData(int plotId, Crop crop);

	
}

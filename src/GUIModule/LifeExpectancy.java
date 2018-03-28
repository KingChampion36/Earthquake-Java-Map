package GUIModule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet{
	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;
	
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName)
	{

		//Create a map to return
		//Implementation of map should be like hashMap
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		
		//Load the file taking one row at a time
		String[] rows = loadStrings(fileName);
		
		for(String row : rows){
			//Separate each row into array of strings called columns 
			String[] columns = row.split(",");
			/*
			if(...){
				//Convert Life expectancy value to float
				float value = Float.parseFloat(columns[15]);
				lifeExpMap.put(columns[4], value);
				
			}*/
		}
		return lifeExpMap;
		
	}
	
	private void shadeCountries()
	{
		for(Marker marker : countryMarkers){
			String countryId = marker.getId();
			if(lifeExpByCountry.containsKey(countryId)){
				//Get value of countryID from the map
				float lifeExp = lifeExpByCountry.get(countryId);
				
				//convert life expectancy range(40, 90) to colour level(10, 255) 
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color (255-colorLevel, 100, colorLevel));
			}else{
				//Default colour of life expectancy is gray
				marker.setColor(color(150, 150, 150));
			}
		}
	}
	
	public void setup()
	{
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		lifeExpByCountry = loadLifeExpectancyFromCSV("data/LifeExpectancyWorldBank.csv");
		
		//1 Feature + 1 marker per country
		countries = GeoJSONReader.loadData(this, "data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	public void draw()
	{
		map.draw();
	}
}

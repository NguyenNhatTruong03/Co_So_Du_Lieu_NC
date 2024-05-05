package website_weather.Services;

import java.io.IOException;
import java.util.List;

import website_weather.Models.weather_weathers;

public interface IExtract_Data {

	List<String> filterlist_Data() throws IOException;

	List<String> filter_Data() throws IOException;

	weather_weathers Read_Data(String temp) throws IOException;

}

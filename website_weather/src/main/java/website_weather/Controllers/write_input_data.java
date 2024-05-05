package website_weather.Controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import website_weather.Models.weather_weathers;

public class write_input_data {
	public static void write_data(List<weather_weathers> weather_list) throws IOException {
		BufferedWriter writer_temp_c = new BufferedWriter(new FileWriter("input/temp_c"));
		BufferedWriter writer_precip_mm = new BufferedWriter(new FileWriter("input/precip_mm"));
		for(weather_weathers weather : weather_list) {
			String name = weather.getLocation().getName();
			
			String temp_c = String.valueOf(weather.getCurrent().getTemp_c());
			
			
			String precip_mm = String.valueOf(weather.getCurrent().getPrecip_mm());
			String data_temp_c = name +", "+temp_c+"\n";
			String data_precip_mm = name +", "+precip_mm+"\n";
			
			writer_temp_c.write(data_temp_c);
			
			writer_precip_mm .write(data_precip_mm);
			
		}
		writer_temp_c.close();
		writer_precip_mm.close();
	}

}

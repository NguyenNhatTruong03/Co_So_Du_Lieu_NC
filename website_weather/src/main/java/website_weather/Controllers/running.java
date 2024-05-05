package website_weather.Controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import website_weather.Models.weather_weathers;
import website_weather.Services.Convert_into_object;
import website_weather.Services.Extract_Data;
import website_weather.Services.IConvert_into_object;
import website_weather.Services.IExtract_Data;
import website_weather.Services.ILoad_Data;
import website_weather.Services.ITransformation_Data;
import website_weather.Services.Transformation_Data;

public class running implements Serializable {

	private static final long serialVersionUID = -6357088958905512660L;

	static ITransformation_Data data_trans = new Transformation_Data();
	static ILoad_Data loaD = new website_weather.Services.Load_Data();
	static IExtract_Data extract_data = new Extract_Data();
	static IConvert_into_object convert = new Convert_into_object();
	
	public static void Call_API() throws IOException {
		String location = "";
		System.out.println("Nhap ten thanh pho voi quy cach thay dau ' ' bang '_' : vi du Ha Noi thay bang Ha_Noi");
		Scanner input = new Scanner(System.in);
		location = input.nextLine();
		weather_weathers weaths = data_trans.json_to_object(location);
		loaD.load(weaths);
	}
	
	public static void load_Input_andRun_MapReduce() throws Exception {
		List<String> values = extract_data.filterlist_Data();
		List<weather_weathers> weather_list = convert.Filterlist_value_converted_to_object(values);
		write_input_data.write_data(weather_list);
		average_temperature.runTemperatureAverage("input/temp_c", "output/output_temp_c");
		average_temperature.runTemperatureAverage("input/precip_mm", "output/output_precip_mm");
	}

	public static void main(String[] args) throws Exception {
		//Call_API();
		load_Input_andRun_MapReduce();
	}
}

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class Clien_API {

	public static void Put_Data(Table Htable) throws IOException {

		
		
		
		
		
		
		Put p4 = new Put(Bytes.toBytes("row4"));
		p4.addColumn(Bytes.toBytes("ID"), Bytes.toBytes("ma_san_pham"), Bytes.toBytes("4"));
		p4.addColumn(Bytes.toBytes("ID"), Bytes.toBytes("ma_the_loai"), Bytes.toBytes("1"));
		p4.addColumn(Bytes.toBytes("Mota"), Bytes.toBytes("ten_san_pham"), Bytes.toBytes("Levents® Faded Tee/ Yellow"));
		p4.addColumn(Bytes.toBytes("Mota"), Bytes.toBytes("hinh_anh"), Bytes.toBytes("Faded Tee.jpg"));
		p4.addColumn(Bytes.toBytes("Mota"), Bytes.toBytes("gia_ban"), Bytes.toBytes("390000"));
		p4.addColumn(Bytes.toBytes("Mota"), Bytes.toBytes("hang_san_xuat"), Bytes.toBytes("Levents"));
		p4.addColumn(Bytes.toBytes("Mota"), Bytes.toBytes("thong_tin"), Bytes.toBytes("Hứa hẹn mang đến những item thoải mái để bạn tự tin thể hiện cá tính, phù hợp với mọi hoạt động dạo phố hàng ngày."));
		p4.addColumn(Bytes.toBytes("Mota"), Bytes.toBytes("ton_kho"), Bytes.toBytes("7"));

		


		
		
		
		Put p5 = new Put(Bytes.toBytes("row5"));
		p5.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("ma_the_loai"), Bytes.toBytes("5"));
		p5.addColumn(Bytes.toBytes("Data"), Bytes.toBytes("ten_the_loai"), Bytes.toBytes("Phụ kiện"));
		p5.addColumn(Bytes.toBytes("Mota"), Bytes.toBytes("Mota"), Bytes.toBytes("Phụ kiện thời trang"));

		

		Htable.put(p4); //put lan luot tung p 
		System.out.println("data inserted");

		Htable.close();
	}

	public static void Update_Data(Table Htable) throws IOException {
		Put p = new Put(Bytes.toBytes("row1"));
		p.addColumn(Bytes.toBytes("TT_CNhan"), Bytes.toBytes("Ten"), Bytes.toBytes("Nguyen Nhat Truong"));
		Htable.put(p);
		System.out.println("data Updated");
		Htable.close();
	}

	public static void Read_Data(Table Htable) throws IOException {
		Get g = new Get(Bytes.toBytes("row1"));

		Result result = Htable.get(g);

		byte[] value = result.getValue(Bytes.toBytes("Data"), Bytes.toBytes("ma_the_loai"));

		byte[] value1 = result.getValue(Bytes.toBytes("Data"), Bytes.toBytes("ten_the_loai"));
		byte[] value2 = result.getValue(Bytes.toBytes("Mota"), Bytes.toBytes("Mota"));
		String ten = Bytes.toString(value);
		String nam_sinh = Bytes.toString(value1);
		String value3=Bytes.toString(value2);

		System.out.println("ma_the_loai " + ten + " ten_the_loai " + nam_sinh+ "  " +value3);
	}

	public static void Delete_Data(Table Htable) throws IOException {

		// Htable.delete(del);

		Htable.close();
		System.out.println("data deleted.....");
	}

	public static void Scan_Table(Table Htable) throws IOException {
		Scan scan = new Scan();

		// Scanning the required columns
		scan.addColumn(Bytes.toBytes("TT_CNhan"), Bytes.toBytes("Ten"));
		scan.addColumn(Bytes.toBytes("TT_CNhan"), Bytes.toBytes("Nam Sinh"));

		ResultScanner scanner = Htable.getScanner(scan);

		for (Result result = scanner.next(); result != null; result = scanner.next())

			System.out.println("Found row : " + result);

		scanner.close();
	}

	public static void main(String[] args) throws IOException {
		Configuration config = HBaseConfiguration.create();
		Connection conn = ConnectionFactory.createConnection(config);
		Table Htable = conn.getTable(TableName.valueOf("category"));
		 //Put_Data(Htable);
		// Update_Data(Htable);
		Read_Data(Htable);
		//Delete_Data(Htable);
		//Scan_Table(Htable);
	}
}
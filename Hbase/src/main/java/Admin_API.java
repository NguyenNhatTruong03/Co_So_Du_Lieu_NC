import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.MasterNotRunningException;

import org.apache.hadoop.conf.Configuration;

public class Admin_API {

	public static void Create_Table(HBaseAdmin admin) throws IOException {

		TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf("users1")).setColumnFamily(ColumnFamilyDescriptorBuilder.of("ID")).setColumnFamily(ColumnFamilyDescriptorBuilder.of("Mota")).build();

			admin.createTable(tableDescriptor);
			System.out.println("Table created");
	}

	public static void Listing_Table(HBaseAdmin admin) throws IOException {
		HTableDescriptor[] tableDescriptor = admin.listTables();

		// printing all the table names.
		for (int i = 0; i < tableDescriptor.length; i++) {
			System.out.println(tableDescriptor[i].getNameAsString());
		}
	}
	//đang lỗi hàng này 
	public static void Disable_Table(HBaseAdmin admin) throws MasterNotRunningException, IOException {

	      try {
	    	  admin.disableTable(TableName.valueOf("category"));
		      System.out.println("Table disabled");
		} catch (Exception e) {
			System.out.println("The table has been disabled");
		}
	}
	public static void Enable_Table(HBaseAdmin admin) throws MasterNotRunningException, IOException {

	      try {
	    	  admin.enableTable(TableName.valueOf("Sinh_Vien"));
		      System.out.println("Table enabled");
		} catch (Exception e) {
			System.out.println("The table has been enabled");
		}
	}
	
	public static void Descriptor_Table(HBaseAdmin admin) throws IOException {
	      HColumnDescriptor columnDescriptor = 
	    		  new HColumnDescriptor("contactDetails");
	      
	      // Adding column family
	      admin.addColumn(TableName.valueOf("Sinh_Vien"), columnDescriptor);
	      System.out.println("coloumn added");
	}
	public static void Exists_Table(HBaseAdmin admin) throws IOException {
	      System.out.println(admin.tableExists(TableName.valueOf("Sinh_Vien")));
	}
	public static void Drop_Table(HBaseAdmin admin) throws IOException {
	      admin.disableTable(TableName.valueOf("category"));
	      admin.deleteTable(TableName.valueOf("category"));
	      System.out.println("Table deleted");
	}
	

	public static void main(String[] args) throws IOException {
		Configuration conf = HBaseConfiguration.create();
		Connection conn = ConnectionFactory.createConnection(conf);
		HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();

		Create_Table(admin);
		//Listing_Table(admin);
		//Disable_Table(admin);
		//Enable_Table(admin);
		//Descriptor_Table(admin);
		//Exists_Table(admin);
		//Drop_Table(admin);
		conn.close();
	}

}
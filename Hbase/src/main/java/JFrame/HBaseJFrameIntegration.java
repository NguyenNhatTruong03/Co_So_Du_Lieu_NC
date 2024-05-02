package JFrame;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HBaseJFrameIntegration extends JFrame {
    private static final String TABLE_NAME = "category";
    private static final String COLUMN_FAMILY = "Data";
    private static final String COLUMN_QUALIFIER = "ten_the_loai";

    private JLabel dataLabel;

    public HBaseJFrameIntegration() {
        setTitle("HBase Data Viewer");
        setSize(684, 424);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo label để hiển thị dữ liệu từ HBase
        dataLabel = new JLabel("Data from HBase will be displayed here");
        dataLabel.setHorizontalAlignment(SwingConstants.LEFT);
        getContentPane().add(dataLabel, BorderLayout.CENTER);
    }

    private void fetchDataFromHBase() {
        try {
            // Tạo cấu hình HBase
            org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
            

            // Tạo kết nối tới HBase
            Connection connection = ConnectionFactory.createConnection(config);

            // Lấy bảng
            Table table = connection.getTable(org.apache.hadoop.hbase.TableName.valueOf(TABLE_NAME));

            // Lấy dữ liệu từ HBase
            Get get = new Get(Bytes.toBytes("row1"));
            Result result = table.get(get);
            byte[] valueBytes = result.getValue(Bytes.toBytes(COLUMN_FAMILY), Bytes.toBytes(COLUMN_QUALIFIER));

            // Hiển thị dữ liệu trên label
            if (valueBytes != null) {
                String value = Bytes.toString(valueBytes);
                dataLabel.setText(value);
            }

            // Đóng kết nối
            table.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HBaseJFrameIntegration frame = new HBaseJFrameIntegration();
            frame.setVisible(true);
            frame.fetchDataFromHBase();
        });
    }
}


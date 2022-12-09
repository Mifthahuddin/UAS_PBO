package abstarct;

import entity.Riwayat;
import entity.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseUtil;

/**
 *
 * @author ACER
 */
public abstract class RiwayatAbstract {
    public static Riwayat[] getAll() {
        String sql = "SELECT id, nama, harga, tanggal, username FROM riwayat_pembelian";
        try (Connection connection = DatabaseUtil.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            List<Riwayat> list = new ArrayList<>();
            while(resultSet.next()) {
                Riwayat riwayat = new Riwayat();
                riwayat.setId(resultSet.getInt("id"));
                riwayat.setNameRiwayat(resultSet.getString("nama"));
                riwayat.setPrice(resultSet.getInt("harga"));
                riwayat.setusername(resultSet.getString("username"));
                list.add(riwayat);
            }
            
            return list.toArray(new Riwayat[]{});
        } catch (SQLException exception) {
          throw new RuntimeException(exception);
        }
    }
    
    public static void add(Riwayat riwayat) {
        String sql = "INSERT INTO riwayat_pembelian(nama, harga, username) VALUES (?,?,?)";

        try {
            Connection connection = DatabaseUtil.connect();
            PreparedStatement statement = connection.prepareStatement(sql);  
            statement.setString(1, riwayat.getNameRiwayat());
            statement.setInt(2, riwayat.getPrice());
            statement.setString(3, riwayat.getusername());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}

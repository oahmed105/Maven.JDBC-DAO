package models;

import com.mysql.jdbc.Driver;
import daos.CarDao;

import java.sql.*;
import java.util.Set;

public class ConnectionFactory implements CarDao {
    public static final String URL = "jdbc:mysql://localhost:3306/cars";
    public static final String USER = "osama";
    public static final String PASS = "osama123";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
    }

    private Cars extractCarFromResultSet(ResultSet rs) throws SQLException {
        Cars user = new Cars();

        user.setId( rs.getInt("id") );
        user.setMake( rs.getString("make") );
        user.setModel( rs.getString("model") );
        user.setYear( rs.getString("year") );
        user.setColor( rs.getString("color") );
        user.setVin( rs.getString("vin") );

        return user;
    }

    @Override
    public Cars getCar(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM car WHERE id =" + id);

            if (rs.next())
            {
                return extractCarFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Set<Cars> getAllCars() {
        Connector connector = new Connector();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            Set users = new HashSet();

            while(rs.next())
            {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }

            return users;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Cars getCarByMakeAndModel(String make, String model) {
        Connector connector = new Connector();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM car WHERE make = ? AND model = ?");
            ps.setString(1, make);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                return extractCarFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertCar(Cars car) {
        return false;
    }

    @Override
    public boolean updateCar(Cars car) {
        return false;
    }

    @Override
    public boolean deleteCar(int id) {
        return false;
    }
}

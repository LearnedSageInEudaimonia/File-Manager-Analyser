package db;

import history.ActionHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogExporter {
    public static void exportToDB(ActionHistory history) throws SQLException {
        try(Connection conn = DBConnection.getConnection()){
            String[] actions = history.getAll();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO action_logs(description) Values (?)");
            for(String action : actions){
                statement.setString(1,action);
                statement.executeUpdate();
            }
            System.out.println("Exported " + actions.length+ "logs to DB");
        }
    }
}

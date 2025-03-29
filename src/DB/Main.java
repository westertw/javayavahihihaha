package DB;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQL db = new SQL();
        db.isConnection();
        db.createTable("competitions");
        db.addTable("TypeSports");
        db.addTable1("services");
        db.addTable2("members");
        db.addTable3("coaches");
        db.addTable4("competitions");
        db.selectTable("TypeSports");
        db.updateTable("competitions");
        db.deleteTable("members");
    }
}

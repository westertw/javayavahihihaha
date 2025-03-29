package DB;
import java.sql.*;

public class SQL {

    private final String host = "localhost";
    private final String port = "5432";
    private final String dbName = "SportClub";
    private final String login = "postgres";
    private final String password = "12345";

    private Connection dbCon; // подключение к бд

    private Connection getDBConnection() {
        String str = "jdbc:postgresql://" + host + ":"
                + port + "/" + dbName;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Соединение установлено");
        } catch (ClassNotFoundException e) {
            System.out.println("Соединение не установлено");
        }
        try {
            dbCon = DriverManager.getConnection(str, login, password);
        } catch (SQLException e) {
            System.out.println("Неверный путь (логин и пароль)");
        }
        return dbCon;
    }

    public void isConnection() throws SQLException, ClassNotFoundException {
        dbCon = getDBConnection();
        System.out.println(dbCon.isValid(1000));
    }

    public void createTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (id INT PRIMARY KEY, name VARCHAR(25), date INT, type VARCHAR(20))";

        try {
            Statement statement = getDBConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица создалась");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTable(String table) {
        try {
            Statement statement = getDBConnection().createStatement();
            int rows = statement.executeUpdate("INSERT INTO "
                    + table + "(id, types) " +
                    "VALUES (10, 'Velosport');");
            System.out.printf("Добавлено %d строк ", rows);
        } catch (SQLException e) {
            System.out.println("Не удалось добавить");
        }
    }

    public void addTable1(String table) {
        try {
            Statement statement = getDBConnection().createStatement();
            int rows = statement.executeUpdate("INSERT INTO "
                    + table + "(id, services) " +
                    "VALUES (10, 'Организация выездов');");
            System.out.printf("Добавлено %d строк ", rows);
        } catch (SQLException e) {
            System.out.println("Не удалось добавить");
        }
    }

    public void addTable2(String table) {
        try {
            Statement statement = getDBConnection().createStatement();
            int rows = statement.executeUpdate("INSERT INTO "
                    + table + "(id, name, age, type) " +
                    "VALUES (10, 'Наталья', 26, 'Фитнес');");
            System.out.printf("Добавлено %d строк ", rows);
        } catch (SQLException e) {
            System.out.println("Не удалось добавить");
        }
    }

    public void addTable3(String table) {
        try {
            Statement statement = getDBConnection().createStatement();
            int rows = statement.executeUpdate("INSERT INTO "
                    + table + "(id, name, specialization, experience  ) " +
                    "VALUES (10, 'Наталья', 'Велоспорт', 3);");
            System.out.printf("Добавлено %d строк ", rows);
        } catch (SQLException e) {
            System.out.println("Не удалось добавить");
        }
    }

    public void addTable4(String table) {
        try {
            Statement statement = getDBConnection().createStatement();
            int rows = statement.executeUpdate("INSERT INTO "
                    + table + "(id, name, date, type) " +
                    "VALUES (10, 'Зимний кубок', 15022025, 'Плавание');");
            System.out.printf("Добавлено %d строк ", rows);
        } catch (SQLException e) {
            System.out.println("Не удалось добавить");
        }
    }

    public void selectTable(String tableName) throws SQLException {
        Statement statement = getDBConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from " + tableName);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String type = resultSet.getString(2);
            System.out.printf("%d. %s. \n", id, type);
        }
    }

    public void updateTable(String tableName) throws SQLException {
        try {
            Statement statement = getDBConnection().createStatement();
            int update = statement.executeUpdate("UPDATE " + tableName + " SET name = 'Чемпионат мира' WHERE name = 'Чемпионат города' ");
            System.out.printf("Добавлено %d строк ", update);
        } catch (SQLException e) {
            System.out.println("Не удалось обновить");
        }
    }

    public void deleteTable(String tableName) throws SQLException {
        try {
            Statement statement = getDBConnection().createStatement();
            int delete = statement.executeUpdate("DELETE FROM " + tableName + " WHERE id = 1");
            System.out.printf("Добавлено %d строк ", delete);
        } catch (SQLException e) {
            System.out.println("Не удалось удалить");
        }

    }
}
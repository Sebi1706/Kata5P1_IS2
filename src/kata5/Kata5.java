
package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static kata5.MailListReader.read;
import java.sql.PreparedStatement;

public class Kata5 {
    
    public static final String ruta = "C:\\Users\\Sebi\\Documents\\NetBeansProjects\\Kata5\\email.txt";
    List<String> emails = read(ruta);

    public static void main(String[] args) {
        Kata5 bd = new Kata5();
        bd.connect();
        bd.createNewTable();
        MailListReader mailListReader = new MailListReader();
        List <String> items = mailListReader.read("email.txt");
        for (String line : items) {
            bd.insert(line);
        }
    }
    
    public void insert(String email) {
        String sql = "INSERT INTO EMAIL(Mail) VALUES(?)";
        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:mail.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    public void selectAll(){
        String sql = "SELECT * FROM PEOPLE";
        try (Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            // Bucle sobre el conjunto de registros.
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                rs.getString("Nombre") + "\t" +
                rs.getString("Apellidos") + "\t" +
                rs.getString("Departamento") + "\t");
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable() {
        // Cadena de conexión SQLite
        String url = "jdbc:sqlite:mail.db";
        // Instrucción SQL para crear nueva tabla
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
        + " id integer PRIMARY KEY AUTOINCREMENT,\n"
        + " Mail text NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement()) {
            // Se crea la nueva tabla
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    
    
}

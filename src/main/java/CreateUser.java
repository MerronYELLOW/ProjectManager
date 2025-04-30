import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateUser extends Exception {

    private final String url;
    private final String adminAccount;
    private final String adminPassword;

    /**
     * Constructor for the MySQL User Manager
     * @param host MySQL server hostname
     * @param port MySQL server port (typically 3306)
     * @param adminAccount Admin username with user creation privileges
     * @param adminPassword Admin password
     */
    public CreateUser(String host, int port, String adminAccount, String adminPassword) {
        this.url = "jdbc:mysql://" + host + ":" + port + "/";
        this.adminAccount = adminAccount;
        this.adminPassword = adminPassword;
    }

    /**
     * Creates a new MySQL user
     * @param username New username to create
     * @param password Password for the new user
     * @param hostPattern Host pattern (use '%' for any host, 'localhost' for local)
     * @return true if user was created successfully
     * @throws SQLException if there's a database error
     */
    public boolean userCreation(String username, String password, String hostPattern) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, adminAccount, adminPassword)) {
            String sql = "CREATE USER ?@? IDENTIFIED BY ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, hostPattern);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
                return true;
            }
        }
    }

    /**
     * Grants permissions to a user for a specific database
     * @param username Username to grant permissions to
     * @param hostPattern Host pattern matching the user creation pattern
     * @param database Database name
     * @param permissions Comma-separated list of permissions (e.g., "SELECT,INSERT")
     * @return true if permissions were granted successfully
     * @throws SQLException if there's a database error
     */
    public boolean grantPermissions(String username, String hostPattern, String database, String permissions) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, adminAccount, adminPassword)) {
            String sql = "GRANT " + permissions + " ON " + database + ".* TO ?@?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, hostPattern);
                pstmt.executeUpdate();

                // Apply the privileges
                try (PreparedStatement flushStmt = conn.prepareStatement("FLUSH PRIVILEGES")) {
                    flushStmt.executeUpdate();
                }
                return true;
            }
        }
    }

    /**
     * Deletes a MySQL user
     * @param username Username to delete
     * @param hostPattern Host pattern matching the user creation pattern
     * @return true if user was deleted successfully
     * @throws SQLException if there's a database error
     */
    public boolean deleteUser(String username, String hostPattern) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, adminAccount, adminPassword)) {
            String sql = "DROP USER ?@?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, hostPattern);
                pstmt.executeUpdate();
                return true;
            }
        }
    }
}
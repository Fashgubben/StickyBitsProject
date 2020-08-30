import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class RetryConn {

    public static void runCommand(String... command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder().command(command);

        try {
            Process process = processBuilder.start();

            // read the output
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String output = null;
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
            }

            // wait for the process to complete
            process.waitFor();

            // close the resources
            bufferedReader.close();
            process.destroy();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        int retryCount = 60;
        boolean transactionCompleted = false;
        do {

            try {

                // root
                String userName = "root";
                // password
                String userPassword = "password";
                // mysqlDB:3306
                String host = "mysqlDB:3306";
                
                String path = "jdbc:mysql://" + host + "/fleet?serverTimezone=UTC";
                conn = DriverManager.getConnection(path, userName,
                        userPassword);

                retryCount = 0;
                stmt = conn.createStatement();
                String query = "SELECT * FROM uvshipposition";
                rs = stmt.executeQuery(query);

                rs.close();
                rs = null;
                stmt.close();
                stmt = null;

                conn.close();
                conn = null;
                transactionCompleted = true;

                // Run shell command from Java
                try {
                    runCommand("java", "-classpath", "../lib/mysql-connector-java-8.0.18.jar:.", "Armada");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (SQLException sqlEx) {

                String sqlState = sqlEx.getSQLState();
                // Put right sqlState here:
                if ("08S01".equals(sqlState)) {
                    System.out.println(sqlState);
                    System.out.println(sqlEx);
                    retryCount--;
                } else {
                    System.out.println(sqlState);
                    System.out.println(sqlEx);
                    System.out.println("Hello");
                    retryCount = 0;
                }
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                        // log this
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) {
                        // log this
                    }
                }
                if (conn != null) {
                    try {
                        //
                        // If we got here, and conn is not null, the
                        // transaction should be rolled back, as not
                        // all work has been done
                        try {
                            conn.rollback();
                        } finally {

                            conn.close();
                        }
                    } catch (SQLException sqlEx) {
                        //
                        // If we got an exception here, something
                        // pretty serious is going on, so we better
                        // pass it up the stack, rather than just
                        // logging it. . .
                        throw sqlEx;
                    }
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException inEx) {
                inEx.printStackTrace();
            }

        } while (!transactionCompleted && (retryCount > 0));
    }
}
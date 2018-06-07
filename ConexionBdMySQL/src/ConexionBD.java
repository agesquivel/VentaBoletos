import java.sql.*;
import javax.swing.JOptionPane;


public class ConexionBD {
        public String db = "bdBoletaje";
        public String url = "jdbc:mysql://localhost/" + db;
        public String user = "root";
        public String pass = "clvprinc";

        public Connection Conectar(){
            Connection link = null;

            try{
                //Class.forName("org.gjt.mm.mysql.Driver");  //version 5
                Class.forName("com.mysql.cj.jdbc.Driver");   //version 8

                link = DriverManager.getConnection(this.url, this.user, this.pass);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }

            return link;
        }
}


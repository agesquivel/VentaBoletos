import java.sql.*;
import javax.swing.JOptionPane;

public class ConexionBD {
        public String db = "bdBoletaje";
        public String url = "jdbc:mysql://localhost/" + db
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false"
                + "&serverTimezone=UTC";
        public String user = "root";
        public String pass = "clvprinc";

        Connection link;

        public Connection Conectar(){
            link = null;

            try{
                //Class.forName("org.gjt.mm.mysql.Driver");  //version 5
                Class.forName("com.mysql.cj.jdbc.Driver");   //version 8

                link = DriverManager.getConnection(this.url, this.user, this.pass);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }

            return link;
        }

        public int insertarCliente(String nombre, String apellidos, String direccion){
            int numRegs = 0;
            try {
                PreparedStatement stInsertar = link.prepareStatement(
                        "insert into cliente(nombre, apellidos, direccion)"
                                + " values(?, ?, ?)");

                stInsertar.setString(1, nombre);
                stInsertar.setString(2, apellidos);
                stInsertar.setString(3, direccion);

                numRegs = stInsertar.executeUpdate();
            }
            catch(SQLException error){
                error.printStackTrace();
            }

            return numRegs;
        }

    public ResultSet consultarClientes(){
        ResultSet registros = null;
        try {
            PreparedStatement stConsultar = link.prepareStatement(
                    "Select nombre, apellidos, direccion from cliente order by clienteID");

            registros = stConsultar.executeQuery();
        }
        catch(SQLException error){
            error.printStackTrace();
        }

        return registros;
    }

    public ResultSet consultarClienteAp(String parAp){
        ResultSet registros = null;
        try {
            PreparedStatement stConsultaAp = link.prepareStatement(
                    "Select nombre, apellidos, direccion " +
                            "from cliente " +
                            "where apellidos like ? "
                            +"order by clienteID");
            stConsultaAp.setString(1, "%" + parAp+ "%");
            registros = stConsultaAp.executeQuery();
        }
        catch(SQLException error){
            error.printStackTrace();
        }

        return registros;
    }
}










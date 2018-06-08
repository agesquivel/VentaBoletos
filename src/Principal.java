import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal extends JFrame implements ActionListener{

    JButton btnConectar;
    JButton btnInsertar;
    JButton btnConsultar;
    JButton btnConsultarAp;

    ConexionBD interfazMysql;

    public Principal(){
        //Creación de interfaz
        JFrame ventana = new JFrame("Ejemplo de uso de BD");
        Container contPrinc = ventana.getContentPane();

        ventana.setSize(600,400);
        ventana.setLocationRelativeTo(null);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Construir la interfaz
        //Crear botón para conectar
        btnConectar = new JButton("Conectar");
        btnConectar.setActionCommand("acConectar");
        btnConectar.addActionListener(this);
        contPrinc.add(btnConectar, BorderLayout.NORTH);

        //Crear botón para insertar
        btnInsertar = new JButton("Insertar");
        btnInsertar.setActionCommand("acInsertar");
        btnInsertar.addActionListener(this);
        contPrinc.add(btnInsertar, BorderLayout.WEST);

        //Crear botón para Consultar
        btnConsultar = new JButton("Consultar");
        btnConsultar.setActionCommand("acConsultar");
        btnConsultar.addActionListener(this);
        contPrinc.add(btnConsultar, BorderLayout.EAST);

        //Crear botón para ConsultaPorAp
        btnConsultarAp = new JButton("Consultar por apellido");
        btnConsultarAp.setActionCommand("acConsultarAp");
        btnConsultarAp.addActionListener(this);
        contPrinc.add(btnConsultarAp, BorderLayout.SOUTH);

        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("acConectar")) {
            //Usar la conexión BD
            interfazMysql = new ConexionBD();
            Connection bdCon = interfazMysql.Conectar();

            if (bdCon != null) {
                JOptionPane.showMessageDialog(null,
                        "Conectado");
            }
        }
        else if (e.getActionCommand().equals("acInsertar")) {
            int nRegs = interfazMysql.insertarCliente("Sonia", "Acevedo Ortíz",
                    "Av. Héroes #100 Col. Centro, Chetumal" );
            System.out.println("Núm de registro afectados: " + nRegs);
        }
        else if (e.getActionCommand().equals("acConsultar")) {
            ResultSet regs = interfazMysql.consultarClientes();
            int cont = 0;
            try {
                while (regs.next()) {
                    cont++;
                    System.out.println("Núm " + cont + ": "
                            + regs.getString("nombre") + ", "
                            + regs.getString("apellidos") + ", "
                            + regs.getString("direccion"));
                }
            }
            catch (SQLException errSelect){
                errSelect.printStackTrace();
            }
        }
        else if (e.getActionCommand().equals("acConsultarAp")) {
            ResultSet regs = interfazMysql.consultarClienteAp("ez");
            int cont = 0;

            System.out.println("Registros recuperados");
            try {
                while (regs.next()) {
                    cont++;
                    System.out.println("Núm " + cont + ": "
                            + regs.getString("nombre") + ", "
                            + regs.getString("apellidos") + ", "
                            + regs.getString("direccion"));
                }
            }
            catch (SQLException errSelect){
                errSelect.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Principal vtnPrinc = new Principal();
    }

}











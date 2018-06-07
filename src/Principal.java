import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Principal extends JFrame implements ActionListener{

    JButton btnConectar;

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
        btnConectar.addActionListener(this);
        contPrinc.add(btnConectar, BorderLayout.NORTH);

        ventana.setVisible(true);
    }

    public static void main(String[] args){
        Principal vtnPrinc = new Principal();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Usar la conexión BD
        ConexionBD con = new ConexionBD();
        Connection bdCon = con.Conectar();

        if (bdCon != null){
            JOptionPane.showMessageDialog(null,
                    "Conectado");
        }
    }
}

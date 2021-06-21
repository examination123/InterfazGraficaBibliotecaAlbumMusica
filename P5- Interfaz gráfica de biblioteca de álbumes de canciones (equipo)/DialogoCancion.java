
/*********************************************************
* Clase DialogoCancion, Interfaz y eventos               *
* @authors Cristina Quintana Sánchez y Niko Rodriguez    *
* @date 09/01/2017                                       *
**********************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogoCancion extends JDialog{
    private JTextField tituloCancion;
    private JTextField nombreInterprete;
    private JTextField duracion;
    private JButton ok;
    private JButton cancel;
    private Cancion song;
    
    /*Creamos aquí la interfaz donde introducimos el título, intérprete y 
    duración de la canción que incluimos en el álbum que se incluirá luego en 
    biblioteca*/
    
    public DialogoCancion(JFrame j,String s,Album al,JTextArea tA) {
        super(j,s,true);
        tituloCancion = new JTextField(15);
        nombreInterprete = new JTextField (15);
        duracion = new JTextField (15);
        ok = new JButton("OK");
        cancel = new JButton("Cancelar");
        Container c = getContentPane();
        JPanel rellenos = new JPanel();
        JPanel botones = new JPanel();
        rellenos.setLayout(new FlowLayout());
        rellenos.add(new JLabel("Título: "));
        rellenos.add(tituloCancion);
        rellenos.add(new JLabel("Intérprete: "));
        rellenos.add(nombreInterprete);
        rellenos.add(new JLabel("Duración: "));
        rellenos.add(duracion);
        getContentPane().add(rellenos);
        botones.add(ok);
        botones.add(cancel);
        c.add(botones,BorderLayout.SOUTH);
        
        /*Aquí creamos los eventos que deben cumplirse para que se guarde la 
        canción (clicando una vez a "OK") o para cancelar la opción (clicando 
        una vez en "Cancelar")*/
        
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                song = new Cancion(tituloCancion.getText(),nombreInterprete.getText(),Integer.parseInt(duracion.getText()));
                al.añadeCanción(song);
                tA.setText(al.toString());
                setVisible(false);
            }
        });
        setLocationRelativeTo(j);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,140);
    }
}


/*********************************************************
* Clase DialogoAlbum, Interfaz y eventos                 *
* @authors Cristina Quintana Sánchez y Niko Rodriguez    *
* @date 09/01/2017                                       *
**********************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogoAlbum extends JDialog{
    private JTextArea canciones;
    private JButton newCancion;
    private JButton finAlbum;
    private JButton cancel;
    private DialogoCancion dC;
    private Album al;
    
    /*Creamos aquí la interfaz donde almacenamos el título, intérprete y 
    duración de la canción que incluimos en el álbum que se incluirá luego en 
    biblioteca*/
    
    public DialogoAlbum(JFrame j,String s,String album,Biblioteca biblio,JTextArea tA){
        super(j,s,true);
        al = new Album(album);
        Container c = getContentPane();
        canciones = new JTextArea(20,25);
        newCancion = new JButton("Añadir canción");
        finAlbum = new JButton("Finalizar álbum");
        cancel = new JButton("Cancelar");
        c.add(new JScrollPane(canciones),BorderLayout.CENTER);
        JPanel boton1 = new JPanel();
        JPanel botonesSur = new JPanel();
        boton1.add(newCancion);
        botonesSur.add(finAlbum);
        botonesSur.add(cancel);
        c.add(boton1,BorderLayout.WEST);
        c.add(botonesSur,BorderLayout.SOUTH);
        canciones.setEditable(false);
        
        /*Aquí creamos los eventos que deben cumplirse para añadir una nueva 
        canción(clicando una vez en "Añadir Canción" y que abra DialogoCancion), 
        dejar de introducir canciones finalizando el álbum(Clicando una vez en 
        "Finalizar álbum", añadiéndolo a la biblioteca)o cancelar (Clicando 
        una vez en "Cancelar)*/
        
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent ae){
                setVisible(false);
            }
        });
        newCancion.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent ae){
                dC = new DialogoCancion(j,"Nueva canción",al,canciones);
                dC.setVisible(true);
            }
        });
        finAlbum.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent ae){
                biblio.añadeÁlbum(al);
                tA.setText(biblio.toString());
                setVisible(false);
            }
        });
        canciones.setText(al.toString());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(j);
        
    }
}
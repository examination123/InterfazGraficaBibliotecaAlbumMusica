
/*********************************************************
* Clase BibliotecaGUI, Interfaz y eventos                *
* @authors Cristina Quintana Sánchez y Niko Rodriguez    *
* @date 09/01/2017                                       *
**********************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BibliotecaGUI extends JFrame{
    private JTextArea lista;
    private JButton otro;
    private Biblioteca miBiblioteca;
    private AñadeAlbum aA;
    
    private void initBiblioteca(){
        
        miBiblioteca = new Biblioteca();
        Cancion canción1 = new Cancion("song1","i1",90);
        Cancion canción2 = new Cancion("song2","i1",90);
        Cancion canción3 = new Cancion("song3","i2",120);
        Cancion canción4 = new Cancion("song1","i2",120);
        Cancion canción5 = new Cancion("song3","i2",120);
        Cancion canción6 = new Cancion("song4","i3",150);
        Cancion canción7 = new Cancion("song1","i3",150);

        Album álbum1 = new Album("Álbum 1");
        álbum1.añadeCanción(canción1);
        álbum1.añadeCanción(canción2);
        álbum1.añadeCanción(canción3);

        Album álbum2 = new Album("Álbum 2");
        álbum2.añadeCanción(canción4);
        álbum2.añadeCanción(canción5);
        álbum2.añadeCanción(canción6);
        álbum2.añadeCanción(canción7);
        
        miBiblioteca.añadeÁlbum(álbum1);
        miBiblioteca.añadeÁlbum(álbum2);
    }
    
    /*Creamos aquí la interfaz donde se almacenan los álbumes nuevos con las 
    canciones introducidas dentro del album*/
    
    public BibliotecaGUI(String t){
        super(t);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initBiblioteca();
        Container p = getContentPane();
        lista = new JTextArea(20,25);
        otro = new JButton("Añadir álbum");
        p.add(new JScrollPane(lista),BorderLayout.CENTER);
        JPanel rellenoBotón = new JPanel();
        rellenoBotón.add(otro);
        p.add(rellenoBotón,BorderLayout.WEST);
        lista.setEditable(false);
        lista.setText(miBiblioteca.toString());

        /*Aquí creamos el evento que debe cumplirse para que empiece a crear el
        album añadiéndole el nombre*/
        
        otro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                aA = new AñadeAlbum(BibliotecaGUI.this,"Crear álbum",lista,miBiblioteca);
                aA.setVisible(true);
            }
        });    
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args){
        new BibliotecaGUI("Mi biblioteca");
    }
    
    /*Creamos aquí la interfaz donde se escribe el nombre del álbum*/
    
    class AñadeAlbum extends JDialog{
        private JTextField nombreAlbum;
        private DialogoAlbum dA;
        private JButton ok;
        private JButton cancel;
    
        public AñadeAlbum(JFrame j, String s,JTextArea tA,Biblioteca biblio){
            super(j,s,true);
            nombreAlbum = new JTextField(15);
            ok = new JButton("OK");
            cancel = new JButton ("Cancelar");
            Container c = getContentPane();
            c.add(new JLabel("Introduzca el nombre del álbum: "));
            JPanel p = new JPanel();
            JPanel bp = new JPanel();
            p.add(nombreAlbum);
            bp.add(ok);
            bp.add(cancel);
            c.add(bp,BorderLayout.SOUTH);
            getContentPane().add(p);
            
            /*Aquí creamos el evento que debe cumplirse para que empiece a crear el
            album cliclando una vez en "OK" para pasar el nombre del álbum para 
            luego ir a DialogoAlbum y empezar a incluir canciones o clicando a 
            cancelar para abortar*/
            
            cancel.addActionListener(new ActionListener(){
                public void actionPerformed (ActionEvent ae){
                    setVisible(false);
                }
            });
            ok.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    /*A veces sale un error, pero cuando se modifica con un espacio o quitándolo se arregla?
                    En realidad no existe error, porque compila y el programa funciona a la perfección*/
                    dA = new DialogoAlbum(j,"Nuevo álbum", nombreAlbum.getText(),biblio,tA);
                    dA.setVisible(true);
                    nombreAlbum.setText("");
                    dA.setVisible(false);
                    dispose();
                }
            });
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();
            setLocationRelativeTo(j);
        }
    }
}

package pingopong;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {
    final static int ANCHURA = 1000;
    final static int ALTURA = 500;
    
    public Main(){
        JFrame ventana = new JFrame();
        ventana.setSize(ANCHURA, ALTURA);
        ventana.setBounds(0,0,ANCHURA,ALTURA);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setTitle("PING PONG");
        
        Gameplay gmp = new Gameplay();
        ventana.add(gmp);
    }
    
   public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new Main();
    });
}

}

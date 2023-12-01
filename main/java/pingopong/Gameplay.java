package pingopong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean juego = false;
    private boolean gameover = false;

    private int scorepy1 = 0;
    private int scorepy2 = 0;

    private Timer timer;
    private int tiempo = 7;

    private int jugador1X = Main.ANCHURA - 35;
    private int jugador1Y = Main.ALTURA / 2 - 75;

    private int jugador2X = 5;
    private int jugador2Y = Main.ALTURA / 2 - 75;

    private int bolax = 200;
    private int bolay = 300;
    private int dirbolax = 3;
    private int dirbolay = 4;

    private boolean up1 = false;
    private boolean down1 = false;
    private boolean up2 = false;
    private boolean down2 = false;

    public Gameplay() {
        timer = new Timer(tiempo, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
        requestFocus(); 
    }

    public void moverBola() {
        if (juego) {
            bolax += dirbolax;
            bolay += dirbolay;
            if (bolax > 1000 - 36) {
                gameover();
            }
            if (bolax < 0) {
                gameover();
            }
            if (bolay < 0) {
                dirbolay = -dirbolay;
            }
            if (bolay > 500 - 55) {
                dirbolay = -dirbolay;
            }
        }
    }

    public void moverJugadores() {
        if (juego) {
            if (up1) {
                if (jugador1Y > 10) {
                    jugador1Y -= 3;
                }
            }
            if (down1) {
                if (jugador1Y < 360) {
                    jugador1Y += 3;
                }
            }
            if (up2) {
                if (jugador2Y > 10) {
                    jugador2Y -= 3;
                }
            }
            if (down2) {
                if (jugador2Y < 360) {
                    jugador2Y += 3;
                }
            }
        }
    }

    public void colisiones() {
        if (new Rectangle(bolax, bolay, 20, 20).intersects(jugador1X, jugador1Y, 15, 100)) {
            dirbolax = -dirbolax;
            scorepy1++;
        }
        if (new Rectangle(bolax, bolay, 20, 20).intersects(jugador2X, jugador2Y, 15, 100)) {
            dirbolax = -dirbolax;
            scorepy2++;
        }
    }
    
  
    public void reiniciar() {
        juego = true;
        gameover = false;
        bolax = 200;
        bolay = 300;
        timer.start();
        scorepy1 = 0;
        scorepy2 = 0;
    }
    
    public void gameover(){
        gameover = true;
        juego = false;
        timer.stop();
    }

    @Override
    public void paint(Graphics g) {
        moverBola();
        moverJugadores();
        colisiones();

        //pintando bk
        g.setColor(Color.black);
        g.fillRect(0, 0, Main.ANCHURA, Main.ALTURA);

        //pintando jugador1
        g.setColor(Color.white);
        g.fillRect(jugador1X, jugador1Y, 15, 100);

        //pintando jugador2
        g.setColor(Color.white);
        g.fillRect(jugador2X, jugador2Y, 15, 100);

        //pintando bola
        g.setColor(Color.red);
        g.fillOval(bolax, bolay, 20, 20);
        
        //puntuacion
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 22));
        g.drawString("SCORE: " + scorepy2, 20, 20);
        g.drawString("SCORE: " + scorepy1, 860, 20);
        
        //mensaje de inicio
        if(!juego && !gameover){
            g.drawString("Presion enter para iniciar", 334, 130);
        }
    
        //final del juego
        if(gameover){
            if(scorepy1 == scorepy2){
                g.drawString("Empate", 450, 130);
            }else if (scorepy1 > scorepy2){
                g.drawString("Winner Jugador1", 410, 130);
            }else{
                g.drawString("Winner Jugador2", 410, 130);
            }
            g.drawString("Presion enter para reiniciar", 334, 170);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up1 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down1 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up2 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down2 = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            juego = true;
            if (gameover) {
                reiniciar();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up1 = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down1 = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up2 = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down2 = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        timer.start();
    }
}

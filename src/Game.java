
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

    //Dimensões da janela
    private final int WIDTH = 480, HEIGHT = 250;

    //Define objeto jogador
    Player player;
    
    //Cria tudo que não seja parte do looop principal
    public Game() {
        //Seta as dimensões da janela
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        //Adiciona as teclas para serem coletadas do teclado
        this.addKeyListener(this);

        //Cria objeto do jogador
        player = new Player(0, 0);
    }

    //Cria a tela em si
    public static void main(String[] args) throws Exception {
        Game jogo = new Game(); //Cria o jogo
        JFrame frame = new JFrame(); //Cria a Janela

        frame.add(jogo); //Adiciona o jogo na janela
        frame.setTitle("Janelinha"); //Define um titulo para a janela
        frame.pack(); //Empacota tudas as configurações da janela
        frame.setLocationRelativeTo(null); //Cria a janela no centro da tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando fechar a janela, encerra o programa
        frame.setVisible(true); //Deixa a Jnaela visivel

        new Thread(jogo).start(); //Roda o run (Game Looping)
    }

    //Lógica do jogo
    public void tick() {
        player.tick();
    }

    //Gráficos do jogo
    public void render() {
        //Otimização da exibição de gráficos
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        //Fundo preto atrás de tudo
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //Renderiza o jogador
        player.render(g);

        //Exibir tudo que foi desenhado
        bs.show();
    }

    //Toda execução do jogo
    @Override
    public void run() {
        while (true) { //Game Loopíng
            tick();
            render();
            try {
                Thread.sleep(1000/60); //Executar o loop a 60 fps (frames por segundo)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //Movimentação jogador
        int tecla = e.getKeyCode();
        if (Ferramentas.boolToInt((tecla == KeyEvent.VK_UP) || (tecla == KeyEvent.VK_W)) == 1) {
            player.W = 1;
        }
        if (Ferramentas.boolToInt((tecla == KeyEvent.VK_LEFT) || (tecla == KeyEvent.VK_A)) == 1) {
            player.A = 1;
        }
        if (Ferramentas.boolToInt((tecla == KeyEvent.VK_DOWN) || (tecla == KeyEvent.VK_S)) == 1) {
            player.S = 1;
        }
        if (Ferramentas.boolToInt((tecla == KeyEvent.VK_RIGHT) || (tecla == KeyEvent.VK_D)) == 1) {
            player.D = 1;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        //Movimentação jogador
        int tecla = e.getKeyCode();
        if (Ferramentas.boolToInt((tecla == KeyEvent.VK_UP) || (tecla == KeyEvent.VK_W)) == 1) {
            player.W = 0;
        }
        if (Ferramentas.boolToInt((tecla == KeyEvent.VK_LEFT) || (tecla == KeyEvent.VK_A)) == 1) {
            player.A = 0;
        }
        if (Ferramentas.boolToInt((tecla == KeyEvent.VK_DOWN) || (tecla == KeyEvent.VK_S)) == 1) {
            player.S = 0;
        }
        if (Ferramentas.boolToInt((tecla == KeyEvent.VK_RIGHT) || (tecla == KeyEvent.VK_D)) == 1) {
            player.D = 0;
        }

    }





}

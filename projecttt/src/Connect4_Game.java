import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

public class Connect4_Game extends Canvas implements Runnable {

    public static final int WIDTH = 900, HEIGHT = 900;
    public static float scale = 1f;
    public int w=9, h=9;
    
    public String player1,player2;

    private Game game;
    private Mouse mouse;
    private JFrame frame;
    private Thread thread;
    private boolean running = false;

    public static BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    public static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    public int[][] board = new int[h][w];
    static Game gamee;



    public void colorRandom(){

        List<String> color = new ArrayList<String>();
        color.add("Blue");
        color.add("Pink");
        Random random = new Random();
        for(int i = 0 ; i < 2 ; i++){
            int randomIndex = random.nextInt(color.size());
            String randomElement = color.get(randomIndex);
            randomElement = player1;
            if(player1.equals("Blue")) {
                System.out.println("Player1 your color is blue.You have to make first move");
            }
            else{
                System.out.println("Color is Pink. Player2 you have to make first move");
            }
            
        }
    }


    public void Connect4_Game(){

        setPreferredSize(new Dimension((int)(WIDTH*scale),(int)(HEIGHT*scale)));
        frame = new JFrame();
        game = new Game();
        mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        


    }
    public void start(){
        running = true;
        thread = new Thread(this,"loop");
        thread.start();

    }
    public void stop(){
        try{
            thread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();

        }

    }
    @Override
    public void run() {
        int frames = 0;
        int updates = 0;
        double updatesPerf = 0;

        while(running){
            update();
            updates++;
            updatesPerf--;
         
        render();
        frames++;
        }
        stop();

    }
    public void update(){
        game.update();
        mouse.update();

    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }
        game.render();
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.drawImage(image,0, 0, (int)(WIDTH*scale),(int)(HEIGHT*scale),null);
        game.renderText(g);
        g.dispose();
        bs.show();
    }

    private static void saveGame(){
        try{
            FileOutputStream fos1 = new FileOutputStream("tahta.txt");
            ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
            oos1.writeObject(gamee);
            oos1.flush();//write out any buffered bytes
            oos1.close();
            System.out.println("Game Saved");
            FileOutputStream fos2 = new FileOutputStream("hamle.txt");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(gamee);
            oos2.flush();//write out any buffered bytes
            oos2.close();
            System.out.println("Game Saved");
        }
        catch(Exception e){
            System.out.println("Can not save data"+e.getClass()+" "+e.getMessage());

        }
    }
    private static void loadGame(){
        try{
            FileInputStream fis1 = new FileInputStream("tahta.txt");
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            gamee = (Game) ois1.readObject();
            ois1.close();
            FileInputStream fis2 = new FileInputStream("hamle.txt");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            gamee = (Game) ois2.readObject();
            ois2.close();

        }
        catch(Exception e){
            System.out.println("Can not save data"+e.getClass()+" "+e.getMessage()); 

        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader in;
        String input;
        String output = "";
        gamee = new Game();
        in = new BufferedReader(new InputStreamReader(System.in));
        //gamee.showIntro();
        do{
            System.out.println("> ");
            input = in.readLine();
            switch (input){
                case "save":
                saveGame();
                break;
                case "load":
                loadGame();
                break;
                default:
                //output = gamee.runCommand(input);
                break;
            }
            System.out.println(output);
        }
        while(!"q".equals(input));

        Connect4_Game c = new Connect4_Game();
        c.frame.setResizable(false);
        c.frame.setTitle("CONNECT 4");
        c.frame.add(c);
        c.frame.pack();
        c.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.frame.setVisible(true);
        c.frame.setLocationRelativeTo(null);
        c.frame.setAlwaysOnTop(true);
        c.start();


    }
}

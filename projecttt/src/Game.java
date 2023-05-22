import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static List<Ball> balls;
    int turn=0;
    public boolean gameOver = false;

    public Game(){
        init();
    }
    public void init(){
        balls = new ArrayList<Ball>();
        gameOver = false;

    }
    public void update(){
        
        if(gameOver){
             if(Mouse.buttonUp(MouseEvent.BUTTON1)){
            init();
        }
        return ;
  }  
  for(int i=0; i < balls.size(); i++)  {
    balls.get(i).update();
  }  
  if(Mouse.buttonDown(MouseEvent.BUTTON1)) {
    if(balls.size() == 0 || balls.get(balls.size() - 1).Inposition){
        int x = (Mouse.getX()/100)*100;
        boolean valid = true;
        for(int i=0; i < balls.size(); i++)  {
            if(balls.get(i).x == x && balls.get(i).y == 0) {
                valid=false;
        }
    }
    if(valid){
        if(turn == 0){
            turn = 1;
            balls.add(new Ball(x, -100, Sprite.ball1));
        }
        else{
            turn = 0;
            balls.add(new Ball(x,-100,Sprite.ball2));
        }

    }

}

}
  checkForGameOver();

}
    private void checkForGameOver(){
        if(balls.size() <= 9) return;
        Ball b = balls.get(balls.size()-1);
        if(!b.Inposition) return;
        int x = (int) b.x;
        int y = (int) b.y;
        //check if it is four in a row
        int xaxis = 1, xplus = 1, xminus = 1;
        int yaxis = 1, yplus = 1, yminus = 1;
        int xyaxis = 1, xyplus = 1, xyminus = 1;
        int yxaxis = 1, yxplus = 1, yxminus = 1;
        boolean strak = true, changed = false;

        while(strak){
            for(int i=0; i < balls.size(); i++){
                Ball ball = balls.get(i);
                if(ball.x == x + xplus * 100 && ball.y == y && samePlayer(ball)){
                    xplus++;
                    xaxis++;
                    changed=true;
            }
            if(ball.x == x + xminus * 100 && ball.y == y && samePlayer(ball)){
                xminus++;
                xaxis++;
                changed=true;
        }
        if(ball.y == y + yplus * 100 && ball.x == x && samePlayer(ball)){
            yplus++;
            yaxis++;
            changed=true;
    }
    if(ball.y == y - yminus * 100 && ball.x == x && samePlayer(ball)){
        yminus++;
        yaxis++;
        changed=true;
}
if(ball.x == x + xyplus * 100 && ball.y == y + xyplus * 100  && samePlayer(ball)){
    xyplus++;
    xyaxis++;
    changed=true;
}
if(ball.x == x - xyminus * 100 && ball.y == y - xyminus * 100  && samePlayer(ball)){
    xyminus++;
    xyaxis++;
    changed=true;
}
if(ball.x == x + yxplus * 100 && ball.y == y - yxplus * 100  && samePlayer(ball)){
    yxplus++;
    yxaxis++;
    changed=true;
}
if(ball.x == x - yxminus * 100 && ball.y == y + yxminus * 100  && samePlayer(ball)){
    yxminus++;
    yxaxis++;
    changed=true;
}

        }
        if(changed) strak = false;
        else changed = false;
    }
    if(xaxis >= 4 || yaxis >=4 || xyaxis >= 4 || yxaxis >= 4) gameOver=true;
    if(balls.size() >= 81) {
        turn = -1;
        gameOver = true;
    }

        
    }
    private boolean samePlayer(Ball ball){

        return false;
    }
    public void render(){
        Render.renderBackground();
        for(int i=0; i < balls.size(); i++)  {
            balls.get(i).render();
        }
        Render.renderSprite(Sprite.overlay, 0, 0);

        for(int i = 0; i < Connect4_Game.pixels.length; i++){
            Connect4_Game.pixels[i] = Render.pixels[i];
        }
        Render.renderSprite(Sprite.ball1, 100, 100);
        Render.renderSprite(Sprite.ball2, 300, 400);
        

    }
    public void renderText(Graphics2D g){
        //if(turn == 0 && ball1.sprite == Sprite.ball2 || turn == 1 && ball1.sprite == Sprite.ball1) return true;}

        if(gameOver){
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 80));
            String sGameOver = "GAME OVER";
            String sWinner ;
            if(turn == 0) sWinner = "Blue wins";
            else if(turn == 1) sWinner = "Pink wins";
            else sWinner = "It is a tie";
            int ig = g.getFontMetrics().stringWidth(sGameOver) / 2 ;
            g.drawString(sGameOver, Connect4_Game.WIDTH/2 * ig, 100);
            g.setFont(new Font("Ariel", 1, 100));

            int it = g.getFontMetrics().stringWidth(sWinner) / 2 ;
            g.drawString(sWinner, Connect4_Game.WIDTH/2 * it, 360);
            
        }

    }

}
public class Ball {

    public double x,y;
    public int width, height;
    public Sprite sprite;
    public double gravity = 3.0;
    private double yval = 0.0;
    private int bounceCounter =0 ;
    public boolean Inposition = false;

    public Ball(double x, double y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.width = sprite.width;
        this.height = sprite.height;
        this.sprite = sprite;


    }
    public void update(){
        if( !Inposition ){
            yval = yval + gravity;
            y += yval;
        }
        if(checkCollision()){
            yval *= -0.25;
            bounceCounter++;
            y = Math.floor(y/100)*100 ;
            if(bounceCounter == 3){
                Inposition = true;
                yval=0;
            }

        }
    }

        private boolean checkCollision(){
            if(y + height >= Connect4_Game.HEIGHT){
                return true;
            }
            for(int i=0; i< Game.balls.size();i++){
                if(this== Game.balls.get(i)) continue;
                if(Game.balls.get(i).x == this.x && this.y + this.height >= Game.balls.get(i).y){
                    return true;
                }

            }
            return false;
        }
    
    public void render(){
        Render.renderSprite(sprite,(int)x , (int)y);

    }
}



public class Position {
    private int x;
    private int y;
    private int maxX;
    private int minX;
    private int maxY;
    private int minY;
    Size size;

    public Position(int x,int y){
        size = new Size();
        this.x = x;
        this.y = y;
        this.maxX = size.getDisplayWidth();
        this.maxY = size.getDisplayHeight();
        this.minX = 0;
        this.minY = 0;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        if (x>= minX && x<=maxX-size.getPlayerObjectWidth()){ //-gameObject width, således at firkanten ikke ryger ud fra display
        this.x = x;
        }else{
            System.out.println("Ude for rammen"); //Kontrol til terminal
            System.out.println("X VÆRDI: " + this.x); //Kontrol til terminal
        }
    }
    public void setY(int y) {
        this.y = y;

    }
}

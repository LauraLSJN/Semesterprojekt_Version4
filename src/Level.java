/*public class Level {


    int currentLevel=0;

    public Level(int level){
        this.currentLevel = level;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }


    public void setup(){
        if(currentLevel == 0){
            new Thread(new GameLoop(new Game(0))).start();
        } else if (currentLevel == 1){
            new Thread(new GameLoop(new Game(1))).start();
        }
    }



}*/

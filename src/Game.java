import java.awt.*;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;


public class Game { //Game klassen - sætter de ting ind som vi skal bruge i vores spil.
    private Display display;
    private List<GameObject> gameObject; //ArrayList af Objekter = GameObject
    private List<ShoppingBasket> shoppingBaskets;
    private List<Tid> tid;
    private Input input; //Input fra brugeren
    Random random = new Random();
    Size size;
    private boolean stopDrop;
    boolean won;
    boolean lost;
    boolean pauseState;
    int currentLevel;

    public Game(int currentLevel) {
        input = new Input();
        size = new Size();
        display = new Display(size.getDisplayWidth(), size.getDisplayHeight(), input);//aendret fra w h Skærmstørrelse 700x500 x: 700, y:500
        this.currentLevel = currentLevel;
        this.pauseState = false;
        this.won = false;
        this.lost = false;
        this.stopDrop = false;

        //Tid
        tid = new ArrayList<>();
        tid.add(new Tid());

        //Shoppingkurven
        shoppingBaskets = new ArrayList<>();

        //Food og Player
        gameObject = new ArrayList<>();

        //Tilføjer Levels
        addLevels();


        //Anvendes til kontrol
        System.out.println("GameObject Størrelse: " + gameObject.size());
        System.out.println(getGameObject());
        display.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    //Funktion som opdateres med framerate
    public void update() {
        gameObject.forEach(gameObject -> gameObject.update());
        detectionOutOfDisplay();
        detection();
        checkStop();
        dropFoodObjects();
        tid.forEach(tid -> tid.update());

    }

    //Funktion der styrer levels
    public void addLevels() {
        if (currentLevel == 1) {
            shoppingBaskets.add(new ShoppingBasket(5));
            gameObject.add(new PlayerObject(new Player(input), 1));
            addFoodObjects();

        } else if (currentLevel == 2) {
            shoppingBaskets.add(new ShoppingBasket(30));
            gameObject.add(new PlayerObject(new Player(input), 2));
            addFoodObjects();

        } else if (currentLevel == 3) {
            shoppingBaskets.add(new ShoppingBasket(50));
            gameObject.add(new PlayerObject(new Player(input), 3));
            System.out.println(getGameObject());
            addFoodObjects();

        }else if(currentLevel == 4){
            shoppingBaskets.add(new ShoppingBasket(100));
            gameObject.add(new PlayerObject(new Player(input), 4));
            addFoodObjects();
        } else if(currentLevel == 5) {
            shoppingBaskets.add(new ShoppingBasket(150));
            gameObject.add(new PlayerObject(new Player(input), 5));
            addFoodObjects();
        }else {
            System.out.println("addLevels: else ");
        }
    }

    //Tilføjer foodObjects til gameObject arraylisten
    public void addFoodObjects() {
        if (currentLevel == 1) {
            gameObject.add(new FoodObjects(1, false, false));
        } else if (currentLevel == 2) {
            gameObject.add(new FoodObjects(2, true, false));
        } else if (currentLevel == 3) {
            gameObject.add(new FoodObjects(3, true, true));
        }else if (currentLevel == 4){
            gameObject.add(new FoodObjects(4, true, true));
        } else if (currentLevel == 5) {
            gameObject.add(new FoodObjects(5, true, true));
          //  gameObject.add(new FoodObjects(3, true, true));
            gameObject.add(new FoodObjects(1, true, true));
        }else {
            System.out.println("addFoodObject: else ");
        }
    }


    //Dropper foodoOjects
    public void dropFoodObjects() {
        int randomTal = random.nextInt(2000); //Random tal
        if (this.stopDrop == false && randomTal <= 25) { //Hvis stopDrop er falsk og randomtol er mindre end eller lig 25, tilføjes nye foodObjects
            addFoodObjects();
        }
    }

    //Funktion der fjerne objekter i arrayListen gameObjects
    public void removeGameObjects() {
        for (int i = 0; i < gameObject.size(); i++) {
            gameObject.remove(i); //Fjerne objekter i gameObject arraylisten
            tid.get(0).stopTid();
        }
    }

    //Tjekker hvorvidt der ikke skal droppes flere foodObjects
    public void checkStop() {
        if (shoppingBaskets.get(0).nowCollectedFood == shoppingBaskets.get(0).maxValue) {
            removeGameObjects();

            if (this.stopDrop == false) {
                setWon(true);
                System.out.println(this.won);
                currentLevel++;
                System.out.println("currentLevel; " + currentLevel);
                display.levelBoks(currentLevel, true);
                display.dispose();
            }
        }

        if (tid.get(0).getMinSecond() == 0 && tid.get(0).getSecond() == 0 && tid.get(0).getMinute() == 0 && this.stopDrop == false) {
            this.stopDrop = true;
            removeGameObjects();
                setLost(true);

            if(isWon() == false && isLost() == true ) {
                display.levelBoks(currentLevel, false);
                display.dispose();

                }
            }
        }


    //Funktion til detection af hvorvidt firkanterne på displayet rammer hinanden
    public void detection() {
        for (int x = 1; x < gameObject.size(); x++) {
            if ((gameObject.get(x).getPosition().getX() >= (gameObject.get(0).getPosition().getX() - 30)) // food x >= player x - 30
                    && (gameObject.get(x).getPosition().getX() <= (gameObject.get(0).getPosition().getX() + size.getPlayerObjectWidth() + 20)) //food x <= player x+ size + 20
                    && ((gameObject.get(x).getPosition().getY() + size.getFoodObjectHeight()) >= gameObject.get(0).getPosition().getY() + 5) // food y + size >= player y +5
                    && ((gameObject.get(x).getPosition().getY() + size.getFoodObjectHeight()) <= (gameObject.get(0).getPosition().getY() + size.getPlayerObjectHeight() + 20)) //food y <= player y + size + 20
                    && ((gameObject.get(x).getPosition().getX() + size.getFoodObjectWidth()) >= (gameObject.get(0).getPosition().getX() - 35)) //food x + size >= player x -35
                    && ((gameObject.get(x).getPosition().getX() + size.getFoodObjectWidth()) <= (gameObject.get(0).getPosition().getX() + size.getFoodObjectWidth() + 60)) //food x + size <= player x + size + 60
            ) {
                //SET
                shoppingBaskets.get(0).setCollectedFood(gameObject.get(x).getPrice().getValuePrice());
                //ADD
                shoppingBaskets.get(0).addCollectedFood(gameObject.get(x).getPrice().getValuePrice());

                //Konsol print, anvendes til kontrol
                System.out.println(gameObject.get(x).getPosition().getX());
                System.out.println(gameObject.toString());
                System.out.println(x);

                gameObject.remove(x); //Fjerner objektet -> Der bliver ramt
                System.out.println(getGameObject()); //Print til konsol -> Se om objektet er fjernet fra arraylist

            }

        }
    }

    //Fjerne gameObjcts objekter der er udenfor display
    public void detectionOutOfDisplay() {
        for (int i = 1; i < gameObject.size(); i++) {
            if (gameObject.get(i).getPosition().getY() >= gameObject.get(0).getPosition().getY() + size.getPlayerObjectHeight()) { //food y >= player y + player height
                gameObject.remove(i);
            }
        }
    }

    public void LevelTekstBoks(Graphics g){
        int tekstBoksWidth = 125; // 500
        int tekstBoksHeight = 50; //100
        int tekstBoksX = 0;
        int tekstBoksY = 110;
        int fontSize = 15;
        Font font = new Font("Monospaced", Font.PLAIN, fontSize);
        g.setFont(font);
        g.setColor(Color.PINK);
        g.fillRect(tekstBoksX,tekstBoksY,tekstBoksWidth,tekstBoksHeight);
        g.setColor(Color.WHITE);
        g.drawString("Level: ",tekstBoksX+5,tekstBoksY+20);
        g.drawString(String.valueOf(currentLevel),tekstBoksX+5+fontSize,tekstBoksY+40);
        //https://www.tabnine.com/code/java/methods/java.awt.Graphics/setFont

    }

    public void render() {
        display.render(this);
    }

    //Getters og Setters
    public List<GameObject> getGameObject() {
        return gameObject;
    }

    public List<ShoppingBasket> getShoppingBaskets() {
        return shoppingBaskets;
    }

    public List<Tid> getTid() {
        return tid;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }
}

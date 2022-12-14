import java.awt.*;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.font.TextAttribute;
import javax.swing.*;


public class Game { //Game klassen - sætter de ting ind som vi skal bruge i vores spil. Det vigtigste her er de to lister
    private Display display;
    private List<GameObject> gameObject; //ArrayList af Objekter = GameObject
    private List<ShoppingBasket> shoppingBaskets;
    private List<Tid> tid;
    private Input input; //Input fra brugeren
    Random random = new Random();
    Size size;
    private boolean stopDrop;

    AttributedString attributedText;
    Font font = new Font("Monospaced", Font.BOLD, 15);
    // Level level;

    boolean won;
    boolean lost;
    boolean pauseState;


    int currentLevel = 0;
    // creating a My HashTable Dictionary
    // Hashtable<Integer, String> gameLevel = new Hashtable<String, String>();

    public void createLevels() {
        //  gameLevel.put(1, "Level 1");
        // gameLevel.put("2", "Level 2");
    }


//boolean test = false;

    public Game(int currentLevel) {
        this.currentLevel = currentLevel;
        this.pauseState = false;
        this.won = false;
        this.lost = false;
        //  this.currentLevel = currentLevel;

        //this.test = false;
        this.stopDrop = false;
        // level = new Level();

        input = new Input();
        size = new Size();
        display = new Display(size.getDisplayWidth(), size.getDisplayHeight(), input);//aendret fra w h Skærmstørrelse 700x500 x: 700, y:500
        //Tid
        tid = new ArrayList<>();
        tid.add(new Tid());

        //Shoppingkurven
        shoppingBaskets = new ArrayList<>();
        //  shoppingBaskets.add(new ShoppingBasket());
        //shoppingBasketsLevel();
        System.out.println("Test");

        //Food og Player
        gameObject = new ArrayList<>();
        //addPlayerLevel();
        // gameObject.add(new PlayerObject(new Player(input),3)); //playerobject skal være index 0 for at detection virker

        // addToLevels();
        addLevels();
        //addFoodObjects();


        //Anvendes til kontrol
        System.out.println("GameObject Størrelse: " + gameObject.size());
        System.out.println(getGameObject());
        display.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }


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


/*
    public void addPlayerLevel(){
        if(currentLevel == 0){
            gameObject.add(new PlayerObject(new Player(input), 1));
        } else if(currentLevel == 1){
            gameObject.add(new PlayerObject(new Player(input), 3));
        } else if (currentLevel == 2){
            gameObject.add(new PlayerObject(new Player(input), 5));
        }else{
            gameObject.add(new PlayerObject(new Player(input), 10));
        }
    }*/

   /* public void shoppingBasketsLevel(){
        if(currentLevel == 0){
            shoppingBaskets.add(new ShoppingBasket(5));
        } else if(currentLevel == 1){
            shoppingBaskets.add(new ShoppingBasket(30));
        } else if (currentLevel == 2){
            shoppingBaskets.add(new ShoppingBasket(50));
        }
    }*/

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
         //   System.exit(0);
        }


        //gameObject.add(new FoodObjects());
        //gameObject.add(new FoodObjects());
    }

    //Dropper foodoOjects
    public void dropFoodObjects() {
        int randomTal = random.nextInt(2000);
        if (this.stopDrop == false && randomTal <= 25) {
            addFoodObjects();
            // addToLevels();
        }
    }

    public void removeGameObjects() {
        for (int i = 0; i < gameObject.size(); i++) {
            gameObject.remove(i); //Fjerne dem der ikke er ramt fra ArrayListe
            //System.out.println(getGameObject());
            //System.out.println("FJERNER: " + getGameObject().get(i));
            // level.setNextLevel(1);
            // removeAllFoodObjects();
            tid.get(0).stopTid();
        }
    }

    public void checkStop() {
        //checkLevel();
        if (shoppingBaskets.get(0).nowCollectedFood == shoppingBaskets.get(0).maxValue) {
            //addFoodObjects(); //Tilføjer nyt objekt til arrayliste hvis shoppingBasket ikke er lig maks
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

        if (tid.get(0).getMinSecond() == 0 && tid.get(0).getSecond() == 0 && tid.get(0).getMinute() == 0 && this.stopDrop == false) { //&& this.stopDrop == false
            this.stopDrop = true;
            removeGameObjects();
                setLost(true);

            if(isWon() == false && isLost() == true ) {
                display.levelBoks(currentLevel, false);
                display.dispose();

                //setTest(true);
            }
            }

           /* if(isWon() == false && isLost() == true ) {
                display.levelBoks(currentLevel, false);
                display.dispose();
            }*/
        }


    //Metode til detection af hvorvidt firkanterne på displayet rammer hinanden
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


                System.out.println(gameObject.get(x).getPosition().getX());
                System.out.println(gameObject.toString());
                System.out.println(x);

                gameObject.remove(x); //Fjerner objektet -> Der bliver ramt
                System.out.println(getGameObject()); //Print til konsol -> Se om objektet er fjernet fra arraylist
                // this.test = true;

            }

        }
    }

    public void detectionOutOfDisplay() {
        for (int i = 1; i < gameObject.size(); i++) {
            if (gameObject.get(i).getPosition().getY() >= gameObject.get(0).getPosition().getY() + size.getPlayerObjectHeight()) { //food y >= player y + player height
                gameObject.remove(i);
            }

        }
    }

    public void update() {
        gameObject.forEach(gameObject -> gameObject.update());
        detectionOutOfDisplay();
        detection();
        checkStop();
        dropFoodObjects();
        //tid.get(0).update();


        tid.forEach(tid -> tid.update()); //Find retur værdi

    }

    public void render() {
        display.render(this);
    }

    public List<GameObject> getGameObject() {
        return gameObject;
    }

    public List<ShoppingBasket> getShoppingBaskets() {
        return shoppingBaskets;
    }

    public List<Tid> getTid() {
        return tid;
    }

    //Price i firkanten
    //https://www.baeldung.com/java-add-text-to-image
    public void setText(Graphics2D graphics, String text, int x, int y) {
        attributedText = new AttributedString(text);
        attributedText.addAttribute(TextAttribute.FONT, font); //Font
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.RED); //Sættes til foreground + farve = hvid
        graphics.drawString(attributedText.getIterator(), x, y); //Placeres i billede -> X og y kordinat er i henhold til image
    }
    
    public void tekstBoks(Graphics g){
        //Center -> GetAscent og getDescent -> Som tage
        //String tekst -> hvor vi kan bruge ovenstående, som tager størrelsen af string ift. font -> Derefter kan vi centrerer det
        //https://www.tabnine.com/code/java/methods/java.awt.Graphics/setFont
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


//Liste/Dictionary -> i Game klasse -> Det første i listen (indeks 0) -> Første settings, som skal gælde for første level
    //Indeks 1 -> Næste level med de værdier som skal sættes
    //Game klasse -> Variabel currentLevel -> Når man taber, level op, og når man vinder level op
    //


}

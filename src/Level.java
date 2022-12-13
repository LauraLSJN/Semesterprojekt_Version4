import java.util.List;

public class Level {


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


    public void checkLevels(List<GameObject> gameObject, List<ShoppingBasket> shoppingBaskets){

            if(currentLevel == 0){
                shoppingBaskets.add(new ShoppingBasket(5));
                gameObject.add(new FoodObjects(1, false));
            } else if(currentLevel == 1){
                shoppingBaskets.add(new ShoppingBasket(100));
                gameObject.add(new FoodObjects(3,true));
            }


    }


}

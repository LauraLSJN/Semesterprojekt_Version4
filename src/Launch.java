public class Launch {

    public static void main(String[] args){
        //https://www.youtube.com/channel/UCow6EoWSXVRu5bgzLM-fBpw/videos
        //Display display = new Display(300,300);
        /*if(((MyFrame) new MyFrame()).isPressed){
            new Thread(new GameLoop(new Game())).start();
        }*/
        //new Thread(new GameLoop(new Game())).start();

        StartGameWindow startgamewindow = new StartGameWindow();//laver nyt objekt og laver intance
        startgamewindow.StartBoks();//Henter startknappen

       // Level level = new Level(0);
        //level.setup();

    }
}

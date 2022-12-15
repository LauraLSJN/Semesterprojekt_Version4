

public class GameLoop implements Runnable {
    private Game game;
    private boolean running;
    private final double UPDATE_RATE = 1.0d / 60.0d;
    private long nextStatTime;
    private int fps, ups;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            if (accumulator >= UPDATE_RATE) {
                while (accumulator >= UPDATE_RATE) {
                    update();
                    accumulator -= UPDATE_RATE;
                }
                renderGameLoop();
            }
            printStats();
        }
    }

    private void printStats() {
        if (System.currentTimeMillis() > nextStatTime) {
            //System.out.println(String.format("FPS: %d, UPS: %d", fps, ups));
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void renderGameLoop() {
        game.renderGame();
        fps++;
    }

    private void update() {
        game.update();
        ups++;
    }
}

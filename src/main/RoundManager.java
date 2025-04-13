package main;

public class RoundManager {
    private GamePanel gp;
    private int currentRound = 1;
    private int maxRounds = 3;
    private int playerWins = 0;
    private int dummyWins = 0;
    private boolean roundEnded = false;
    private long roundEndTime = 0;
    private long roundTransitionDelay = 3000; // 3 seconds between rounds
    private int roundTimeLimit = 60; // 60 seconds per round
    private long roundStartTime = 0;
    private boolean matchEnded = false;
    private String matchWinner = null;
    private int transitionCounter = 0;

    public RoundManager(GamePanel gp) {
        this.gp = gp;
    }

    public void startNewRound() {
        // Reset characters for new round
        gp.player.setDefaultValues();
        gp.player.getPlayerImage(gp.cchoice);

        gp.dummy = new entity.Dummy(gp);

        roundEnded = false;
        roundStartTime = System.currentTimeMillis();
        gp.gameState = gp.playState;
        transitionCounter = 0;

        System.out.println("New Round " + currentRound + " started.");
        System.out.println("Player Health: " + gp.player.health);
        System.out.println("Dummy Health: " + gp.dummy.health);
    }

    public void startMatch() {
        currentRound = 1;
        playerWins = 0;
        dummyWins = 0;
        matchEnded = false;
        matchWinner = null;
        startNewRound();
    }

    public void update() {
        if (gp.gameState == gp.playState) {
            if (!roundEnded) {
                long elapsedTime = (System.currentTimeMillis() - roundStartTime) / 1000;
                System.out.println("Elapsed Time: " + elapsedTime + ", isTimeUp(): " + isTimeUp());

                // Only end round if health is 0 or time is up
                // Note: Death animations are handled in GamePanel's update method
                if (gp.player.health <= 0 || gp.dummy.health <= 0 || isTimeUp()) {
                    endRound();
                    gp.gameState = gp.roundTransitionState;
                }
            }
        } else if (gp.gameState == gp.roundTransitionState) {
            transitionCounter++;
            if (transitionCounter > (gp.fps * (roundTransitionDelay / 1000))) {
                transitionCounter = 0;
                if (currentRound < maxRounds) { // Continue to the next round if not the last
                    currentRound++;
                    startNewRound();
                } else if (currentRound == maxRounds && !matchEnded) { // After the last round
                    matchEnded = true;
                    matchWinner = playerWins > dummyWins ? "player" : "dummy";
                    if (matchWinner.equals("player")) {
                        gp.gameState = gp.winState;
                    } else {
                        gp.gameState = gp.gameOverState;
                    }
                }
            }
        }
    }

    private boolean isTimeUp() {
        return (System.currentTimeMillis() - roundStartTime) / 1000 >= roundTimeLimit;
    }

    private void endRound() {
        roundEnded = true;
        roundEndTime = System.currentTimeMillis();

        // Determine round winner
        if (gp.player.health <= 0) {
            dummyWins++;
        } else if (gp.dummy.health <= 0) {
            playerWins++;
        } else {
            // Time up - determine winner based on remaining health percentage
            float playerHealthPercent = gp.player.health / 100f;
            float dummyHealthPercent = gp.dummy.health / 100f;

            if (playerHealthPercent > dummyHealthPercent) {
                playerWins++;
            } else if (dummyHealthPercent > playerHealthPercent) {
                dummyWins++;
            } else {
                // Draw - no one gets a point
            }
        }
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getDummyWins() {
        return dummyWins;
    }

    public boolean isRoundEnded() {
        return roundEnded;
    }

    public int getRemainingTime() {
        if (isTimeUp()) return 0;
        return roundTimeLimit - (int)((System.currentTimeMillis() - roundStartTime) / 1000);
    }

    public String getMatchWinner() {
        return matchWinner;
    }

    public boolean isMatchEnded() {
        return matchEnded;
    }
}
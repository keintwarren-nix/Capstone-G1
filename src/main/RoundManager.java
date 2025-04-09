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

    public RoundManager(GamePanel gp) {
        this.gp = gp;
    }

    public void startNewRound() {
        // Reset characters for new round
        gp.player.setDefaultValues();
        gp.player.getPlayerImage(gp.cchoice);

        // Create a new dummy opponent
        gp.dummy = new entity.Dummy(gp);

        // Reset round state
        roundEnded = false;
        roundStartTime = System.currentTimeMillis();
        gp.gameState = gp.playState;
    }

    public void startMatch() {
        // Reset match stats
        currentRound = 1;
        playerWins = 0;
        dummyWins = 0;
        matchEnded = false;
        startNewRound();
    }

    public void update() {
        // Check if round has ended
        if (!roundEnded) {
            // Check for round end conditions
            if (gp.player.health <= 0 || gp.dummy.health <= 0 || isTimeUp()) {
                endRound();
            }
        } else if (!matchEnded) {
            // Handle round transition
            if (System.currentTimeMillis() - roundEndTime >= roundTransitionDelay) {
                if (playerWins >= (maxRounds / 2 + 1) || dummyWins >= (maxRounds / 2 + 1)) {
                    // Match has ended - someone has won majority of rounds
                    matchEnded = true;
                    gp.gameState = playerWins > dummyWins ? gp.winState : gp.gameOverState;
                } else {
                    // Start next round
                    currentRound++;
                    startNewRound();
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
                // Alternatively, you could give a point to both or handle draws differently
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
}
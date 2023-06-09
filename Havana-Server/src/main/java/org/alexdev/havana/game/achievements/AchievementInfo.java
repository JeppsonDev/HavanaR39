package org.alexdev.havana.game.achievements;

public class AchievementInfo {
    private int id;
    private String name;
    private int level;
    private int pixelReward;
    private int coinsReward;
    private int progressRequired;

    public AchievementInfo(int id, String name, int level, int pixelReward, int coinsReward, int progressRequired) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.pixelReward = pixelReward;
        this.coinsReward = coinsReward;
        this.progressRequired = progressRequired;
    }

    /**
     * Get the achievement id.
     *
     * @return the achievement id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the achievement name.
     *
     * @return the achievement name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the level of the achievement.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Get the pixel reward when completing achievement.
     *
     * @return the pixel reward
     */
    public int getPixelReward() {
        return pixelReward;
    }

    /**
     * Get the coin reward
     *
     * @return coin reward
     */
    public int getCoinsReward() {
        return coinsReward;
    }

    /**
     * Get the progress required for the achievement.
     *
     * @return the progress required
     */
    public int getProgressRequired() {
        return progressRequired;
    }
}

package main.controlleur.navigation;

public class ChefRobot
{
    private NavigationStrategy strategy;

    public ChefRobot(NavigationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(NavigationStrategy strategy) {
        this.strategy = strategy;
    }

    public NavigationStrategy getStrategy() {
        return this.strategy;
    }

}

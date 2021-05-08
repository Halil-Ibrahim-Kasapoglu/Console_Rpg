package Player;

public class PlayerHealth {

    private int currentHealth;

    public int getCurrentHealth() {
        return currentHealth;
    }

    private int maxHealth;

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public PlayerHealth( int maxHealth){
        setMaxHealth(maxHealth);
    }

    public void applyDamage(int amount){
        if (amount < 0) return;
        currentHealth -= amount;
        if (currentHealth <= 0){
            currentHealth = 0;
        }
    }

    public boolean hasFullHealth(){
        if (currentHealth == maxHealth){
            return true;
        }else return false;
    }

    public void restoreHealthFully(){
        currentHealth = maxHealth;
        System.out.println("Health fully restored");
    }

    public void restoreHealth(int amount){
        if (hasFullHealth()){
            System.out.println("Your health is already maximum");
            return;
        }
        currentHealth += amount;
        if (currentHealth >= maxHealth) {
            System.out.println("Health fully restored");
            currentHealth = maxHealth;
        }
    }

    @Override
    public String toString() {
        return "PlayerHealth{" +
                "currentHealth=" + currentHealth +
                ", maxHealth=" + maxHealth +
                '}';
    }
}

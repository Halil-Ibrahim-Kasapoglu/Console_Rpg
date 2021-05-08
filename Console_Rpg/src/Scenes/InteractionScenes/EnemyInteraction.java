package Scenes.InteractionScenes;

import Entity.Enemy;
import Inventory.EquippedItem;
import Items.Armor;
import Items.Weapon;
import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.RandomManager;
import Manager.SceneManager;
import Manager.UserManager;
import Player.Player;
import Scenes.Scene;

import java.util.ArrayList;
import java.util.Random;

public class EnemyInteraction extends Scene {

    private Enemy enemy;

    private boolean onFight = false;

    public EnemyInteraction(){
        super();
        this.enemy = Enemy.generateRandomEnemy();
    }

    @Override
    protected void InitializeSceneCommands() {
        sceneCommands.add(new KernelCommand("attack", new KernelRunnable() {@Override public void process(){AttackCommand();}}));
        sceneCommands.add(new KernelCommand("ignore", new KernelRunnable() {@Override public void process(){IgnoreCommand();}}));

        super.InitializeSceneCommands();
    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        DisplayEncounterMessage();

    }

    private void DisplayEncounterMessage(){
        System.out.println("You have encountered with an enemy called " + enemy.name);
        System.out.println(enemy);
        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void DisplayFightStatus(){
        sceneCommands = new ArrayList<KernelCommand>();
        if (enemy.isAlive()){
            System.out.println("Player Health: " + UserManager.Master().getActivePlayer().getHealth().getCurrentHealth());
            System.out.println("Enemy Health: " + enemy.getHealth());
            sceneCommands.add(new KernelCommand("hit", new KernelRunnable() {
                @Override
                public void process() {
                    HitCommand();
                }
            }));
            sceneCommands.add(new KernelCommand("simulate", new KernelRunnable() {
                @Override
                public void process() {
                    SimulateCommand();
                }
            }));
            sceneCommands.add(new KernelCommand("leave", new KernelRunnable() {
                @Override
                public void process() {
                    System.out.println("Canimizi zor kurtardik");
                    IgnoreCommand();
                }
            }));
        }else {
            System.out.println("You killed enemy");
            int rewardPrice = RandomManager.Random(1 , 50);
            int rewardXp = RandomManager.Random(1 ,5);
            UserManager.Master().getActivePlayer().IncrementXp(rewardXp);
            UserManager.Master().getActivePlayer().IncrementMoney(rewardPrice);
            sceneCommands.add(new KernelCommand("leave", new KernelRunnable() {
                @Override
                public void process() {
                    IgnoreCommand();
                }
            }));
        }
        Kernel.Master().SetAvailableCommands(sceneCommands);
        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void SimulateCommand(){

        while (enemy.isAlive() && UserManager.Master().getActivePlayer().isAlive()){
            HitCommand();
        }
    }

    private void HitCommand(){

        Player player = UserManager.Master().getActivePlayer();
        Weapon playerWeapon = (Weapon) player.getInventory().getEquippedItem(EquippedItem.Weapon);
        Armor playerArmor = (Armor) player.getInventory().getEquippedItem(EquippedItem.Armor);

        double playerDamageValue = playerWeapon == null ? 0 : playerWeapon.getDamage();
        double playerArmorValue = playerArmor == null ? 0 : playerArmor.getArmor();

        double enemyDamage = enemy.getDamage();
        double enemyDamageAffected = enemyDamage - playerArmorValue;
        enemyDamageAffected = Math.max(0 , enemyDamageAffected);

        System.out.println("Player hit : " + playerDamageValue);
        System.out.println("Enemy hit : " + enemyDamageAffected);

        enemy.getDamage(playerDamageValue);
        player.GotHit((int) enemyDamageAffected , enemy);

        if (player.isAlive()) {
            DisplayFightStatus();
        }

    }

    private void IgnoreCommand(){
        SceneManager.Master().popScene();
    }
    private void AttackCommand(){
        onFight = true;
        DisplayFightStatus();
        //System.out.println("not implemented");
    }

}
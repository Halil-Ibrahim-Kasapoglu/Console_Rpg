package Scenes;

import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.RandomManager;
import Manager.SceneManager;
import Manager.UserManager;
import Scenes.InteractionScenes.CollectableInteraction;
import Scenes.InteractionScenes.EnemyInteraction;
import Scenes.InteractionScenes.ItemInteraction;

import java.util.Random;

public class TravelScene extends Scene{

    @Override
    protected void InitializeSceneCommands() {
        sceneCommands.add(new KernelCommand("move", new KernelRunnable() {@Override public void process(String[] params){MoveCommand();}}));
        //sceneCommands.add(new KernelCommand("xp5", new KernelRunnable() {@Override public void process(){UserManager.Master().getActivePlayer().IncrementXp(5);}}));
        //sceneCommands.add(new KernelCommand("xp20", new KernelRunnable() {@Override public void process(){UserManager.Master().getActivePlayer().IncrementXp(20);}}));
        sceneCommands.add(new KernelCommand("home", new KernelRunnable() {@Override public void process(String[] params){HomeCommand();}}));
        super.InitializeSceneCommands();

    }
    @Override
    public void OnLoad() {
        super.OnLoad();

        System.out.println("Don't be shy. Take a step");
        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void MoveCommand(){

        int event = RandomManager.Random(0,3);

        if (event == 0){
            String[] walkingMassages = new String[]{"Yurudum" , "Sadece Yurudum" , "Dere tepe duz gittim" , "Firtina oncesi sessizlik bu" , "Ayaklarima kara sular indi"};
            System.out.println(walkingMassages[RandomManager.Random(0 , walkingMassages.length - 1)]);
        }else if (event == 1){
            SceneManager.Master().pushScene(new ItemInteraction());
        }else if (event == 2){
            SceneManager.Master().pushScene(new EnemyInteraction());
        }else {
            SceneManager.Master().pushScene(new CollectableInteraction());
//            int moneyCollected = 0;
//            String[] noPriceMaterials = new String[]{"gazoz kapagi" , "boncuk" , "degersiz metal", "tas" , "nfs rivals cd si" , "windows 10" , "silgi" , "kapi kolu"};
//            String[] lowPriceMaterials = new String[]{"bakir tel" , "call of duty mw3 cd si" , "kolye" , "deri parcasi" , "yarisi yenmis dardanel ton" , "premium minecraft hesabi" , "logitech mouse"};
//            String[] highPriceMaterials = new String[]{"faber castel 2220" , "macbook pro" , "bir bardak su" , "bim poseti" , "pes 2013 cd si", "bir siir kitabi" , "pentagon sifresi" , "a101 den alinmis kulaklik"};
//            String[] legendaryPriceMaterials = new String[]{"nokia 3310" , "gun mayhem 4" , "pingsiz internet" , "orme patik" , "dobi misket" , "ubuntu"};
//            int itemTier = RandomManager.Random(1 , 20);
//            if (itemTier <= 9) {
//                moneyCollected = 0;
//                System.out.println("Yerde trash item: " + noPriceMaterials[RandomManager.Random(0 , noPriceMaterials.length-1)] + " buldun.");
//                System.out.println("Degersiz bir madde oldugu icin sinirlenip firlattin.");
//                System.out.println(moneyCollected + " money collected");
//            }else if (itemTier <= 15){
//                moneyCollected = RandomManager.Random(1,5)*itemTier;
//                System.out.println("Yerde common item: " + lowPriceMaterials[RandomManager.Random(0 , lowPriceMaterials.length-1)] + " buldun.");
//                System.out.println("Kisa gunun kari allah bereket versin.");
//                System.out.println(moneyCollected + " money collected");
//            }else if (itemTier <= 19){
//                moneyCollected = RandomManager.Random(10,30)*itemTier;
//                System.out.println("Yerde rare item: " + highPriceMaterials[RandomManager.Random(0 , highPriceMaterials.length-1)] + " buldun.");
//                System.out.println("Aman allahim boylesi degerli bi maddenin yerde isi de ne.");
//                System.out.println(moneyCollected + " money collected");
//            }else {
//                moneyCollected = RandomManager.Random(50,150)*itemTier;
//                System.out.println("Yerde legendary item: " + legendaryPriceMaterials[RandomManager.Random(0 , legendaryPriceMaterials.length-1)] + " buldun.");
//                System.out.println("Masallah akiyor bu aksam");
//                System.out.println(moneyCollected + " money collected");
//            }
//            UserManager.Master().getActivePlayer().IncrementMoney(moneyCollected);

        }
    }
    private void HomeCommand(){
        SceneManager.Master().popScene();
    }
}

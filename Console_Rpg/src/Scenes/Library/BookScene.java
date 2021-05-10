package Scenes.Library;

import Manager.FileManager;
import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;
import Scenes.Scene;
import UI.MultipageView;
import Utility.UtilityHelper;

import java.util.ArrayList;

public class BookScene extends Scene {

    private String bookPath;
    private MultipageView itemView;


    public BookScene(String bookPath){
        this.bookPath = bookPath;
        itemView = new MultipageView(5, getAvailableVolumes());
    }

    @Override
    protected void InitializeSceneCommands() {

        sceneCommands.add(new KernelCommand("read", new KernelRunnable() {
            @Override
            public void process(String[] params) {
                ReadCommand(params);
            }
        }, new String[]{"row-id"}));
        sceneCommands.add(new KernelCommand("back", new KernelRunnable() {@Override public void process(String[] params){BackCommand();}}));

        super.InitializeSceneCommands();
    }

    private ArrayList<String> getAvailableVolumes(){
        System.out.println(bookPath);
        return FileManager.ReadFileAsArray("data/library/" + bookPath + "/volumes.txt");
    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        System.out.println("Select a volume and start reading");
        itemView.setRows(getAvailableVolumes());
        LoadContent(itemView.getCurrentPage());

    }

    private void LoadContent(int page){
        if (!itemView.loadPage(page)) return;

        Kernel.Master().DisplayAvailableCommands();
        System.out.println("=======================");
    }

    private void ReadCommand(String[] params){
        int row;
        try{
            row = Integer.parseInt(params[0]);
        }catch (NumberFormatException e){
            System.out.println("row param must be integer");
            return;
        }
        if (row < 1 || row > itemView.getRowCnt()){
            System.out.println("no such book in row: " + row);
            return;
        }

        String volumeContent = FileManager.ReadAll("data/library/" + bookPath + "/" + "volume_" + row + ".txt");
        System.out.println(volumeContent );
    }

    private void BackCommand(){
        SceneManager.Master().popScene();
    }

}

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
    }

    @Override
    protected void LoadSceneCommands() {
        super.LoadSceneCommands();
        sceneCommands.add(new KernelCommand("read", new KernelRunnable() {
            @Override
            public void process(String[] params) {
                ReadCommand(params);
            }
        }, new String[]{"row-id"}));
        sceneCommands.add(new KernelCommand("back", new KernelRunnable() {@Override public void process(String[] params){DismissScene();}}));
    }

    @Override
    public void OnSceneCreated() {
        super.OnSceneCreated();
        itemView = new MultipageView(5, getAvailableVolumes());
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();
        System.out.println("Select a volume and start reading");
        itemView.setRows(getAvailableVolumes());
        itemView.loadPage(itemView.getCurrentPage());
    }

    private ArrayList<String> getAvailableVolumes(){
        System.out.println(bookPath);
        return FileManager.ReadFileAsArray("data/library/" + bookPath + "/volumes.txt");
    }

    private void ReadCommand(String[] params){

        if (!UtilityHelper.isNumeric(params[0])){
            System.out.println("row param must be integer");
            return;
        }
        int row = Integer.parseInt(params[0]);
        if (row < 1 || row > itemView.getRowCnt()){
            System.out.println("no such book in row: " + row);
            return;
        }

        String volumeContent = FileManager.ReadAll("data/library/" + bookPath + "/" + "volume_" + row + ".txt");
        System.out.println(volumeContent);
    }

}

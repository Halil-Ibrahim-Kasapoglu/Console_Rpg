package Scenes.Library;

import Manager.FileManager;
import Manager.Kernel.Kernel;
import Manager.Kernel.KernelCommand;
import Manager.Kernel.KernelRunnable;
import Manager.SceneManager;
import Scenes.InspectionScenes.ItemInspectScene;
import Scenes.Scene;
import UI.MultipageView;
import Utility.UtilityHelper;

import java.util.ArrayList;

public class LibraryScene extends Scene {

    private MultipageView itemView;

    @Override
    public void OnSceneCreated() {
        super.OnSceneCreated();
        itemView = new MultipageView(5, getAvailableBooks());
    }

    @Override
    protected void OnSceneDraw() {
        super.OnSceneDraw();
        System.out.println("Welcome to the source of unlimited wisdom in Console Rpg");
        itemView.setRows(getAvailableBooks());
        itemView.loadPage(itemView.getCurrentPage());
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

    private ArrayList<String> getAvailableBooks(){
        return FileManager.ReadFileAsArray("data/library/books.txt");
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

        String bookPath = UtilityHelper.CompressedString(getAvailableBooks().get(row - 1));
        SceneManager.Master().pushScene(new BookScene(bookPath));
    }
}

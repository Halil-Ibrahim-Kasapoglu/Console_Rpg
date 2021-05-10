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

    public LibraryScene(){
        itemView = new MultipageView(5, getAvailableBooks());
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

    private ArrayList<String> getAvailableBooks(){
        return FileManager.ReadFileAsArray("data/library/books.txt");
    }

    @Override
    public void OnLoad() {
        super.OnLoad();

        System.out.println("Welcome to the source of unlimited wisdom in Console Rpg");
        itemView.setRows(getAvailableBooks());
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

        String bookPath = UtilityHelper.CompressedString(getAvailableBooks().get(row - 1));
        SceneManager.Master().pushScene(new BookScene(bookPath));
    }

    private void BackCommand(){
        SceneManager.Master().popScene();
    }

}

package Entities;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Linker implements GUI_Linker {
    /*
    Dhmioyrgia link gia label h gia button
     */

    @Override
    public void CreateLink(Label label) {
        String Identifier = label.getId();
        GuiLabelLinks.put(Identifier,label);
    }

    @Override
    public void CreateLink(Button button) {
        String Identifier = button.getId();
        GuiButtonLinks.put(Identifier,button);
    }


/*
Me8odoi pou epistrefoun to link gia to label h button
 */
    public Label GetLabelLink(String LabelName) {
        return this.GuiLabelLinks.get(LabelName);
    }


    public Button GetButtonLink(String ButtonName) {
        return this.GuiButtonLinks.get(ButtonName);
    }

    /*
    Example
    ! Shmantiko ! Prokeimenoy na doylepsei prepei na kseroume to Id toy label pou 8eloume na paroume
    Logo fxml to id poy orizete sthn class kai sto Fxml to kseroume
    Class1 implements Gui_Linker
    {
        @FXML
        Label Test;--> Id = Test
        Linker linker = new Linker();
        linker.CreateLink(Test);
    }

    Class2 implements Gui_Linker
    {
        Label MyTest;
        Linker MyLinker = new Linker();
        this.MyTest = MyLinker.GetLabelLink(Test);
    }
     */

}

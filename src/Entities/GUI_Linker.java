package Entities;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public interface GUI_Linker {
    /*
    Ta maps auta kratane links
    gia na yparxei epikoiwnia sta labels
    metaksy pollaplwn classewn

     */
    Map<String, Label> GuiLabelLinks = new HashMap<>();

    Map<String, Button> GuiButtonLinks = new HashMap<>();

    /*
    Oi abstract me8odoi autoi ylopoiounte sthn Linker.java
     */
    void CreateLink(Label label);

    void CreateLink(Button button);

}

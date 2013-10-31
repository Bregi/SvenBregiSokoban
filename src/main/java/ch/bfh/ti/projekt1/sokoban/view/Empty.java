package ch.bfh.ti.projekt1.sokoban.view;

public class Empty extends Element {

    public Empty() {
        initialize();
    }

    public void initialize() {
        addImage(EMPTY_PATH);
    }
}

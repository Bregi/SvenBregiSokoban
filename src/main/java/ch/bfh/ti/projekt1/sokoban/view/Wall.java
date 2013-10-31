package ch.bfh.ti.projekt1.sokoban.view;

public class Wall extends Element {

    public Wall() {
        initialize();
    }

    public void initialize() {
        addImage(WALL_PATH);
    }
}

package ch.bfh.ti.projekt1.sokoban.model;

public class Empty extends Element {

    public Empty() {
        initialize();
    }

    public void initialize() {
        addImage("src/main/resources/ch/bfh/ti/projekt1/sokoban/img/empty.jpg");
    }
}

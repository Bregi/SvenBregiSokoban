package ch.bfh.ti.projekt1.sokoban.view.element;

public class Finish extends Element {

    public Finish() {
        addImage(GOAL_PATH);
    }

    public void setCompleted() {
        addImage(FINISH_PATH);
    }

    public void setLeft() {
        addImage(GOAL_PATH);
    }

}

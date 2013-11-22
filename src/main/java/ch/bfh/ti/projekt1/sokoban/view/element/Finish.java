package ch.bfh.ti.projekt1.sokoban.view.element;

public class Finish extends Element {

    public Finish() {
        addImage(FINISH_PATH);
    }
    public void setCompleted(){
    	addImage(GOAL_PATH);
    }
    public void setLeft(){
    	addImage(FINISH_PATH);
    }

}

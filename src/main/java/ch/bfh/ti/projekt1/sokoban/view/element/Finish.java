package ch.bfh.ti.projekt1.sokoban.view.element;

/**
 * The model of the finish element
 * 
 * @author Marco Berger 
 * @since 01/09/2013
 */
public class Finish extends Element {

    public Finish() {
        addImage(GOAL_PATH);
    }

    /**
     * Changes the image of the element if the player has finished this goal
     */
    public void setCompleted() {
        addImage(FINISH_PATH);
    }

    /**
     * Changes the image of the element if the player has left the field
     */
    public void setLeft() {
        addImage(GOAL_PATH);
    }

}

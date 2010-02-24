package dijordan;


/******************************************
 * Controller for the Container widget
 * @author: Diane Jordan (dijordan@wpi.edu)
 * For A1.implementation in CS3733 B01
 */
public class PyramidOthersController extends java.awt.event.MouseAdapter {
  /* a pointer to the game */
  protected PyramidGame theGame;

  /*************
   * Constructor
   */
  public PyramidOthersController(PyramidGame pGame) {
    super();
    theGame = pGame;
  }
  /********************************************************
   * If there is a mouse pressed event, deselect everything
   */
  public void mousePressed(java.awt.event.MouseEvent me) {
	//theGame.selectNone();
	// not sure how best to deal with this...
	theGame.refreshWidgets();
  }  
}

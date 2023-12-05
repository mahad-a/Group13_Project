package org.example.uno.GUI;

/**
 * The UnoGameModelView interface represents the objects that are used as views in the UNO game architecture.
 * Classes that are implementing this interface are expected to provide an implementation for updating the view of the UnoGameModel.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.0
 */
public interface UnoGameModelView {

    /**
     * Updates the view based on an UnoEvent.
     *
     * @param e The UnoEvent object that needs to be viewed.
     */
    void updateView(UnoEvent e);

    void restartGame();
    void undoMove();
    void redoMove();
    void loadGame();
}

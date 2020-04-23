
package io.github.longlinht.library.ui.kp;

/**
 * Created by Tao He on 18-7-14.
 * hetaoof@gmail.com
 */

public interface IPanelConflictLayout {
    boolean isKeyboardShowing();

    /**
     * @return The real status of Visible or not
     */
    boolean isVisible();

    /**
     * Keyboard->Panel
     *
     */
    void handleShow();

    /**
     * Panel->Keyboard
     *
     */
    void handleHide();

    /**
     * @param isIgnoreRecommendHeight Ignore guaranteeing the panel height equal to the keyboard
     *                                height.
     * @attr ref cn.dreamtobe.kpswitch.R.styleable#KPSwitchPanelLayout_ignore_recommend_height
     */
    @SuppressWarnings("JavaDoc")
    void setIgnoreRecommendHeight(boolean isIgnoreRecommendHeight);
}

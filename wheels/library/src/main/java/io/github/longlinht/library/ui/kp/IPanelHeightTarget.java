
package io.github.longlinht.library.ui.kp;

/**
 * Created by Tao He on 18-7-14.
 * hetaoof@gmail.com
 */

public interface IPanelHeightTarget {

    /**
     * for handle the panel's height, will be equal to the keyboard height which had saved last time.
     */
    void refreshHeight(final int panelHeight);

    /**
     * @return get the height of target-view.
     */
    int getHeight();

    /**
     * Be invoked by onGlobalLayoutListener call-back.
     *
     * @param showing whether the keyboard is showing or not.
     */
    void onKeyboardShowing(boolean showing);
}

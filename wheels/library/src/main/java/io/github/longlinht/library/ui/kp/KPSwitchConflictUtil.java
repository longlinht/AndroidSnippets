package io.github.longlinht.library.ui.kp;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import io.github.longlinht.library.utils.ScreenUtils;


public class KPSwitchConflictUtil {

    public static void attach(final View panelLayout,
                              /** Nullable **/final View switchPanelKeyboardBtn,
                              /** Nullable **/final View focusView) {
        attach(panelLayout, switchPanelKeyboardBtn, focusView,null, null);
    }

    /**
     * Attach the action of {@code switchPanelKeyboardBtn} and the {@code focusView} to
     * non-layout-conflict.
     * <p/>
     * You do not have to use this method to attach non-layout-conflict, in other words, you can
     * {@link #showKeyboard(View, View)}、{@link #hidePanelAndKeyboard(View,EditText)}, and in the case of don't
     * invoke this method to attach, and if your activity with the fullscreen-theme, please ensure your
     * panel layout is {@link View#INVISIBLE} before the keyboard is going to show.
     *
     * @param panelLayout            the layout of panel.
     * @param switchPanelKeyboardBtn the view will be used to trigger switching between the panel and
     *                               the keyboard.
     * @param focusView              the view will be focused or lose the focus.
     * @param switchClickListener    the click listener is used to listening the click event for
     *                               {@code switchPanelKeyboardBtn}.
     */
    public static void attach(final View panelLayout,
                              /** Nullable **/final View switchPanelKeyboardBtn,
                              /** Nullable **/final View switchMore,
                              /** Nullable **/final View focusView,
                              /** Nullable **/final SwitchClickListener switchClickListener) {
        final Activity activity = (Activity) panelLayout.getContext();
        if (switchPanelKeyboardBtn != null) {
            switchPanelKeyboardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean switchToPanel = switchPanelAndKeyboard(panelLayout);
                    if (switchClickListener != null) {
                        switchClickListener.onClickSwitch(switchToPanel, v.getId());
                    }
                    switchShow(panelLayout, focusView, switchToPanel);
                }
            });
        }
        if (switchMore != null) {
            switchMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean switchToPanel = switchPanelAndKeyboard(panelLayout);
                    if (switchClickListener != null) {
                        switchClickListener.onClickSwitch(switchToPanel, v.getId());
                    }
                    onlyShowMore(panelLayout, focusView, switchToPanel);
                }
            });
        }

        if (isHandleByPlaceholder(activity)) {
            focusView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        /**
                         * Show the fake empty keyboard-same-height panel to fix the conflict when
                         * keyboard going to show.
                         * @see KPSwitchConflictUtil#showKeyboard(View, View)
                         */
                        panelLayout.setVisibility(View.INVISIBLE);
                    }
                    return false;
                }
            });
        }
    }

    /**
     * To show the panel(hide the keyboard automatically if the keyboard is showing) with
     * non-layout-conflict.
     *
     * @param panelLayout the layout of panel.
     */
    public static void showPanel(final View panelLayout,final View focusView) {
        final Activity activity = (Activity) panelLayout.getContext();
        KeyboardUtil.closeKeybord((EditText) focusView,activity);
        panelLayout.setVisibility(View.VISIBLE);
        if (activity.getCurrentFocus() != null) {
//            KeyboardUtil.hideKeyboard(activity.getCurrentFocus());
        }
    }

    /**
     * To show the keyboard(hide the panel automatically if the panel is showing) with
     * non-layout-conflict.
     *
     * @param panelLayout the layout of panel.
     * @param focusView   the view will be focused.
     */
    public static void showKeyboard(final View panelLayout, final View focusView) {
        final Activity activity = (Activity) panelLayout.getContext();

//        KeyboardUtil.showKeyboard(focusView);
        KeyboardUtil.openKeybord((EditText) focusView,activity);
        if (isHandleByPlaceholder(activity)) {
            panelLayout.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * If the keyboard is showing, then going to show the {@code panelLayout},
     * and hide the keyboard with non-layout-conflict.
     * <p/>
     * If the panel is showing, then going to show the keyboard,
     * and hide the {@code panelLayout} with non-layout-conflict.
     * <p/>
     * If the panel and the keyboard are both hiding. then going to show the {@code panelLayout}
     * with non-layout-conflict.
     *
     * @param panelLayout the layout of panel.
     * @return If true, switch to showing {@code panelLayout}; If false, switch to showing Keyboard.
     */
    public static boolean switchPanelAndKeyboard(final View panelLayout) {
        boolean switchToPanel = panelLayout.getVisibility() != View.VISIBLE;
        return switchToPanel;
    }

    private static void switchShow(View panelLayout, View focusView, boolean switchToPanel) {
        if (!switchToPanel) {
            showKeyboard(panelLayout, focusView);
        } else {
            showPanel(panelLayout, focusView);
        }
    }

    private static void onlyShowMore(View panelLayout, final View focusView, boolean switchToPanel) {
        if (switchToPanel) {
            showPanel(panelLayout, focusView);
        }
    }

    /**
     * Hide the panel and the keyboard.
     *
     * @param panelLayout the layout of panel.
     */
    public static void hidePanelAndKeyboard(final View panelLayout,EditText et) {
        final Activity activity = (Activity) panelLayout.getContext();

        final View focusView = activity.getCurrentFocus();
        KeyboardUtil.closeKeybord(et, activity);
        if (focusView != null) {
//            KeyboardUtil.hideKeyboard(activity.getCurrentFocus());
            focusView.clearFocus();
        }

        panelLayout.setVisibility(View.GONE);
    }

    /**
     * This listener is used to listening the click event for a view which is received the click event
     * to switch between Panel and Keyboard.
     */
    public interface SwitchClickListener {
        /**
         * @param switchToPanel If true, switch to showing Panel; If false, switch to showing Keyboard.
         */
        void onClickSwitch(boolean switchToPanel, int resId);
    }

    /**
     * @param isFullScreen        Whether in fullscreen theme.
     * @param isTranslucentStatus Whether in translucent status theme.
     * @param isFitsSystem        Whether the root view(the child of the content view) is in
     *                            {@code getFitSystemWindow()} equal true.
     * @return Whether handle the conflict by show panel placeholder, otherwise, handle by delay the
     * visible or gone of panel.
     */
    public static boolean isHandleByPlaceholder(boolean isFullScreen, boolean isTranslucentStatus, boolean isFitsSystem) {
        return isFullScreen || (isTranslucentStatus && !isFitsSystem);
    }

    static boolean isHandleByPlaceholder(final Activity activity) {
        return isHandleByPlaceholder(ScreenUtils.isFullScreen(activity), ScreenUtils.isTranslucentStatus(activity), ScreenUtils.isFitsSystemWindows(activity));
    }

}

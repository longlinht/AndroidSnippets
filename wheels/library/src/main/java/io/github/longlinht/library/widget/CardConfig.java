package io.github.longlinht.library.widget;

/**
 * Created by Tao He on 18-4-29.
 * hetaoof@gmail.com
 */

public final class CardConfig {
    /**
     * 显示可见的卡片数量
     */
    public static final int DEFAULT_SHOW_ITEM = 1;
    /**
     * 默认缩放的比例
     */
    public static final float DEFAULT_SCALE = 0.1f;
    /**
     * 卡片Y轴偏移量时按照14等分计算
     */
    public static final int DEFAULT_TRANSLATE_Y = 14;
    /**
     * 卡片滑动时默认倾斜的角度
     */
    public static final float DEFAULT_ROTATE_DEGREE = 15f;
    /**
     * 卡片滑动时不偏左也不偏右
     */
    public static final int SWIPING_NONE = 1;
    /**
     * 卡片向上滑动时
     */
    public static final int SWIPING_UP = 1 << 2;
    /**
     * 卡片向下滑动时
     */
    public static final int SWIPING_DOWN = 1 << 3;
    /**
     * 卡片从上滑出
     */
    public static final int SWIPED_UP = 1;
    /**
     * 卡片从下滑出
     */
    public static final int SWIPED_DOWN = 1 << 2;

}

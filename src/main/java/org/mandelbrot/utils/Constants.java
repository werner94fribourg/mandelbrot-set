package org.mandelbrot.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Store of all global constants used in the application. The class is final to avoid heritage.
 */
public final class Constants {
    /**
     * The width of the mandelbrot set window.
     */
    public static final int WIDTH = 1254;
    /**
     * The height of the mandelbrot set window.
     */
    public static final int HEIGHT = 880;

    /**
     * The initial number of performed steps to display the mandelbrot set.
     */
    public static final int INITIAL_NB_STEPS = 1024;

    /**
     * The initial number of available threads to compute the set.
     */
    public static final int MAX_NB_CORES = Runtime.getRuntime().availableProcessors();

    /**
     * The initial zoom factor.
     */
    public static final int INITIAL_ZOOM_SCALE = 375;

    /**
     * The html wrapper of the application's status.
     */
    public static final String STATUS_WRAPPER = "<html><div style='font-size: 16px; padding: 10px;'>%s</div></html>";

    /**
     * Instantiation of the executor, that will take care of executing mandelbrot values tasks asynchronously.
     * I used a cached thread pool in order to create new threads as needed when performing tasks or reuse existing ones when they are available.
     */
    public static final Executor EXECUTOR = Executors.newCachedThreadPool();

    /**
     * The list of display colors in the mandelbrot set when choosing the gray palette.
     */
    private static final int[] GRAY_PALETTE = {
            0x000000, 0x040404, 0x080808, 0x0C0C0C,
            0x101010, 0x141414, 0x181818, 0x1C1C1C,
            0x202020, 0x242424, 0x282828, 0x2C2C2C,
            0x303030, 0x343434, 0x383838, 0x3C3C3C,
            0x404040, 0x444444, 0x484848, 0x4C4C4C,
            0x505050, 0x545454, 0x585858, 0x5C5C5C,
            0x606060, 0x646464, 0x686868, 0x6C6C6C,
            0x707070, 0x747474, 0x787878, 0x7C7C7C,
            0x808080, 0x848484, 0x888888, 0x8C8C8C,
            0x909090, 0x949494, 0x989898, 0x9C9C9C,
            0xA0A0A0, 0xA4A4A4, 0xA8A8A8, 0xACACAC,
            0xB0B0B0, 0xB4B4B4, 0xB8B8B8, 0xBCBCBC,
            0xC0C0C0, 0xC4C4C4, 0xC8C8C8, 0xCCCCCC,
            0xD0D0D0, 0xD4D4D4, 0xD8D8D8, 0xDCDCDC,
            0xE0E0E0, 0xE4E4E4, 0xE8E8E8, 0xECECEC,
            0xF0F0F0, 0xF4F4F4, 0xF8F8F8, 0xFCFCFC
    };

    /**
     * The list of display colors in the mandelbrot set when choosing the blue palette.
     */
    private static final int[] BLUE_PALETTE = {
            0x000033, 0x000038, 0x00003D, 0x000042,
            0x000047, 0x00004C, 0x000051, 0x000056,
            0x00005B, 0x000060, 0x000065, 0x00006A,
            0x00006F, 0x000074, 0x000079, 0x00007E,
            0x000083, 0x000088, 0x00008D, 0x000092,
            0x000097, 0x00009C, 0x0000A1, 0x0000A6,
            0x0000AB, 0x0000B0, 0x0000B5, 0x0000BA,
            0x0000BF, 0x0000C4, 0x0000C9, 0x0000CE,
            0x0000D3, 0x0000D8, 0x0000DD, 0x0000E2,
            0x0000E7, 0x0000EC, 0x0000F1, 0x0000F6,
            0x0000FB, 0x0000FF, 0x0000FF, 0x0000F5,
            0x0000EB, 0x0000E1, 0x0000D7, 0x0000CD,
            0x0000C3, 0x0000B9, 0x0000AF, 0x0000A5,
            0x00009B, 0x000091, 0x000087, 0x00007D,
            0x000073, 0x000069, 0x00005F, 0x000055,
            0x00004B, 0x000041, 0x000037, 0x00002D,
    };

    /**
     * The list of display colors in the mandelbrot set when choosing the red palette.
     */
    private static final int[] RED_PALETTE = {
            0x000000, 0x040000, 0x080000, 0x0C0000,
            0x100000, 0x140000, 0x180000, 0x1C0000,
            0x200000, 0x240000, 0x280000, 0x2C0000,
            0x300000, 0x340000, 0x380000, 0x3C0000,
            0x400000, 0x440000, 0x480000, 0x4C0000,
            0x500000, 0x540000, 0x580000, 0x5C0000,
            0x600000, 0x640000, 0x680000, 0x6C0000,
            0x700000, 0x740000, 0x780000, 0x7C0000,
            0x800000, 0x840000, 0x880000, 0x8C0000,
            0x900000, 0x940000, 0x980000, 0x9C0000,
            0xA00000, 0xA40000, 0xA80000, 0xAC0000,
            0xB00000, 0xB40000, 0xB80000, 0xBC0000,
            0xC00000, 0xC40000, 0xC80000, 0xCC0000,
            0xD00000, 0xD40000, 0xD80000, 0xDC0000,
            0xE00000, 0xE40000, 0xE80000, 0xEC0000,
            0xF00000, 0xF40000, 0xF80000, 0xFC0000
    };

    /**
     * The list of display colors in the mandelbrot set when choosing the green palette.
     */
    private static final int[] GREEN_PALETTE = {
            0x000000, 0x000400, 0x000800, 0x000C00,
            0x001000, 0x001400, 0x001800, 0x001C00,
            0x002000, 0x002400, 0x002800, 0x002C00,
            0x003000, 0x003400, 0x003800, 0x003C00,
            0x004000, 0x004400, 0x004800, 0x004C00,
            0x005000, 0x005400, 0x005800, 0x005C00,
            0x006000, 0x006400, 0x006800, 0x006C00,
            0x007000, 0x007400, 0x007800, 0x007C00,
            0x008000, 0x008400, 0x008800, 0x008C00,
            0x009000, 0x009400, 0x009800, 0x009C00,
            0x00A000, 0x00A400, 0x00A800, 0x00AC00,
            0x00B000, 0x00B400, 0x00B800, 0x00BC00,
            0x00C000, 0x00C400, 0x00C800, 0x00CC00,
            0x00D000, 0x00D400, 0x00D800, 0x00DC00,
            0x00E000, 0x00E400, 0x00E800, 0x00EC00,
            0x00F000, 0x00F400, 0x00F800, 0x00FC00
    };

    /**
     * List of all available color palettes.
     */
    public static final int[][] PALETTES = {GRAY_PALETTE, BLUE_PALETTE, RED_PALETTE, GREEN_PALETTE};

    /**
     * Private constructor for the Constants class to avoid instantiation.
     */
    private Constants(){
        throw new AssertionError("Non-instantiable class.");
    };
}

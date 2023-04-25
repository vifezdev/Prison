package gg.convict.prison.crate.util;

public class DyeUtil {

    public static byte getDyeColor(String colorCode) {
        colorCode = colorCode.toLowerCase()
                .replaceAll("&", "")
                .replaceAll("&l", "");

        switch (colorCode) {
            case "0":
            case "black":
                return 0;
            case "6":
            case "orange":
                return 14;
            case "d":
            case "pink":
            case "magenta":
                return 13;
            case "3":
            case "b":
            case "aqua":
                return 12;
            case "e":
            case "yellow":
                return 11;
            case "a":
            case "lime":
                return 10;
            case "7":
            case "gray":
                return 8;
            case "8":
            case "dark_gray":
                return 7;
            case "5":
            case "purple":
                return 5;
            case "9":
            case "dark_blue":
                return 4;
            case "2":
            case "dark_green":
                return 2;
            case "c":
            case "4":
            case "red":
            case "dark_red":
                return 1;
            default:
                return 15;
        }
    }
}

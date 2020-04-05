package com.jmv74211.easybuy.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tools {

  // -----------------------------------------------------------------------------------------------

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  // -----------------------------------------------------------------------------------------------

  public static int getRandomNum(int limit) {
    Random random = new Random(System.currentTimeMillis());
    return random.nextInt(limit);
  }

  // -----------------------------------------------------------------------------------------------

  public static String getCustomColor(int color) {
    ArrayList<String> colors = new ArrayList<String>(
            Arrays.asList("#64b5f6", "#64b5f6", "#ef5350", "#ab47bc", "#5c6bc0", "#4dd0e1",
                    "#81c784", "#dce775", "#ffb74d", "#a1887f", "#fff176")
    );

    if (color >= colors.size()) {
      return colors.get(getRandomNum(colors.size()));
    }

    return colors.get(color);
  }

  // -----------------------------------------------------------------------------------------------
}

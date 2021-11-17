package com.kagof.intellij.plugins.pokeprogress;

import com.intellij.ui.JBColor;
import com.intellij.ui.scale.JBUIScale;
import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.util.List;

public final class Painter {
    private static final float ONE_HALF = 0.5f;

    private Painter() {
    }

    public static Paint getTransparencyPaint(final Color backgroundColor, final int width, final boolean movingRight) {
        final JBColor transparent = new JBColor(new Color(0, 0, 0, 0), new Color(0, 0, 0, 0));
        return new LinearGradientPaint(0, JBUIScale.scale(2f), width, JBUIScale.scale(2f),
            new float[] {0, 1}, new Color[] {movingRight ? backgroundColor : transparent,
            movingRight ? transparent : backgroundColor});
    }

    public static Paint getTypePaint(final Pokemon pokemon, final int height) {
        return getTypePaint(pokemon, 0, height);
    }

    public static Paint getTypePaint(final Pokemon pokemon, final int startY, final int height) {
        final List<PokemonType> types = pokemon.getTypes();
        final int numColors = types.size();
        if (numColors == 1) {
	        return types.get(0).getColorDark();
        }

        final float numColorsReciprocal = 1f / numColors;
		final float[] fractions = new float[numColors * 2];
		final Color[] colors = new Color[numColors * 2];
	
	    for (int i = 0; i < numColors; i++) {
			fractions[i * 2] = i == 0 ? 0F : Math.nextUp(numColorsReciprocal * i);
			fractions[i * 2 + 1] = i == numColors - 1 ? 1F : Math.nextDown(numColorsReciprocal * (i + 1));
		    final Color color = types.get(i).getColorDark();
		    colors[i * 2] = color;
		    colors[i * 2 + 1] = color;
	    }
		
        return new LinearGradientPaint(0, startY, 0, startY + height, fractions, colors);
    }
	
}

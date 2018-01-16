package br.ufc.samuel.backontrack.util.showcase;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import br.ufc.samuel.backontrack.R;

/**
 * Created by samue on 12/01/2018.
 */

public class CustomShowcaseView extends StandardShowcaseDrawer {

    private static final int ALPHA_60_PERCENT = 153;
    private final float outerRadius;
    private final float innerRadius;

    public CustomShowcaseView(Resources resources, Resources.Theme theme) {
        super(resources, theme);
        outerRadius = resources.getDimension(R.dimen.custom_showcase_radius_outer);
        innerRadius = resources.getDimension(R.dimen.custom_showcase_radius_inner);
    }

    @Override
    public void setShowcaseColour(int color) {
        eraserPaint.setColor(color);
    }

    @Override
    public void drawShowcase(Bitmap buffer, float x, float y, float scaleMultiplier) {
        Canvas bufferCanvas = new Canvas(buffer);
        eraserPaint.setAlpha(ALPHA_60_PERCENT);
        bufferCanvas.drawCircle(x, y, outerRadius, eraserPaint);
        eraserPaint.setAlpha(0);
        bufferCanvas.drawCircle(x, y, innerRadius, eraserPaint);
    }

    @Override
    public int getShowcaseWidth() {
        return (int) (outerRadius * 2);
    }

    @Override
    public int getShowcaseHeight() {
        return (int) (outerRadius * 2);
    }

    @Override
    public float getBlockedRadius() {
        return innerRadius;
    }
}


package com.android.wallpaper.nexus;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptC_nexus
    extends android.renderscript.ScriptC
{
    public ScriptC_nexus(RenderScript rs, Resources resources, int id) {
        super(rs, resources, id, true);
    }


    private int mField_gIsPreview;
    public void set_gIsPreview(int v) {
        mField_gIsPreview = v;
        setVar(0, v);
    }

    private float mField_gXOffset;
    public void set_gXOffset(float v) {
        mField_gXOffset = v;
        setVar(1, v);
    }

    private int mField_gMode;
    public void set_gMode(int v) {
        mField_gMode = v;
        setVar(2, v);
    }

    private ProgramFragment mField_gPFTexture;
    public void set_gPFTexture(ProgramFragment v) {
        mField_gPFTexture = v;
        setVar(3, v.getID());
    }

    private ProgramStore mField_gPSBlend;
    public void set_gPSBlend(ProgramStore v) {
        mField_gPSBlend = v;
        setVar(4, v.getID());
    }

    private ProgramFragment mField_gPFTexture565;
    public void set_gPFTexture565(ProgramFragment v) {
        mField_gPFTexture565 = v;
        setVar(5, v.getID());
    }

    private ProgramVertex mField_gPVOrtho;
    public void set_gPVOrtho(ProgramVertex v) {
        mField_gPVOrtho = v;
        setVar(6, v.getID());
    }
    public ProgramVertex get_gPVOrtho() {
        return mField_gPVOrtho;
    }

    private Allocation mField_gTBackground;
    public void set_gTBackground(Allocation v) {
        mField_gTBackground = v;
        setVar(7, v.getID());
    }

    private Allocation mField_gTPulse;
    public void set_gTPulse(Allocation v) {
        mField_gTPulse = v;
        setVar(8, v.getID());
    }

    private Allocation mField_gTGlow;
    public void set_gTGlow(Allocation v) {
        mField_gTGlow = v;
        setVar(9, v.getID());
    }




    public void invokable_initPulses() {
        invoke(4);
    }

    public void invokable_tap(int x, int y) {
        FieldPacker fp = new FieldPacker(8);
        fp.addI32(x);
        fp.addI32(y);
        invokeV(0, fp);
    }

}



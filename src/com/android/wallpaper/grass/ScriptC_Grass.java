
package com.android.wallpaper.grass;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptC_Grass
    extends android.renderscript.ScriptC
{
    private ScriptField_Blade mField_Blades;
    private ScriptField_Vertex mField_Verticies;

    public ScriptC_Grass(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    public void bind_Blades(ScriptField_Blade f) {
        mField_Blades = f;
        if (f == null) {
            bindAllocation(null, 20);
        } else {
            bindAllocation(f.getAllocation(), 20);
        }
    }
    public ScriptField_Blade get_Blades() {
        return mField_Blades;
    }

    public void bind_Verticies(ScriptField_Vertex f) {
        mField_Verticies = f;
        if (f == null) {
            bindAllocation(null, 21);
        } else {
            bindAllocation(f.getAllocation(), 21);
        }
    }
    public ScriptField_Vertex get_Verticies() {
        return mField_Verticies;
    }

    private int mField_gBladesCount;
    public void set_gBladesCount(int v) {
        mField_gBladesCount = v;
        setVar(0, v);
    }
    private int mField_gIndexCount;
    public void set_gIndexCount(int v) {
        mField_gIndexCount = v;
        setVar(1, v);
    }
    private int mField_gWidth;
    public void set_gWidth(int v) {
        mField_gWidth = v;
        setVar(2, v);
    }
    private int mField_gHeight;
    public void set_gHeight(int v) {
        mField_gHeight = v;
        setVar(3, v);
    }

    private float mField_gXOffset;
    public void set_gXOffset(float v) {
        mField_gXOffset = v;
        setVar(4, v);
    }
    private float mField_gDawn;
    public void set_gDawn(float v) {
        mField_gDawn = v;
        setVar(5, v);
    }
    private float mField_gMorning;
    public void set_gMorning(float v) {
        mField_gMorning = v;
        setVar(6, v);
    }
    private float mField_gAfternoon;
    public void set_gAfternoon(float v) {
        mField_gAfternoon = v;
        setVar(7, v);
    }
    private float mField_gDusk;
    public void set_gDusk(float v) {
        mField_gDusk = v;
        setVar(8, v);
    }

    private int mField_gIsPreview;
    public void set_gIsPreview(int v) {
        mField_gIsPreview = v;
        setVar(9, v);
    }

    private ProgramVertex mField_gPVBackground;
    public void set_gPVBackground(ProgramVertex v) {
        mField_gPVBackground = v;
        setVar(10, v.getID());
    }
    private ProgramFragment mField_gPFBackground;
    public void set_gPFBackground(ProgramFragment v) {
        mField_gPFBackground = v;
        setVar(11, v.getID());
    }
    private ProgramFragment mField_gPFGrass;
    public void set_gPFGrass(ProgramFragment v) {
        mField_gPFGrass = v;
        setVar(12, v.getID());
    }
    private ProgramStore mField_gPSBackground;
    public void set_gPSBackground(ProgramStore v) {
        mField_gPSBackground = v;
        setVar(13, v.getID());
    }

    private Allocation mField_gTNight;
    public void set_gTNight(Allocation v) {
        mField_gTNight = v;
        setVar(14, v.getID());
    }
    private Allocation mField_gTSunset;
    public void set_gTSunset(Allocation v) {
        mField_gTSunset = v;
        setVar(15, v.getID());
    }
    private Allocation mField_gTSunrise;
    public void set_gTSunrise(Allocation v) {
        mField_gTSunrise = v;
        setVar(16, v.getID());
    }
    private Allocation mField_gTSky;
    public void set_gTSky(Allocation v) {
        mField_gTSky = v;
        setVar(17, v.getID());
    }
    private Allocation mField_gTAa;
    public void set_gTAa(Allocation v) {
        mField_gTAa = v;
        setVar(18, v.getID());
    }
    private SimpleMesh mField_gBladesMesh;
    public void set_gBladesMesh(SimpleMesh v) {
        mField_gBladesMesh = v;
        setVar(19, v.getID());
    }


    public void invokable_updateBlades() {
        invoke(1);
    }
}


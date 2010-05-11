
package com.android.wallpaper.fall;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptC_Fall
    extends android.renderscript.ScriptC
{
    private ScriptField_Constants mField_Constants;

    public ScriptC_Fall(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    public ScriptField_Constants get_Constants() {
        return mField_Constants;
    }

    private float mField_g_glWidth;
    public void set_g_glWidth(float v) {
        mField_g_glWidth = v;
        setVar(0, v);
    }
    private float mField_g_glHeight;
    public void set_g_glHeight(float v) {
        mField_g_glHeight = v;
        setVar(1, v);
    }
    private float mField_g_meshWidth;
    public void set_g_meshWidth(float v) {
        mField_g_meshWidth = v;
        setVar(2, v);
    }
    private float mField_g_meshHeight;
    public void set_g_meshHeight(float v) {
        mField_g_meshHeight = v;
        setVar(3, v);
    }
    private float mField_g_xOffset;
    public void set_g_xOffset(float v) {
        mField_g_xOffset = v;
        setVar(4, v);
    }
    private float mField_g_rotate;
    public void set_g_rotate(float v) {
        mField_g_rotate = v;
        setVar(5, v);
    }
    private int mField_g_newDropX;
    public void set_g_newDropX(int v) {
        mField_g_newDropX = v;
        setVar(6, v);
    }
    private int mField_g_newDropY;
    public void set_g_newDropY(int v) {
        mField_g_newDropY = v;
        setVar(7, v);
    }

    private ProgramVertex mField_g_PVWater;
    public void set_g_PVWater(ProgramVertex v) {
        mField_g_PVWater = v;
        setVar(8, v.getID());
    }
    private ProgramVertex mField_g_PVSky;
    public void set_g_PVSky(ProgramVertex v) {
        mField_g_PVSky = v;
        setVar(9, v.getID());
    }

    private ProgramFragment mField_g_PFSky;
    public void set_g_PFSky(ProgramFragment v) {
        mField_g_PFSky = v;
        setVar(10, v.getID());
    }
    private ProgramStore mField_g_PFSLeaf;
    public void set_g_PFSLeaf(ProgramStore v) {
        mField_g_PFSLeaf = v;
        setVar(11, v.getID());
    }
    private ProgramFragment mField_g_PFBackground;
    public void set_g_PFBackground(ProgramFragment v) {
        mField_g_PFBackground = v;
        setVar(12, v.getID());
    }

    private Allocation mField_g_TLeaves;
    public void set_g_TLeaves(Allocation v) {
        mField_g_TLeaves = v;
        setVar(13, v.getID());
    }
    private Allocation mField_g_TRiverbed;
    public void set_g_TRiverbed(Allocation v) {
        mField_g_TRiverbed = v;
        setVar(14, v.getID());
    }

    private SimpleMesh mField_g_WaterMesh;
    public void set_g_WaterMesh(SimpleMesh v) {
        mField_g_WaterMesh = v;
        setVar(15, v.getID());
    }

    public void bind_Constants(ScriptField_Constants f) {
        mField_Constants = f;
        if (f == null) {
            bindAllocation(null, 16);
        } else {
            bindAllocation(f.getAllocation(), 16);
        }
    }

    private ProgramStore mField_g_PFSBackground;
    public void set_g_PFSBackground(ProgramStore v) {
        mField_g_PFSBackground = v;
        setVar(17, v.getID());
    }

    public void invokable_initLeaves() {
        invoke(1);
    }
}


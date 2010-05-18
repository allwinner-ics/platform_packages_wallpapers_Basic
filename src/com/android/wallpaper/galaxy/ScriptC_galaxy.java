package com.android.wallpaper.galaxy;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptC_galaxy
    extends android.renderscript.ScriptC
{
    public ScriptC_galaxy(RenderScript rs, Resources resources, int id) {
        super(rs, resources, id, true);
    }


    private float mField_gXOffset;
    public void set_gXOffset(float v) {
        mField_gXOffset = v;
        setVar(0, v);
    }

    private int mField_gIsPreview;
    public void set_gIsPreview(int v) {
        mField_gIsPreview = v;
        setVar(1, v);
    }



    private ProgramFragment mField_gPFBackground;
    public void set_gPFBackground(ProgramFragment v) {
        mField_gPFBackground = v;
        setVar(2, v.getID());
    }

    private ProgramFragment mField_gPFStars;
    public void set_gPFStars(ProgramFragment v) {
        mField_gPFStars = v;
        setVar(3, v.getID());
    }

    private ProgramVertex mField_gPVStars;
    public void set_gPVStars(ProgramVertex v) {
        mField_gPVStars = v;
        setVar(4, v.getID());
    }

    private ProgramVertex mField_gPVBkProj;
    public void set_gPVBkProj(ProgramVertex v) {
        mField_gPVBkProj = v;
        setVar(5, v.getID());
    }

    private ProgramStore mField_gPSLights;
    public void set_gPSLights(ProgramStore v) {
        mField_gPSLights = v;
        setVar(6, v.getID());
    }


    private Allocation mField_gTSpace;
    public void set_gTSpace(Allocation v) {
        mField_gTSpace = v;
        setVar(7, v.getID());
    }

    private Allocation mField_gTFlares;
    public void set_gTFlares(Allocation v) {
        mField_gTFlares = v;
        setVar(8, v.getID());
    }

    private Allocation mField_gTLight1;
    public void set_gTLight1(Allocation v) {
        mField_gTLight1 = v;
        setVar(9, v.getID());
    }

    private SimpleMesh mField_gParticlesMesh;
    public void set_gParticlesMesh(SimpleMesh v) {
        mField_gParticlesMesh = v;
        setVar(10, v.getID());
    }


    private ScriptField_Particle mField_Particle;
    public void bind_Particles(ScriptField_Particle f) {
        mField_Particle = f;
        if (f == null) {
            bindAllocation(null, 11);
        } else {
            bindAllocation(f.getAllocation(), 11);
        }
    }
    public ScriptField_Particle get_Particle() {
        return mField_Particle;
    }



}




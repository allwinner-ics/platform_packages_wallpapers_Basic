/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.wallpaper.galaxy;

import android.renderscript.*;
import android.content.res.Resources;
import android.util.Log;

public class ScriptC_Galaxy extends ScriptC {
    // Constructor
    public  ScriptC_Galaxy(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    private final static int mExportVarIdx_gXOffset = 0;
    private float mExportVar_gXOffset;
    public void set_gXOffset(float v) {
        mExportVar_gXOffset = v;
        setVar(mExportVarIdx_gXOffset, v);
    }

    public float get_gXOffset() {
        return mExportVar_gXOffset;
    }

    private final static int mExportVarIdx_gIsPreview = 1;
    private int mExportVar_gIsPreview;
    public void set_gIsPreview(int v) {
        mExportVar_gIsPreview = v;
        setVar(mExportVarIdx_gIsPreview, v);
    }

    public int get_gIsPreview() {
        return mExportVar_gIsPreview;
    }

    private final static int mExportVarIdx_gPFBackground = 2;
    private ProgramFragment mExportVar_gPFBackground;
    public void set_gPFBackground(ProgramFragment v) {
        mExportVar_gPFBackground = v;
        setVar(mExportVarIdx_gPFBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFBackground() {
        return mExportVar_gPFBackground;
    }

    private final static int mExportVarIdx_gPFStars = 3;
    private ProgramFragment mExportVar_gPFStars;
    public void set_gPFStars(ProgramFragment v) {
        mExportVar_gPFStars = v;
        setVar(mExportVarIdx_gPFStars, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFStars() {
        return mExportVar_gPFStars;
    }

    private final static int mExportVarIdx_gPVStars = 4;
    private ProgramVertex mExportVar_gPVStars;
    public void set_gPVStars(ProgramVertex v) {
        mExportVar_gPVStars = v;
        setVar(mExportVarIdx_gPVStars, (v == null) ? 0 : v.getID());
    }

    public ProgramVertex get_gPVStars() {
        return mExportVar_gPVStars;
    }

    private final static int mExportVarIdx_gPVBkProj = 5;
    private ProgramVertex mExportVar_gPVBkProj;
    public void set_gPVBkProj(ProgramVertex v) {
        mExportVar_gPVBkProj = v;
        setVar(mExportVarIdx_gPVBkProj, (v == null) ? 0 : v.getID());
    }

    public ProgramVertex get_gPVBkProj() {
        return mExportVar_gPVBkProj;
    }

    private final static int mExportVarIdx_gPSLights = 6;
    private ProgramStore mExportVar_gPSLights;
    public void set_gPSLights(ProgramStore v) {
        mExportVar_gPSLights = v;
        setVar(mExportVarIdx_gPSLights, (v == null) ? 0 : v.getID());
    }

    public ProgramStore get_gPSLights() {
        return mExportVar_gPSLights;
    }

    private final static int mExportVarIdx_gTSpace = 7;
    private Allocation mExportVar_gTSpace;
    public void set_gTSpace(Allocation v) {
        mExportVar_gTSpace = v;
        setVar(mExportVarIdx_gTSpace, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTSpace() {
        return mExportVar_gTSpace;
    }

    private final static int mExportVarIdx_gTFlares = 8;
    private Allocation mExportVar_gTFlares;
    public void set_gTFlares(Allocation v) {
        mExportVar_gTFlares = v;
        setVar(mExportVarIdx_gTFlares, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTFlares() {
        return mExportVar_gTFlares;
    }

    private final static int mExportVarIdx_gTLight1 = 9;
    private Allocation mExportVar_gTLight1;
    public void set_gTLight1(Allocation v) {
        mExportVar_gTLight1 = v;
        setVar(mExportVarIdx_gTLight1, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTLight1() {
        return mExportVar_gTLight1;
    }

    private final static int mExportVarIdx_gParticlesMesh = 10;
    private SimpleMesh mExportVar_gParticlesMesh;
    public void set_gParticlesMesh(SimpleMesh v) {
        mExportVar_gParticlesMesh = v;
        setVar(mExportVarIdx_gParticlesMesh, (v == null) ? 0 : v.getID());
    }

    public SimpleMesh get_gParticlesMesh() {
        return mExportVar_gParticlesMesh;
    }

    private final static int mExportVarIdx_Particles = 11;
    private ScriptField_Particle mExportVar_Particles;
    public void bind_Particles(ScriptField_Particle v) {
        mExportVar_Particles = v;
        if(v == null) bindAllocation(null, mExportVarIdx_Particles);
        else bindAllocation(v.getAllocation(), mExportVarIdx_Particles);
    }

    public ScriptField_Particle get_Particles() {
        return mExportVar_Particles;
    }

}


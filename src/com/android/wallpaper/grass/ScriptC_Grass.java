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

package com.android.wallpaper.grass;

import android.renderscript.*;
import android.content.res.Resources;
import android.util.Log;

public class ScriptC_Grass extends ScriptC {
    // Constructor
    public  ScriptC_Grass(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    private final static int mExportVarIdx_gBladesCount = 0;
    private int mExportVar_gBladesCount;
    public void set_gBladesCount(int v) {
        mExportVar_gBladesCount = v;
        setVar(mExportVarIdx_gBladesCount, v);
    }

    public int get_gBladesCount() {
        return mExportVar_gBladesCount;
    }

    private final static int mExportVarIdx_gIndexCount = 1;
    private int mExportVar_gIndexCount;
    public void set_gIndexCount(int v) {
        mExportVar_gIndexCount = v;
        setVar(mExportVarIdx_gIndexCount, v);
    }

    public int get_gIndexCount() {
        return mExportVar_gIndexCount;
    }

    private final static int mExportVarIdx_gWidth = 2;
    private int mExportVar_gWidth;
    public void set_gWidth(int v) {
        mExportVar_gWidth = v;
        setVar(mExportVarIdx_gWidth, v);
    }

    public int get_gWidth() {
        return mExportVar_gWidth;
    }

    private final static int mExportVarIdx_gHeight = 3;
    private int mExportVar_gHeight;
    public void set_gHeight(int v) {
        mExportVar_gHeight = v;
        setVar(mExportVarIdx_gHeight, v);
    }

    public int get_gHeight() {
        return mExportVar_gHeight;
    }

    private final static int mExportVarIdx_gXOffset = 4;
    private float mExportVar_gXOffset;
    public void set_gXOffset(float v) {
        mExportVar_gXOffset = v;
        setVar(mExportVarIdx_gXOffset, v);
    }

    public float get_gXOffset() {
        return mExportVar_gXOffset;
    }

    private final static int mExportVarIdx_gDawn = 5;
    private float mExportVar_gDawn;
    public void set_gDawn(float v) {
        mExportVar_gDawn = v;
        setVar(mExportVarIdx_gDawn, v);
    }

    public float get_gDawn() {
        return mExportVar_gDawn;
    }

    private final static int mExportVarIdx_gMorning = 6;
    private float mExportVar_gMorning;
    public void set_gMorning(float v) {
        mExportVar_gMorning = v;
        setVar(mExportVarIdx_gMorning, v);
    }

    public float get_gMorning() {
        return mExportVar_gMorning;
    }

    private final static int mExportVarIdx_gAfternoon = 7;
    private float mExportVar_gAfternoon;
    public void set_gAfternoon(float v) {
        mExportVar_gAfternoon = v;
        setVar(mExportVarIdx_gAfternoon, v);
    }

    public float get_gAfternoon() {
        return mExportVar_gAfternoon;
    }

    private final static int mExportVarIdx_gDusk = 8;
    private float mExportVar_gDusk;
    public void set_gDusk(float v) {
        mExportVar_gDusk = v;
        setVar(mExportVarIdx_gDusk, v);
    }

    public float get_gDusk() {
        return mExportVar_gDusk;
    }

    private final static int mExportVarIdx_gIsPreview = 9;
    private int mExportVar_gIsPreview;
    public void set_gIsPreview(int v) {
        mExportVar_gIsPreview = v;
        setVar(mExportVarIdx_gIsPreview, v);
    }

    public int get_gIsPreview() {
        return mExportVar_gIsPreview;
    }

    private final static int mExportVarIdx_gPVBackground = 10;
    private ProgramVertex mExportVar_gPVBackground;
    public void set_gPVBackground(ProgramVertex v) {
        mExportVar_gPVBackground = v;
        setVar(mExportVarIdx_gPVBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramVertex get_gPVBackground() {
        return mExportVar_gPVBackground;
    }

    private final static int mExportVarIdx_gPFBackground = 11;
    private ProgramFragment mExportVar_gPFBackground;
    public void set_gPFBackground(ProgramFragment v) {
        mExportVar_gPFBackground = v;
        setVar(mExportVarIdx_gPFBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFBackground() {
        return mExportVar_gPFBackground;
    }

    private final static int mExportVarIdx_gPFGrass = 12;
    private ProgramFragment mExportVar_gPFGrass;
    public void set_gPFGrass(ProgramFragment v) {
        mExportVar_gPFGrass = v;
        setVar(mExportVarIdx_gPFGrass, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFGrass() {
        return mExportVar_gPFGrass;
    }

    private final static int mExportVarIdx_gPSBackground = 13;
    private ProgramStore mExportVar_gPSBackground;
    public void set_gPSBackground(ProgramStore v) {
        mExportVar_gPSBackground = v;
        setVar(mExportVarIdx_gPSBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramStore get_gPSBackground() {
        return mExportVar_gPSBackground;
    }

    private final static int mExportVarIdx_gTNight = 14;
    private Allocation mExportVar_gTNight;
    public void set_gTNight(Allocation v) {
        mExportVar_gTNight = v;
        setVar(mExportVarIdx_gTNight, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTNight() {
        return mExportVar_gTNight;
    }

    private final static int mExportVarIdx_gTSunset = 15;
    private Allocation mExportVar_gTSunset;
    public void set_gTSunset(Allocation v) {
        mExportVar_gTSunset = v;
        setVar(mExportVarIdx_gTSunset, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTSunset() {
        return mExportVar_gTSunset;
    }

    private final static int mExportVarIdx_gTSunrise = 16;
    private Allocation mExportVar_gTSunrise;
    public void set_gTSunrise(Allocation v) {
        mExportVar_gTSunrise = v;
        setVar(mExportVarIdx_gTSunrise, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTSunrise() {
        return mExportVar_gTSunrise;
    }

    private final static int mExportVarIdx_gTSky = 17;
    private Allocation mExportVar_gTSky;
    public void set_gTSky(Allocation v) {
        mExportVar_gTSky = v;
        setVar(mExportVarIdx_gTSky, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTSky() {
        return mExportVar_gTSky;
    }

    private final static int mExportVarIdx_gTAa = 18;
    private Allocation mExportVar_gTAa;
    public void set_gTAa(Allocation v) {
        mExportVar_gTAa = v;
        setVar(mExportVarIdx_gTAa, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTAa() {
        return mExportVar_gTAa;
    }

    private final static int mExportVarIdx_gBladesMesh = 19;
    private SimpleMesh mExportVar_gBladesMesh;
    public void set_gBladesMesh(SimpleMesh v) {
        mExportVar_gBladesMesh = v;
        setVar(mExportVarIdx_gBladesMesh, (v == null) ? 0 : v.getID());
    }

    public SimpleMesh get_gBladesMesh() {
        return mExportVar_gBladesMesh;
    }

    private final static int mExportVarIdx_Blades = 20;
    private ScriptField_Blade mExportVar_Blades;
    public void bind_Blades(ScriptField_Blade v) {
        mExportVar_Blades = v;
        if(v == null) bindAllocation(null, mExportVarIdx_Blades);
        else bindAllocation(v.getAllocation(), mExportVarIdx_Blades);
    }

    public ScriptField_Blade get_Blades() {
        return mExportVar_Blades;
    }

    private final static int mExportVarIdx_Verticies = 21;
    private ScriptField_Vertex mExportVar_Verticies;
    public void bind_Verticies(ScriptField_Vertex v) {
        mExportVar_Verticies = v;
        if(v == null) bindAllocation(null, mExportVarIdx_Verticies);
        else bindAllocation(v.getAllocation(), mExportVarIdx_Verticies);
    }

    public ScriptField_Vertex get_Verticies() {
        return mExportVar_Verticies;
    }

    private final static int mExportFuncIdx_updateBlades = 0;
    public void invoke_updateBlades() {
        invoke(mExportFuncIdx_updateBlades);
    }

}


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

package com.android.wallpaper.fall;

import android.renderscript.*;
import android.content.res.Resources;
import android.util.Log;

public class ScriptC_Fall extends ScriptC {
    // Constructor
    public  ScriptC_Fall(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    private final static int mExportVarIdx_g_glWidth = 0;
    private float mExportVar_g_glWidth;
    public void set_g_glWidth(float v) {
        mExportVar_g_glWidth = v;
        setVar(mExportVarIdx_g_glWidth, v);
    }

    public float get_g_glWidth() {
        return mExportVar_g_glWidth;
    }

    private final static int mExportVarIdx_g_glHeight = 1;
    private float mExportVar_g_glHeight;
    public void set_g_glHeight(float v) {
        mExportVar_g_glHeight = v;
        setVar(mExportVarIdx_g_glHeight, v);
    }

    public float get_g_glHeight() {
        return mExportVar_g_glHeight;
    }

    private final static int mExportVarIdx_g_meshWidth = 2;
    private float mExportVar_g_meshWidth;
    public void set_g_meshWidth(float v) {
        mExportVar_g_meshWidth = v;
        setVar(mExportVarIdx_g_meshWidth, v);
    }

    public float get_g_meshWidth() {
        return mExportVar_g_meshWidth;
    }

    private final static int mExportVarIdx_g_meshHeight = 3;
    private float mExportVar_g_meshHeight;
    public void set_g_meshHeight(float v) {
        mExportVar_g_meshHeight = v;
        setVar(mExportVarIdx_g_meshHeight, v);
    }

    public float get_g_meshHeight() {
        return mExportVar_g_meshHeight;
    }

    private final static int mExportVarIdx_g_xOffset = 4;
    private float mExportVar_g_xOffset;
    public void set_g_xOffset(float v) {
        mExportVar_g_xOffset = v;
        setVar(mExportVarIdx_g_xOffset, v);
    }

    public float get_g_xOffset() {
        return mExportVar_g_xOffset;
    }

    private final static int mExportVarIdx_g_rotate = 5;
    private float mExportVar_g_rotate;
    public void set_g_rotate(float v) {
        mExportVar_g_rotate = v;
        setVar(mExportVarIdx_g_rotate, v);
    }

    public float get_g_rotate() {
        return mExportVar_g_rotate;
    }

    private final static int mExportVarIdx_g_newDropX = 6;
    private int mExportVar_g_newDropX;
    public void set_g_newDropX(int v) {
        mExportVar_g_newDropX = v;
        setVar(mExportVarIdx_g_newDropX, v);
    }

    public int get_g_newDropX() {
        return mExportVar_g_newDropX;
    }

    private final static int mExportVarIdx_g_newDropY = 7;
    private int mExportVar_g_newDropY;
    public void set_g_newDropY(int v) {
        mExportVar_g_newDropY = v;
        setVar(mExportVarIdx_g_newDropY, v);
    }

    public int get_g_newDropY() {
        return mExportVar_g_newDropY;
    }

    private final static int mExportVarIdx_g_PVWater = 8;
    private ProgramVertex mExportVar_g_PVWater;
    public void set_g_PVWater(ProgramVertex v) {
        mExportVar_g_PVWater = v;
        setVar(mExportVarIdx_g_PVWater, (v == null) ? 0 : v.getID());
    }

    public ProgramVertex get_g_PVWater() {
        return mExportVar_g_PVWater;
    }

    private final static int mExportVarIdx_g_PVSky = 9;
    private ProgramVertex mExportVar_g_PVSky;
    public void set_g_PVSky(ProgramVertex v) {
        mExportVar_g_PVSky = v;
        setVar(mExportVarIdx_g_PVSky, (v == null) ? 0 : v.getID());
    }

    public ProgramVertex get_g_PVSky() {
        return mExportVar_g_PVSky;
    }

    private final static int mExportVarIdx_g_PFSky = 10;
    private ProgramFragment mExportVar_g_PFSky;
    public void set_g_PFSky(ProgramFragment v) {
        mExportVar_g_PFSky = v;
        setVar(mExportVarIdx_g_PFSky, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_g_PFSky() {
        return mExportVar_g_PFSky;
    }

    private final static int mExportVarIdx_g_PFSLeaf = 11;
    private ProgramStore mExportVar_g_PFSLeaf;
    public void set_g_PFSLeaf(ProgramStore v) {
        mExportVar_g_PFSLeaf = v;
        setVar(mExportVarIdx_g_PFSLeaf, (v == null) ? 0 : v.getID());
    }

    public ProgramStore get_g_PFSLeaf() {
        return mExportVar_g_PFSLeaf;
    }

    private final static int mExportVarIdx_g_PFBackground = 12;
    private ProgramFragment mExportVar_g_PFBackground;
    public void set_g_PFBackground(ProgramFragment v) {
        mExportVar_g_PFBackground = v;
        setVar(mExportVarIdx_g_PFBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_g_PFBackground() {
        return mExportVar_g_PFBackground;
    }

    private final static int mExportVarIdx_g_TLeaves = 13;
    private Allocation mExportVar_g_TLeaves;
    public void set_g_TLeaves(Allocation v) {
        mExportVar_g_TLeaves = v;
        setVar(mExportVarIdx_g_TLeaves, (v == null) ? 0 : v.getID());
    }

    public Allocation get_g_TLeaves() {
        return mExportVar_g_TLeaves;
    }

    private final static int mExportVarIdx_g_TRiverbed = 14;
    private Allocation mExportVar_g_TRiverbed;
    public void set_g_TRiverbed(Allocation v) {
        mExportVar_g_TRiverbed = v;
        setVar(mExportVarIdx_g_TRiverbed, (v == null) ? 0 : v.getID());
    }

    public Allocation get_g_TRiverbed() {
        return mExportVar_g_TRiverbed;
    }

    private final static int mExportVarIdx_g_WaterMesh = 15;
    private Mesh mExportVar_g_WaterMesh;
    public void set_g_WaterMesh(Mesh v) {
        mExportVar_g_WaterMesh = v;
        setVar(mExportVarIdx_g_WaterMesh, (v == null) ? 0 : v.getID());
    }

    public Mesh get_g_WaterMesh() {
        return mExportVar_g_WaterMesh;
    }

    private final static int mExportVarIdx_g_Constants = 16;
    private ScriptField_Constants mExportVar_g_Constants;
    public void bind_g_Constants(ScriptField_Constants v) {
        mExportVar_g_Constants = v;
        if(v == null) bindAllocation(null, mExportVarIdx_g_Constants);
        else bindAllocation(v.getAllocation(), mExportVarIdx_g_Constants);
    }

    public ScriptField_Constants get_g_Constants() {
        return mExportVar_g_Constants;
    }

    private final static int mExportVarIdx_g_PFSBackground = 17;
    private ProgramStore mExportVar_g_PFSBackground;
    public void set_g_PFSBackground(ProgramStore v) {
        mExportVar_g_PFSBackground = v;
        setVar(mExportVarIdx_g_PFSBackground, (v == null) ? 0 : v.getID());
    }

    public ProgramStore get_g_PFSBackground() {
        return mExportVar_g_PFSBackground;
    }

    private final static int mExportFuncIdx_initLeaves = 0;
    public void invoke_initLeaves() {
        invoke(mExportFuncIdx_initLeaves);
    }

}


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

package com.android.wallpaper.nexus;

import android.renderscript.*;
import android.content.res.Resources;
import android.util.Log;

public class ScriptC_Nexus extends ScriptC {
    // Constructor
    public  ScriptC_Nexus(RenderScript rs, Resources resources, int id, boolean isRoot) {
        super(rs, resources, id, isRoot);
    }

    private final static int mExportVarIdx_gIsPreview = 0;
    private int mExportVar_gIsPreview;
    public void set_gIsPreview(int v) {
        mExportVar_gIsPreview = v;
        setVar(mExportVarIdx_gIsPreview, v);
    }

    public int get_gIsPreview() {
        return mExportVar_gIsPreview;
    }

    private final static int mExportVarIdx_gXOffset = 1;
    private float mExportVar_gXOffset;
    public void set_gXOffset(float v) {
        mExportVar_gXOffset = v;
        setVar(mExportVarIdx_gXOffset, v);
    }

    public float get_gXOffset() {
        return mExportVar_gXOffset;
    }

    private final static int mExportVarIdx_gMode = 2;
    private int mExportVar_gMode;
    public void set_gMode(int v) {
        mExportVar_gMode = v;
        setVar(mExportVarIdx_gMode, v);
    }

    public int get_gMode() {
        return mExportVar_gMode;
    }

    private final static int mExportVarIdx_gPFTexture = 3;
    private ProgramFragment mExportVar_gPFTexture;
    public void set_gPFTexture(ProgramFragment v) {
        mExportVar_gPFTexture = v;
        setVar(mExportVarIdx_gPFTexture, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFTexture() {
        return mExportVar_gPFTexture;
    }

    private final static int mExportVarIdx_gPSBlend = 4;
    private ProgramStore mExportVar_gPSBlend;
    public void set_gPSBlend(ProgramStore v) {
        mExportVar_gPSBlend = v;
        setVar(mExportVarIdx_gPSBlend, (v == null) ? 0 : v.getID());
    }

    public ProgramStore get_gPSBlend() {
        return mExportVar_gPSBlend;
    }

    private final static int mExportVarIdx_gPFTexture565 = 5;
    private ProgramFragment mExportVar_gPFTexture565;
    public void set_gPFTexture565(ProgramFragment v) {
        mExportVar_gPFTexture565 = v;
        setVar(mExportVarIdx_gPFTexture565, (v == null) ? 0 : v.getID());
    }

    public ProgramFragment get_gPFTexture565() {
        return mExportVar_gPFTexture565;
    }

    private final static int mExportVarIdx_gTBackground = 6;
    private Allocation mExportVar_gTBackground;
    public void set_gTBackground(Allocation v) {
        mExportVar_gTBackground = v;
        setVar(mExportVarIdx_gTBackground, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTBackground() {
        return mExportVar_gTBackground;
    }

    private final static int mExportVarIdx_gTPulse = 7;
    private Allocation mExportVar_gTPulse;
    public void set_gTPulse(Allocation v) {
        mExportVar_gTPulse = v;
        setVar(mExportVarIdx_gTPulse, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTPulse() {
        return mExportVar_gTPulse;
    }

    private final static int mExportVarIdx_gTGlow = 8;
    private Allocation mExportVar_gTGlow;
    public void set_gTGlow(Allocation v) {
        mExportVar_gTGlow = v;
        setVar(mExportVarIdx_gTGlow, (v == null) ? 0 : v.getID());
    }

    public Allocation get_gTGlow() {
        return mExportVar_gTGlow;
    }

    private final static int mExportFuncIdx_initPulses = 0;
    public void invoke_initPulses() {
        invoke(mExportFuncIdx_initPulses);
    }

    private final static int mExportFuncIdx_addTap = 1;
    public void invoke_addTap(int x, int y) {
        FieldPacker addTap_fp = new FieldPacker(8);
        addTap_fp.addI32(x);
        addTap_fp.addI32(y);
        invoke(mExportFuncIdx_addTap, addTap_fp);
    }

}


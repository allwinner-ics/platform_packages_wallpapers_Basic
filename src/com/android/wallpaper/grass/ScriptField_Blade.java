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

public class ScriptField_Blade extends android.renderscript.Script.FieldBase {
    static public class Item {
        public static final int sizeof = 52;

        float angle;
        int size;
        float xPos;
        float yPos;
        float offset;
        float scale;
        float lengthX;
        float lengthY;
        float hardness;
        float h;
        float s;
        float b;
        float turbulencex;

        Item() {
        }

    }

    private Item mItemArray[];
    private FieldPacker mIOBuffer;
    public static Element createElement(RenderScript rs) {
        Element.Builder eb = new Element.Builder(rs);
        eb.add(Element.F32(rs), "angle");
        eb.add(Element.I32(rs), "size");
        eb.add(Element.F32(rs), "xPos");
        eb.add(Element.F32(rs), "yPos");
        eb.add(Element.F32(rs), "offset");
        eb.add(Element.F32(rs), "scale");
        eb.add(Element.F32(rs), "lengthX");
        eb.add(Element.F32(rs), "lengthY");
        eb.add(Element.F32(rs), "hardness");
        eb.add(Element.F32(rs), "h");
        eb.add(Element.F32(rs), "s");
        eb.add(Element.F32(rs), "b");
        eb.add(Element.F32(rs), "turbulencex");
        return eb.create();
    }

    public  ScriptField_Blade(RenderScript rs, int count) {
        mItemArray = null;
        mIOBuffer = null;
        mElement = createElement(rs);
        init(rs, count);
    }

    private void copyToArray(Item i, int index) {
        if (mIOBuffer == null) mIOBuffer = new FieldPacker(Item.sizeof * mType.getX() /* count */);
        mIOBuffer.reset(index * Item.sizeof);
        mIOBuffer.addF32(i.angle);
        mIOBuffer.addI32(i.size);
        mIOBuffer.addF32(i.xPos);
        mIOBuffer.addF32(i.yPos);
        mIOBuffer.addF32(i.offset);
        mIOBuffer.addF32(i.scale);
        mIOBuffer.addF32(i.lengthX);
        mIOBuffer.addF32(i.lengthY);
        mIOBuffer.addF32(i.hardness);
        mIOBuffer.addF32(i.h);
        mIOBuffer.addF32(i.s);
        mIOBuffer.addF32(i.b);
        mIOBuffer.addF32(i.turbulencex);
    }

    public void set(Item i, int index, boolean copyNow) {
        if (mItemArray == null) mItemArray = new Item[mType.getX() /* count */];
        mItemArray[index] = i;
        if (copyNow)  {
            copyToArray(i, index);
            mAllocation.subData1D(index, 1, mIOBuffer.getData());
        }

    }

    public void copyAll() {
        for (int ct=0; ct < mItemArray.length; ct++) copyToArray(mItemArray[ct], ct);
        mAllocation.data(mIOBuffer.getData());
    }

}


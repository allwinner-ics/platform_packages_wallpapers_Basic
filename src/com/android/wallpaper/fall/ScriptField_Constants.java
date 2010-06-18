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

public class ScriptField_Constants extends android.renderscript.Script.FieldBase {
    static public class Item {
        public static final int sizeof = 192;

        Float4 Drop01;
        Float4 Drop02;
        Float4 Drop03;
        Float4 Drop04;
        Float4 Drop05;
        Float4 Drop06;
        Float4 Drop07;
        Float4 Drop08;
        Float4 Drop09;
        Float4 Drop10;
        Float4 Offset;
        float Rotate;

        Item() {
            Drop01 = new Float4();
            Drop02 = new Float4();
            Drop03 = new Float4();
            Drop04 = new Float4();
            Drop05 = new Float4();
            Drop06 = new Float4();
            Drop07 = new Float4();
            Drop08 = new Float4();
            Drop09 = new Float4();
            Drop10 = new Float4();
            Offset = new Float4();
        }

    }

    private Item mItemArray[];
    private FieldPacker mIOBuffer;
    public  ScriptField_Constants(RenderScript rs, int count) {
        mItemArray = null;
        mIOBuffer = null;
        {
            Element.Builder eb = new Element.Builder(rs);
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop01");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop02");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop03");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop04");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop05");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop06");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop07");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop08");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop09");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Drop10");
            eb.add(Element.createVector(rs, Element.DataType.FLOAT_32, 4), "Offset");
            eb.add(Element.F32(rs), "Rotate");
            /*            eb.add(Element.U32(rs), "#padding_1");
            eb.add(Element.U32(rs), "#padding_2");
            eb.add(Element.U32(rs), "#padding_3");*/
            mElement = eb.create();
        }

        init(rs, count);
    }

    private void copyToArray(Item i, int index) {
        if (mIOBuffer == null) mIOBuffer = new FieldPacker(Item.sizeof * mType.getX() /* count */);
        mIOBuffer.reset(index * Item.sizeof);
        mIOBuffer.addF32(i.Drop01);
        mIOBuffer.addF32(i.Drop02);
        mIOBuffer.addF32(i.Drop03);
        mIOBuffer.addF32(i.Drop04);
        mIOBuffer.addF32(i.Drop05);
        mIOBuffer.addF32(i.Drop06);
        mIOBuffer.addF32(i.Drop07);
        mIOBuffer.addF32(i.Drop08);
        mIOBuffer.addF32(i.Drop09);
        mIOBuffer.addF32(i.Drop10);
        mIOBuffer.addF32(i.Offset);
        mIOBuffer.addF32(i.Rotate);
        mIOBuffer.skip(12);
    }

    public void set(Item i, int index, boolean copyNow) {
        if (mItemArray == null) mItemArray = new Item[mType.getX() /* count */];
        mItemArray[index] = i;
        if (copyNow)  {
            copyToArray(i, index);
            mAllocation.subData1D(index * Item.sizeof, Item.sizeof, mIOBuffer.getData());
        }

    }

    public void copyAll() {
        for (int ct=0; ct < mItemArray.length; ct++) copyToArray(mItemArray[ct], ct);
        mAllocation.data(mIOBuffer.getData());
    }

}

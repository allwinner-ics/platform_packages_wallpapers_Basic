
package com.android.wallpaper.fall;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptField_Constants
    extends android.renderscript.Script.FieldBase
{

    static public class Item {
        Item() {
        }

        // When a float2 is present LLVM alings to 8 bytes.
        public static final int sizeof = (11*4*4 + 4);
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
    }
    private Item mItemArray[];


    public ScriptField_Constants(RenderScript rs, int count) {
        // Allocate a pack/unpack buffer
        mIOBuffer = new FieldPacker(Item.sizeof * count);
        mItemArray = new Item[count];

        Element.Builder eb = new Element.Builder(rs);
        // Make this an array when we can.
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
        mElement = eb.create();

        init(rs, count);
    }

    private void copyToArray(Item i, int index) {
        mIOBuffer.reset(index * Item.sizeof);
        mIOBuffer.addF32(i.Drop01.x);
        mIOBuffer.addF32(i.Drop01.y);
        mIOBuffer.addF32(i.Drop01.z);
        mIOBuffer.addF32(i.Drop01.w);

        mIOBuffer.addF32(i.Drop02.x);
        mIOBuffer.addF32(i.Drop02.y);
        mIOBuffer.addF32(i.Drop02.z);
        mIOBuffer.addF32(i.Drop02.w);

        mIOBuffer.addF32(i.Drop03.x);
        mIOBuffer.addF32(i.Drop03.y);
        mIOBuffer.addF32(i.Drop03.z);
        mIOBuffer.addF32(i.Drop03.w);

        mIOBuffer.addF32(i.Drop04.x);
        mIOBuffer.addF32(i.Drop04.y);
        mIOBuffer.addF32(i.Drop04.z);
        mIOBuffer.addF32(i.Drop04.w);

        mIOBuffer.addF32(i.Drop05.x);
        mIOBuffer.addF32(i.Drop05.y);
        mIOBuffer.addF32(i.Drop05.z);
        mIOBuffer.addF32(i.Drop05.w);

        mIOBuffer.addF32(i.Drop06.x);
        mIOBuffer.addF32(i.Drop06.y);
        mIOBuffer.addF32(i.Drop06.z);
        mIOBuffer.addF32(i.Drop06.w);

        mIOBuffer.addF32(i.Drop07.x);
        mIOBuffer.addF32(i.Drop07.y);
        mIOBuffer.addF32(i.Drop07.z);
        mIOBuffer.addF32(i.Drop07.w);

        mIOBuffer.addF32(i.Drop08.x);
        mIOBuffer.addF32(i.Drop08.y);
        mIOBuffer.addF32(i.Drop08.z);
        mIOBuffer.addF32(i.Drop08.w);

        mIOBuffer.addF32(i.Drop09.x);
        mIOBuffer.addF32(i.Drop09.y);
        mIOBuffer.addF32(i.Drop09.z);
        mIOBuffer.addF32(i.Drop09.w);

        mIOBuffer.addF32(i.Drop10.x);
        mIOBuffer.addF32(i.Drop10.y);
        mIOBuffer.addF32(i.Drop10.z);
        mIOBuffer.addF32(i.Drop10.w);

        mIOBuffer.addF32(i.Offset.x);
        mIOBuffer.addF32(i.Offset.y);
        mIOBuffer.addF32(i.Offset.z);
        mIOBuffer.addF32(i.Offset.w);

        mIOBuffer.addF32(i.Rotate);
    }

    public void set(Item i, int index, boolean copyNow) {
        mItemArray[index] = i;
        if (copyNow) {
            copyToArray(i, index);
            mAllocation.subData1D(index * Item.sizeof, Item.sizeof, mIOBuffer.getData());
        }
    }

    public void copyAll() {
        for (int ct=0; ct < mItemArray.length; ct++) {
            copyToArray(mItemArray[ct], ct);
        }
        mAllocation.data(mIOBuffer.getData());
    }


    private FieldPacker mIOBuffer;
}

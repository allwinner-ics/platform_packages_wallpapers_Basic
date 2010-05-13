package com.android.wallpaper.galaxy;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptField_Particle
    extends android.renderscript.Script.FieldBase
{

    static public class Item {
        Item() {
            position = new Float3();
        }

        public static final int sizeof = (4*4);
        Short4 color;
        Float3 position;
    }
    private Item mItemArray[];


    public ScriptField_Particle(RenderScript rs, int count) {
        // Allocate a pack/unpack buffer
        mIOBuffer = new FieldPacker(Item.sizeof * count);
        mItemArray = new Item[count];

        Element.Builder eb = new Element.Builder(rs);
        eb.add(Element.createAttrib(rs, Element.DataType.UNSIGNED_8, Element.DataKind.USER, 4), "color");
        eb.add(Element.createAttrib(rs, Element.DataType.FLOAT_32, Element.DataKind.USER, 3), "position");
        mElement = eb.create();

        init(rs, count);
    }

    private void copyToArray(Item i, int index) {
        mIOBuffer.reset(index * Item.sizeof);
        mIOBuffer.addU8(i.color);
        mIOBuffer.addF32(i.position);
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



package com.android.wallpaper.grass;

import android.content.res.Resources;
import android.renderscript.*;
import android.util.Log;

public class ScriptField_Blade
    extends android.renderscript.Script.FieldBase
{

    static public class Item {
        Item() {
        }

        // When a float2 is present LLVM alings to 8 bytes.
        public static final int sizeof = (13*4);
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
    }
    private Item mItemArray[];


    public ScriptField_Blade(RenderScript rs, int count) {
        // Allocate a pack/unpack buffer
        mIOBuffer = new FieldPacker(Item.sizeof * count);
        mItemArray = new Item[count];

        Element.Builder eb = new Element.Builder(rs);
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "angle");
        eb.add(Element.createUser(rs, Element.DataType.SIGNED_32), "size");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "xPos");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "yPos");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "offset");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "scale");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "lengthX");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "lengthY");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "hardness");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "h");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "s");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "b");
        eb.add(Element.createUser(rs, Element.DataType.FLOAT_32), "turbulencex");
        mElement = eb.create();

        init(rs, count);
    }

    private void copyToArray(Item i, int index) {
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

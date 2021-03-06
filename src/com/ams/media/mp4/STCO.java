package com.ams.media.mp4;

import java.io.DataInputStream;
import java.io.IOException;

public final class STCO extends BOX {
    private long[] offsets;

    public STCO(int version) {
        super(version);
    }

    public void read(DataInputStream in) throws IOException {
        int count = in.readInt();
        offsets = new long[count];
        for (int i = 0; i < count; i++) {
            offsets[i] = in.readInt();
        }
    }

    public void read64(DataInputStream in) throws IOException {
        int count = in.readInt();
        offsets = new long[count];
        for (int i = 0; i < count; i++) {
            offsets[i] = in.readLong();
        }
    }

    public long[] getOffsets() {
        return offsets;
    }
}
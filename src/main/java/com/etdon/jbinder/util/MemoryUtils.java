package com.etdon.jbinder.util;

import com.etdon.commons.conditional.Preconditions;
import com.etdon.commons.util.ByteUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

public final class MemoryUtils {

    public static String toHexString(@NotNull final MemorySegment memorySegment) {

        return toHexString(memorySegment, 0);

    }

    public static String toHexString(@NotNull final MemorySegment memorySegment, final int groupSize) {

        Preconditions.checkNotNull(memorySegment);
        if (memorySegment.byteSize() == 0)
            return "";

        int groupCounter = 0;
        final StringBuilder output = new StringBuilder();
        final ByteBuffer byteBuffer = memorySegment.asByteBuffer();
        while (byteBuffer.hasRemaining()) {
            output.append(ByteUtils.byteToHexString(byteBuffer.get()));
            if (groupSize == 0) continue;
            groupCounter++;
            if (groupCounter >= groupSize &&
                    byteBuffer.hasRemaining()) {
                output.append(' ');
                groupCounter = 0;
            }
        }

        return output.toString();

    }

    private MemoryUtils() {

        throw new UnsupportedOperationException();

    }

}

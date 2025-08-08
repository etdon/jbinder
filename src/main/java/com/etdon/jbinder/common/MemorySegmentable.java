package com.etdon.jbinder.common;

import org.jetbrains.annotations.NotNull;

import java.lang.foreign.*;

public interface MemorySegmentable {

    @NotNull
    MemoryLayout getMemoryLayout();

    @NotNull
    MemorySegment createMemorySegment(@NotNull final Arena arena);

    MemorySegmentable NULL = new MemorySegmentable() {
        @NotNull
        @Override
        public MemoryLayout getMemoryLayout() {
            return ValueLayout.ADDRESS;
        }

        @NotNull
        @Override
        public MemorySegment createMemorySegment(@NotNull final Arena arena) {
            return MemorySegment.NULL;
        }
    };

}

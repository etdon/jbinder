package com.etdon.jbinder.common;

import org.jetbrains.annotations.NotNull;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public interface MemorySegmentable {

    @NotNull
    MemorySegment createMemorySegment(@NotNull final Arena arena);

    MemorySegmentable NULL = (_) -> MemorySegment.NULL;

}

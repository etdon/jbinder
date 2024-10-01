package com.etdon.jbinder.foreign;

import com.etdon.commons.conditional.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.lang.foreign.MemoryLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class Union extends ForeignConcept {

    private final List<MemoryLayout> memoryLayouts = new ArrayList<>();

    private Union(@NotNull final MemoryLayout... types) {

        Preconditions.checkNotNull(types);
        this.memoryLayouts.addAll(Arrays.asList(types));

    }

    public MemoryLayout getLargestMember() {

        return this.memoryLayouts.stream()
                .max(Comparator.comparingLong(MemoryLayout::byteSize))
                .orElseThrow();

    }

    public static Union of(@NotNull final MemoryLayout... memoryLayouts) {

        Preconditions.checkNotNull(memoryLayouts);
        return new Union(memoryLayouts);

    }

}

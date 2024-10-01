package com.etdon.jbinder;

import com.etdon.commons.conditional.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.foreign.SymbolLookup;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SymbolLookupCache {

    private final Map<String, SymbolLookup> entries = new HashMap<>();

    @Nullable
    public SymbolLookup get(@NotNull final String key) {

        Preconditions.checkNotNull(key);
        return entries.get(key);

    }

    @NotNull
    public SymbolLookup getOrThrow(@NotNull final String key) {

        Preconditions.checkNotNull(key);
        return Optional.ofNullable(entries.get(key)).orElseThrow();

    }

    public void register(@NotNull final String key, @NotNull final SymbolLookup value) {

        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        this.entries.put(key, value);

    }

    public void unregister(final String key) {

        Preconditions.checkNotNull(key);
        this.entries.remove(key);

    }

}

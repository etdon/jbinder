package com.etdon.jbinder.function;

import com.etdon.commons.conditional.Preconditions;
import com.etdon.jbinder.SymbolLookupCache;
import com.etdon.jbinder.constant.DefaultLibrary;
import org.jetbrains.annotations.NotNull;

import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;

public class NativeCaller {

    private final Linker linker;
    private final SymbolLookupCache symbolLookupCache;

    public NativeCaller(@NotNull final Linker linker,
                        @NotNull final SymbolLookupCache symbolLookupCache) {

        Preconditions.checkNotNull(linker);
        Preconditions.checkNotNull(symbolLookupCache);
        this.linker = linker;
        this.symbolLookupCache = symbolLookupCache;

    }

    public Object call(@NotNull final NativeFunction nativeFunction) throws Throwable {

        Preconditions.checkNotNull(nativeFunction);
        final SymbolLookup symbolLookup = nativeFunction.getLibrary().equals(DefaultLibrary.STD)
                ? linker.defaultLookup()
                : this.symbolLookupCache.getOrThrow(nativeFunction.getLibrary());

        return nativeFunction.call(linker, symbolLookup);

    }

    public void callDiscarding(@NotNull final NativeFunction... nativeFunctions) throws Throwable {

        for (final NativeFunction nativeFunction : nativeFunctions)
            this.call(nativeFunction);

    }

}

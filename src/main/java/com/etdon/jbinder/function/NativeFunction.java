package com.etdon.jbinder.function;

import com.etdon.commons.conditional.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;

public abstract class NativeFunction {

    private final String library;
    private final String name;
    private final FunctionDescriptor signature;
    private MethodHandle methodHandle = null;

    public NativeFunction(@NotNull final String library,
                          @NotNull final String name,
                          @NotNull final FunctionDescriptor signature) {

        Preconditions.checkNotNull(library);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(signature);
        this.library = library;
        this.name = name;
        this.signature = signature;

    }

    @NotNull
    public MethodHandle obtainHandle(@NotNull final Linker linker, @NotNull final SymbolLookup symbolLookup) {

        if (this.methodHandle != null)
            return this.methodHandle;

        Preconditions.checkNotNull(linker);
        Preconditions.checkNotNull(symbolLookup);
        this.methodHandle = linker.downcallHandle(symbolLookup.find(this.name).orElseThrow(), this.signature);
        return this.methodHandle;

    }

    public abstract Object call(@NotNull final Linker linker, @NotNull final SymbolLookup symbolLookup) throws Throwable;

    @NotNull
    public String getLibrary() {

        return this.library;

    }

    @NotNull
    public String getName() {

        return this.name;

    }

    @NotNull
    public FunctionDescriptor getSignature() {

        return this.signature;

    }

    @Nullable
    public MethodHandle getMethodHandle() {

        return this.methodHandle;

    }

}

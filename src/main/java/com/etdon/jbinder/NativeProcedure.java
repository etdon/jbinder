package com.etdon.jbinder;

import com.etdon.commons.conditional.Preconditions;
import com.etdon.commons.util.Exceptional;
import org.jetbrains.annotations.NotNull;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Arrays;

public abstract class NativeProcedure {

    private final FunctionDescriptor signature;

    public NativeProcedure(@NotNull final FunctionDescriptor signature) {

        Preconditions.checkNotNull(signature);
        this.signature = signature;

    }

    @NotNull
    public MemorySegment upcallStub(@NotNull final Linker linker,
                                    @NotNull final Arena arena,
                                    @NotNull final Object owner,
                                    @NotNull final String methodName) throws Throwable {

        Preconditions.checkNotNull(linker);
        Preconditions.checkNotNull(arena);
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(methodName);
        final Class<?> ownerClass = owner.getClass();
        final Method foundMethod = Arrays.stream(ownerClass.getDeclaredMethods())
                .filter(method -> method.getName().equals(methodName))
                .findFirst()
                .orElseThrow();

        final MethodHandle methodHandle = MethodHandles.lookup().findVirtual(
                ownerClass,
                foundMethod.getName(),
                MethodType.methodType(foundMethod.getReturnType(), foundMethod.getParameterTypes())
        );

        if (methodHandle == null)
            throw Exceptional.of(RuntimeException.class, "Failed to bind. (Owner: {}, Method: {})", ownerClass.getSimpleName(), methodName);

        return this.upcallStub(linker, arena, methodHandle.bindTo(owner));

    }

    @NotNull
    public MemorySegment upcallStub(@NotNull final Linker linker, @NotNull final Arena arena, @NotNull final MethodHandle methodHandle) {

        Preconditions.checkNotNull(linker);
        Preconditions.checkNotNull(arena);
        Preconditions.checkNotNull(methodHandle);
        return linker.upcallStub(methodHandle, this.signature, arena);

    }

    @NotNull
    public FunctionDescriptor getSignature() {

        return this.signature;

    }

}

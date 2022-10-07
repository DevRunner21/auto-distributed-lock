package com.pawn.autodistributedlock;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DistributedLockConfig.class)
public @interface EnableDistributedLock {
}

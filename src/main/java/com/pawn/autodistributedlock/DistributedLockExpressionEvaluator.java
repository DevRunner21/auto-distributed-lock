package com.pawn.autodistributedlock;

import lombok.Value;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DistributedLockExpressionEvaluator extends CachedExpressionEvaluator {
    private final Map<ExpressionKey, Expression> cache = new ConcurrentHashMap<>(64);

    @Value
    static class TargetMethodRoot{
        Method method;
        Object[] args;
    }

    public String evaluate(String keyExpression, Method method, Object[] args) {

        TargetMethodRoot rootObject = new TargetMethodRoot(method, args);
        var methodKey = new AnnotatedElementKey(method, null);
        var context = new MethodBasedEvaluationContext(rootObject, method, args, getParameterNameDiscoverer());

        var expr = getExpression(cache, methodKey, keyExpression).getValue(context);
        return Objects.requireNonNull(expr).toString();
    }

}

package com.study.junit.Junit5_09_확장모델;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    // 1. final 로 선언 하는 방법
    // private static final long THRESHOLD = 1000L;

    private long THRESHOLD;

    // 2. 생성자의 인자로 초기화 하는 방법
    public FindSlowTestExtension(long THRESHOLD){
        this.THRESHOLD = THRESHOLD;
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        ExtensionContext.Store store = getStore(extensionContext);
        store.put("START TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        Method requiredTestMethod = extensionContext.getRequiredTestMethod();
        SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);
        String testMethodName = extensionContext.getRequiredTestMethod().getName();
        ExtensionContext.Store store = getStore(extensionContext);
        long start_time = store.remove("START TIME", long.class);
        long duration = System.currentTimeMillis() - start_time;

        // 1초 이상 걸리는 테스트의 경우 관련 알림 문구 표시
        if(duration > THRESHOLD && annotation == null) {
            System.out.printf("plz consider mark method [%s] with SlowTest. \n", testMethodName);
        }
    }

    private ExtensionContext.Store getStore(ExtensionContext extensionContext){
        String testClassName = extensionContext.getRequiredTestClass().getName();
        String testMethodName = extensionContext.getRequiredTestMethod().getName();
        return extensionContext.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
    }
}

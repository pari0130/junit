package com.study.junit.Junit5_09_확장모델;

import com.study.junit.Study;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(FindSlowTestExtension.class) // extension 을 이용 하는 방법 01 - 선언적
@Log4j2
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // order annotation 을 이용하여 순서를 정함
class StudyMulti {

    int value = 1;

    // 2. 생성자의 인자로 초기화 하는 방법
    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @FastTest
    @Order(2)
    void create_new_study1() throws InterruptedException {
        // Thread.sleep(1005L); // 1초 이상 걸리는 테스트를 확인하기 위한 Thread.sleep();
        log.info("FastTest 테스트");
        log.info(this);
        log.info(value++);
        Study study = new Study(value);
        assertThat(study.getLimit()).isGreaterThan(0);

    }

    @SlowTest
    @Order(1)
    void create_new_study2() throws InterruptedException {
        Thread.sleep(1005L);
        log.info("SlowTest 테스트");
        log.info("create1 : " + value++);
    }

    @Order(3)
    @DisplayName("반복 테스트 1")
    @ParameterizedTest(name = "{index} {displayName} message{0}") // 파라미터를 정의하는 어노테이션
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
    @NullAndEmptySource
        // null empty 인자에 대한 테스트
    void create_new_study1(String message){
        log.info(message);
    }

    @Order(4)
    @DisplayName("반복 테스트 1")
    @ParameterizedTest(name = "{index} {displayName} message{0}") // 파라미터를 정의하는 어노테이션
    @ValueSource(ints = {10, 20, 40})
    void create_new_study2(@ConvertWith(StudyConverter.class) Study study){
        log.info(study.getLimit());
    }

    // 인자 타입 컨버트
    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object o, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can Only convert to Study");
            return new Study(Integer.parseInt(o.toString()), "test");
        }
    }

    // 커스텀 어그리게이터
    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }
}

package com.study.junit.Junit5_08_junit_platform_properties;

import com.study.junit.Study;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
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

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Log4j2
// @TestInstance(TestInstance.Lifecycle.PER_CLASS) // test instance 를 달아줌으로서 1번만 생성되므로 Test class 에 static 이 필요없다.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // order annotation 을 이용하여 순서를 정함
class StudyProperties {

    int value = 1;

    @FastTest
    @Order(2)
    void create_new_study1(){
        log.info("FastTest 테스트");
        log.info(this);
        log.info(value++);
        Study study = new Study(value);
        assertThat(study.getLimit()).isGreaterThan(0);

    }

    @SlowTest
    @Order(1)
    void create_new_study2(){
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

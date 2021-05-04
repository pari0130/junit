package com.study.junit.Junit5_05_테스트반복하기2부;

import com.study.junit.domain.Study;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Log4j2
class StudyRepeat {

    @DisplayName("반복 테스트 1")
    @ParameterizedTest(name = "{index} {displayName} message{0}") // 파라미터를 정의하는 어노테이션
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
    @NullAndEmptySource // null empty 인자에 대한 테스트
    void create_new_study1(String message){
        log.info(message);
    }

    @DisplayName("반복 테스트 1")
    @ParameterizedTest(name = "{index} {displayName} message{0}") // 파라미터를 정의하는 어노테이션
    @ValueSource(ints = {10, 20, 40})
    void create_new_study2(@ConvertWith(StudyConverter.class) Study study){
        log.info(study.getLimit());
    }

    @DisplayName("반복 테스트 1")
    @ParameterizedTest(name = "{index} {displayName} message{0}") // 파라미터를 정의하는 어노테이션
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    void create_new_study3(int limit, String name){
        Study study = new Study(limit, name);
        log.info(study);
    }

    @DisplayName("반복 테스트 1")
    @ParameterizedTest(name = "{index} {displayName} message{0}") // 파라미터를 정의하는 어노테이션
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    void create_new_study4(ArgumentsAccessor argumentsAccessor){
        // 인자의 순서를 가저와서 주입 할 수 있다.
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        log.info(study);
    }

    @DisplayName("반복 테스트 1")
    @ParameterizedTest(name = "{index} {displayName} message{0}") // 파라미터를 정의하는 어노테이션
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    void create_new_study4(@AggregateWith(StudyAggregator.class) Study study){
        // 커스텀 aggregator
        log.info(study);
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
    static class StudyAggregator implements ArgumentsAggregator{
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }
}

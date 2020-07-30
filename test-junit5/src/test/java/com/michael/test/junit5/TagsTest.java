package com.michael.test.junit5;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagsTest {

    @Test
    @Tag("tagDemo")
    void test() {
        fail("Not yet implemented");
    }

    /**
     * 多个标签
     */
    @Test
    @Tag("test-tag")
    @Tag("tagDemo")
    void testTag1() {
        assertTrue(true);
    }

    /**
     * tags 组合
     */
    @Test
    @Tags({@Tag("tagDemo"),@Tag("test-tag")})
    void testTag3() {
        assertTrue(true);
    }

    /**
     * 获取标签内容
     * @param info
     */
    @Test
    @Tag("ShowInfo")
    void GetTagFromInfo(TestInfo info) {
        System.out.println(info.getTags());
    }

    /**
     * 自定义标签
     * @param info
     */
    @QiucaoTag
    void customTag(TestInfo info) {
        System.out.println(info.getTags());
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Tag("qiucao")
    @Test
    public @interface QiucaoTag {}

}

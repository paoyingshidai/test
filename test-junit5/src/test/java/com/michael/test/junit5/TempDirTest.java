package com.michael.test.junit5;

import com.michael.test.junit5.tool.ListWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://junit.org/junit5/docs/current/user-guide/#writing-tests-built-in-extensions
 * @author Michael
 */
public class TempDirTest {

    @Test
    void writeItemsToFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("test.txt");

        new ListWriter(file).write("a", "b", "c");

        assertEquals(singletonList("a,b,c"), Files.readAllLines(file));
    }

}

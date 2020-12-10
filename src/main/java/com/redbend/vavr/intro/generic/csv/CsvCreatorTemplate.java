package com.redbend.vavr.intro.generic.csv;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.BufferedWriter;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;

import io.vavr.CheckedFunction1;
import io.vavr.control.Try;

import static com.fasterxml.jackson.dataformat.csv.CsvGenerator.Feature.ESCAPE_QUOTE_CHAR_WITH_ESCAPE_CHAR;
import static com.fasterxml.jackson.dataformat.csv.CsvGenerator.Feature.STRICT_CHECK_FOR_QUOTING;
import static com.fasterxml.jackson.dataformat.csv.CsvSchema.DEFAULT_COLUMN_SEPARATOR;
import static io.vavr.collection.Stream.ofAll;
import static java.nio.file.Files.newBufferedWriter;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.stream.Collectors.toList;

/**
 * Generic template that allows to create CSV files for a DTO of type T.
 *
 * @param <T> - Type of the DTO for which the CSV file will be handled.
 */
public class CsvCreatorTemplate<T> {

    public static final String FILE_EXTENSION = ".csv";

    private static final CsvMapper MAPPER = new CsvMapper()
            .enable(ESCAPE_QUOTE_CHAR_WITH_ESCAPE_CHAR)
            .enable(STRICT_CHECK_FOR_QUOTING);

    private final ObjectWriter fileCreatingWriter;
    private final ObjectWriter rowAddingWriter;

    private CsvCreatorTemplate(Class<T> clazz) {
        CsvSchema csvCreateSchema = MAPPER.schemaFor(clazz)
                .withHeader()
                .withColumnSeparator(DEFAULT_COLUMN_SEPARATOR)
                .withHeader();
        fileCreatingWriter = MAPPER.writer(csvCreateSchema);

        CsvSchema csvAddRowSchema = MAPPER.schemaFor(clazz)
                .withColumnSeparator(DEFAULT_COLUMN_SEPARATOR);
        rowAddingWriter = MAPPER.writer(csvAddRowSchema);
    }

    /**
     * Factory method for the {@link CsvCreatorTemplate}. Will create an instance for the given DTO.
     *
     * @param clazz - class of the DTO.
     * @param <T>   - type of the DTO.
     * @return - {@link CsvCreatorTemplate}
     */
    public static <T> CsvCreatorTemplate<T> createFor(Class<T> clazz) {
        return new CsvCreatorTemplate<>(clazz);
    }

    /**
     * Creates a new file with added headers for the given DTO.
     *
     * @param path - path to the created file
     * @return - {@link Try} of {@link Void}
     */
    public Try<Void> createFileWithHeaders(Path path) {
        return Try.withResources(() -> newBufferedWriter(path, CREATE))
                .of(writeWithWriter(null, fileCreatingWriter));
    }

    /**
     * Adds a single row to the CSV file. The file has to be created first.
     *
     * @param input - DTO to be added to CSV.
     * @param path  - path of the CVS file.
     * @return - {@link Try} of {@link Void}.
     */
    public Try<Void> addRow(T input, Path path) {
        return Try.withResources(() -> newBufferedWriter(path, APPEND))
                .of(writeWithWriter(input, rowAddingWriter));
    }

    /**
     * Adds all of the element of the collection as rows to the CSV file.  The file has to be created first.
     *
     * @param inputs - {@link Collection} of DTO's to be added to CSV.
     * @param path   - path of the CVS file.
     * @return - {@link Try} of {@link Void}.
     */
    public Try<Void> addRows(Collection<T> inputs, Path path) {
        return Try.withResources(() -> newBufferedWriter(path, APPEND))
                .of(writeBatchWithWriter(inputs, rowAddingWriter));
    }

    /**
     * Adds all of the element of the stream as rows to the CSV file. Elements will be added in batches of 1000
     * elements. The file has to be created first.
     *
     * @param inputs - {@link Stream} of DTO's to be added to CSV.
     * @param path   - path of the CVS file.
     * @return - {@link Try} of {@link Void}.
     */
    public Stream<Try<Void>> addRows(Stream<T> inputs, Path path) {
        return ofAll(inputs)
                .sliding(1000, 1000)
                .map(stream -> stream.collect(toList()))
                .map(list -> addRows(list, path))
                .toJavaStream();
    }

    private CheckedFunction1<BufferedWriter, Void> writeWithWriter(T input, ObjectWriter rowAddingWriter) {
        return bw -> {
            rowAddingWriter.writeValue(bw, input);
            return null;
        };
    }

    private CheckedFunction1<BufferedWriter, Void> writeBatchWithWriter(Collection<T> input, ObjectWriter rowAddingWriter) {
        return bw -> {
            rowAddingWriter.writeValues(bw).writeAll(input);
            return null;
        };
    }
}

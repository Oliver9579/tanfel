package hu.course.model.sevice;

import hu.course.model.domain.Course;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;

    public DataApi(FileReader fileReader, DataParser dataParser) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
    }

    public List<Course> getData(String input) {
        return dataParser.parse(fileReader.read(input));
    }
}

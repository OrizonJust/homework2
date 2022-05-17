package edu.laverno.dao;

import edu.laverno.domain.Answer;
import edu.laverno.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TestDAOImpl implements TestDAO {

    private final String path;

    public TestDAOImpl(@Value("${path}") String path) {
        this.path = path;
    }

    @Override
    public List<Question> getQuestions() {
        try (BufferedReader br = new BufferedReader(new FileReader(path + "/question.csv"))) {
            String row;
            List<Question> list = new ArrayList<>();

            while ((row = br.readLine()) != null) {
                String[] data = row.split(",");

                list.add(new Question(data));
            }

            return list;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Answer getAnswer(Question question) {
        try (BufferedReader br = new BufferedReader(new FileReader(path + "/answer.csv"))) {
            String row;

            while ((row = br.readLine()) != null) {
                String[] data = row.split(",");

                if (Objects.equals(data[0], question.getName()))
                    return new Answer(data);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}

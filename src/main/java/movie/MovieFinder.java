package movie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieFinder {
    private String source;

    public MovieFinder(String source) {
        this.source = source;
    }

    List<String> findAll() {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            while (true) {
                String title = reader.readLine();
                if (title == null) {
                    break;
                }
                result.add(title);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

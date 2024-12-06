import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class csv_reader {
    public List<Adress> readCsv(String filePath) throws Exception {
        List<Adress> addresses = new ArrayList<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
            List<String[]> records = reader.readAll();
            for (String[] record : records.subList(1, records.size())) {
                Adress address = new Adress(record[0], record[1], record[2], record[3]);
                addresses.add(address);
            }
        }
        return addresses;
    }
}

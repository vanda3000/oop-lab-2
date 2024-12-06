import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class xml_reader {
    public List<Adress> readXml(String filePath) throws Exception {
        List<Adress> addresses = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);

            String city = null, street = null, house = null, floor = null;

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();

                    // Читаем атрибуты тега <item>
                    if ("item".equals(elementName)) {
                        city = reader.getAttributeValue(null, "city");
                        street = reader.getAttributeValue(null, "street");
                        house = reader.getAttributeValue(null, "house");
                        floor = reader.getAttributeValue(null, "floor");

                        // Создаем объект Address на основе атрибутов
                        addresses.add(new Adress(city, street, house, floor));
                    }
                }
            }
        }
        return addresses;
    }
}

import java.util.*;
import java.util.stream.Collectors;

public class AddressProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        csv_reader csvReader = new csv_reader();
        xml_reader xmlReader = new xml_reader();

        while (true) {
            System.out.println("Введите путь до файла-справочника или команду 'exit' для завершения:");
            String filePath = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(filePath)) {
                System.out.println("Завершение работы.");
                break;
            }

            long startTime = System.currentTimeMillis();
            List<Adress> addresses = new ArrayList<>();
            try {
                if (filePath.endsWith(".csv")) {
                    addresses = csvReader.readCsv(filePath);
                } else if (filePath.endsWith(".xml")) {
                    addresses = xmlReader.readXml(filePath);
                } else {
                    System.out.println("Неподдерживаемый формат файла. Попробуйте снова.");
                    continue;
                }

                if (addresses.isEmpty()) {
                    System.out.println("Файл пустой или не удалось обработать данные.");
                    continue;
                }

                // Найти дублирующиеся записи
                System.out.println("\nДублирующиеся записи (с количеством повторений):");
                findDuplicates(addresses).forEach((record, count) ->
                        System.out.println(record + ": " + count + " раз")
                );

                // Подсчитать этажность зданий
                System.out.println("\nКоличество зданий разной этажности в каждом городе:");
                countFloors(addresses).forEach((city, floors) ->
                        System.out.println(city + ": " + floors)
                );

                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("\nВремя обработки файла: " + elapsedTime + " мс.\n");
            } catch (Exception e) {
                System.out.println("Ошибка обработки файла: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static Map<String, Long> findDuplicates(List<Adress> addresses) {
        return addresses.stream()
                .map(Adress::toString)
                .collect(Collectors.groupingBy(record -> record, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static Map<String, Map<String, Integer>> countFloors(List<Adress> addresses) {
        Map<String, Map<String, Integer>> cityFloorCounts = new HashMap<>();
        for (Adress address : addresses) {
            String city = address.getCity();
            String floor = address.getFloor();
            cityFloorCounts.putIfAbsent(city, new HashMap<>());
            cityFloorCounts.get(city).put(floor, cityFloorCounts.get(city).getOrDefault(floor, 0) + 1);
        }
        return cityFloorCounts;
    }
}

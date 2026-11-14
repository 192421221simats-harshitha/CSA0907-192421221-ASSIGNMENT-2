
import java.util.*;

public class PharmacyMaps {

    static final int LOOKUPS = 100000;

    static String medicineCode(int i) {
        return String.format("MED%06d", i);
    }

    static Map<String, Integer> createMap(String type, int size) {
        Map<String, Integer> map;

        if (type.equals("HashMap")) {
            map = new HashMap<>();
        } else if (type.equals("TreeMap")) {
            map = new TreeMap<>();
        } else {
            map = new LinkedHashMap<>();
        }

        for (int i = 1; i <= size; i++) {
            map.put(medicineCode(i), 100 + i);
        }

        return map;
    }

    static long performLookups(
            Map<String, Integer> map,
            String[] searchCodes) {

        long start = System.nanoTime();
        long total = 0;

        for (String code : searchCodes) {
            Integer stock = map.get(code);

            if (stock != null) {
                total += stock;
            }
        }

        long end = System.nanoTime();

        // Use result so that the lookup work is meaningful.
        if (total == -1) {
            System.out.println(total);
        }

        return end - start;
    }

    static String codeOrderReport(Map<String, Integer> map) {
        StringBuilder report = new StringBuilder();

        // TreeMap already has sorted keys.
        // Other maps are copied into a TreeMap.
        Map<String, Integer> sortedMap = new TreeMap<>(map);

        for (Map.Entry<String, Integer> entry :
                sortedMap.entrySet()) {
            report.append(entry.getKey())
                  .append(" : ")
                  .append(entry.getValue())
                  .append("\n");
        }

        return report.toString();
    }

    static String insertionOrderReport(
            Map<String, Integer> map,
            List<String> insertionKeys) {

        StringBuilder report = new StringBuilder();

        if (map instanceof LinkedHashMap) {
            // LinkedHashMap naturally maintains insertion order.
            for (Map.Entry<String, Integer> entry :
                    map.entrySet()) {
                report.append(entry.getKey())
                      .append(" : ")
                      .append(entry.getValue())
                      .append("\n");
            }
        } else {
            // Other maps need the original insertion order list.
            for (String code : insertionKeys) {
                report.append(code)
                      .append(" : ")
                      .append(map.get(code))
                      .append("\n");
            }
        }

        return report.toString();
    }

    static void runExperiment(int size) {

        System.out.println("\n==================================");
        System.out.println("NUMBER OF MEDICINES: " + size);
        System.out.println("==================================");

        String[] types = {
            "HashMap", "TreeMap", "LinkedHashMap"
        };

        List<String> insertionKeys = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            insertionKeys.add(medicineCode(i));
        }

        String[] searchCodes = new String[LOOKUPS];

        Random random = new Random(10);

        for (int i = 0; i < LOOKUPS; i++) {
            int number = random.nextInt(size) + 1;
            searchCodes[i] = medicineCode(number);
        }

        for (String type : types) {

            Map<String, Integer> map =
                    createMap(type, size);

            long lookupTime =
                    performLookups(map, searchCodes);

            long startCode = System.nanoTime();
            String codeReport = codeOrderReport(map);
            long codeTime =
                    System.nanoTime() - startCode;

            long startInsertion = System.nanoTime();
            String insertionReport =
                    insertionOrderReport(map, insertionKeys);
            long insertionTime =
                    System.nanoTime() - startInsertion;

            System.out.println("\nMap: " + type);

            System.out.println("100,000 lookup time: "
                    + lookupTime + " ns");

            System.out.println("Code-order report time: "
                    + codeTime + " ns");

            System.out.println("Insertion-order report time: "
                    + insertionTime + " ns");

            System.out.println("Code-order first medicine: "
                    + codeReport.split("\n")[0]);

            System.out.println("Insertion-order first medicine: "
                    + insertionReport.split("\n")[0]);

            System.out.println("Total medicines: " + map.size());
        }
    }

    public static void main(String[] args) {

        System.out.println("HOSPITAL PHARMACY INVENTORY");
        System.out.println("MAP COLLECTION COMPARISON");

        runExperiment(1000);
        runExperiment(5000);
        runExperiment(10000);

        System.out.println("\nExperiment completed successfully.");
    }
}

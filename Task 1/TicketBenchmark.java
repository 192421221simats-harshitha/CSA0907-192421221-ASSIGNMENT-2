
public class TicketBenchmark {

    static volatile int resultLength;

    static String usingString(int tickets, int fields) {
        int totalLength = 0;

        for (int i = 0; i < tickets; i++) {
            String ticket = "";
            for (int j = 1; j <= fields; j++) {
                ticket = ticket + "Field" + j + " ";
            }
            totalLength += ticket.length();
        }
        resultLength = totalLength;
        return "String";
    }

    static String usingStringBuilder(int tickets, int fields) {
        int totalLength = 0;

        for (int i = 0; i < tickets; i++) {
            StringBuilder ticket = new StringBuilder();
            for (int j = 1; j <= fields; j++) {
                ticket.append("Field").append(j).append(" ");
            }
            totalLength += ticket.length();
        }
        resultLength = totalLength;
        return "StringBuilder";
    }

    static String usingStringBuffer(int tickets, int fields) {
        int totalLength = 0;

        for (int i = 0; i < tickets; i++) {
            StringBuffer ticket = new StringBuffer();
            for (int j = 1; j <= fields; j++) {
                ticket.append("Field").append(j).append(" ");
            }
            totalLength += ticket.length();
        }
        resultLength = totalLength;
        return "StringBuffer";
    }

    static void measure(String name, Runnable task) {
        long totalTime = 0;
        int runs = 5;

        for (int i = 0; i < runs; i++) {
            long start = System.nanoTime();
            task.run();
            long end = System.nanoTime();
            totalTime += (end - start);
        }

        System.out.println(name + " average time: "
                + (totalTime / runs) + " ns");
    }

    public static void main(String[] args) {

        int[][] workloads = {
            {1, 6},
            {10000, 50},
            {1000, 20}
        };

        String[] workloadNames = {
            "Short ticket",
            "Large workload",
            "Medium workload"
        };

        for (int i = 0; i < workloads.length; i++) {

            int tickets = workloads[i][0];
            int fields = workloads[i][1];

            System.out.println("\n" + workloadNames[i]);
            System.out.println("Tickets: " + tickets
                    + ", Fields per ticket: " + fields);

            measure("String", () ->
                    usingString(tickets, fields));

            measure("StringBuilder", () ->
                    usingStringBuilder(tickets, fields));

            measure("StringBuffer", () ->
                    usingStringBuffer(tickets, fields));
        }
    }
}

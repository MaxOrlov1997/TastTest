import java.util.*;
/*You are given a list of cities. Each direct connection between two cities has its transportation cost
(an integer bigger than 0). The goal is to find the paths of minimum cost between pairs of cities. Assume that the cost
of each path (which is the sum of costs of all direct connections belonging to this path) is at most 200000. The name
of a city is a string containing characters a,...,z and is at most 10 characters long.2)

Input

s [the number of tests <= 10]
n [the number of cities <= 10000]
NAME [city name]
p [the number of neighbors of city NAME]
nr cost [nr - index of a city connected to NAME (the index of the first city is 1)]
           [cost - the transportation cost]
r [the number of paths to find <= 100]
NAME1 NAME2 [NAME1 - source, NAME2 - destination]
[empty line separating the tests]

 this task chat gpt help me*/
public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tests = Integer.parseInt(scanner.nextLine()); // number of test

        while (tests-- > 0) {
            int n = Integer.parseInt(scanner.nextLine()); // number of cities
            Map<String, Integer> cityIndex = new HashMap<>();
            List<List<Edge>> graph = new ArrayList<>();

            // Information of cities and his neighbors
            for (int i = 0; i < n; i++) {
                String cityName = scanner.nextLine();
                cityIndex.put(cityName, i);
                int neighbors = Integer.parseInt(scanner.nextLine());
                graph.add(new ArrayList<>());

                for (int j = 0; j < neighbors; j++) {
                    String[] connection = scanner.nextLine().split(" ");
                    int neighborIndex = Integer.parseInt(connection[0]) - 1; // index neighbors
                    int cost = Integer.parseInt(connection[1]); // costs
                    graph.get(i).add(new Edge(neighborIndex, cost));
                }
            }

            int routes = Integer.parseInt(scanner.nextLine()); // number of route
            for (int i = 0; i < routes; i++) {
                String[] route = scanner.nextLine().split(" ");
                String source = route[0];
                String destination = route[1];

                int minCost = findMinimumCost(cityIndex.get(source), cityIndex.get(destination), graph, n);
                System.out.println(minCost);
            }

            if (scanner.hasNextLine()) scanner.nextLine(); // empty line
        }
    }

    // Algorithm minimal costs to route
    private static int findMinimumCost(int source, int destination, List<List<Edge>> graph, int n) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.cost));
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        pq.add(new Edge(source, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentNode = current.node;
            int currentCost = current.cost;

            if (currentNode == destination) return currentCost;
            if (currentCost > distances[currentNode]) continue;

            for (Edge neighbor : graph.get(currentNode)) {
                int newCost = currentCost + neighbor.cost;
                if (newCost < distances[neighbor.node]) {
                    distances[neighbor.node] = newCost;
                    pq.add(new Edge(neighbor.node, newCost));
                }
            }
        }

        return distances[destination];
    }

    // Class for representing a graph edge
    static class Edge {
        int node;
        int cost;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}

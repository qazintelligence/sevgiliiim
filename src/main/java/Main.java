import java.util.*;

public class Main {

    public static void main(String[] args) {
        Map<String, List<String>> adjacencyList = new HashMap<>();
        adjacencyList.put("A", Arrays.asList("C", "B", "D"));
        adjacencyList.put("B", Arrays.asList("A", "C", "E", "G"));
        adjacencyList.put("C", Arrays.asList("A", "B", "D"));
        adjacencyList.put("D", Arrays.asList("C", "A"));
        adjacencyList.put("E", Arrays.asList("G", "F", "B"));
        adjacencyList.put("F", Arrays.asList("G", "E"));
        adjacencyList.put("G", Arrays.asList("F", "B", "E"));

        Main graph = new Main(adjacencyList);

        List<String> dfsOrder = graph.DepthFirstSearch("A");
        List<String> bfsOrder = graph.BreadthFirstSearch("A");

        System.out.println("DFS: " + dfsOrder);
        System.out.println("BFS: " + bfsOrder);
    }
    private Map<String, List<String>> adjacencyList;

        public Main(Map<String, List<String>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public List<String> DepthFirstSearch(String start) {
        Stack<String> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            String curr = stack.pop();
            if (!visited.contains(curr)) {
                visited.add(curr);
                result.add(curr);
                List<String> neighbors = adjacencyList.get(curr);
                if (neighbors != null) {
                    for (String neighbor : neighbors) {
                        if (!visited.contains(neighbor)) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }
        return result;
    }

    public List<String> BreadthFirstSearch(String start) {
        Set<String> visited = new HashSet<>();
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            result.add(curr);
            List<String> neighbors = adjacencyList.get(curr);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
        return result;
    }


}
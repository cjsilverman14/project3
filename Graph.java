import java.util.*;
import java.io.*;

public class Graph {

    Map<String, City> vertexMap = new HashMap<String, City>(); 
    /* add an undirected edge */
    public void addEdge(String start, String end, int w) {
        City u = getVertex(start);
        City v = getVertex(end);
        u.nbs.add(new Road(u, v, w));
        v.nbs.add(new Road(v, u, w));
    }

    /* add a directed edge */
    public void addDirectedEdge(String start, String end, int w) {
        City u = getVertex(start);
        City v = getVertex(end);
        u.nbs.add(new Road(u, v, w));
    }

    // retrieve vertex associated with the given name
    public City getVertex(String name) {
        City v = vertexMap.get(name);
        if (v == null) {
            v = new City(name);
            vertexMap.put(name, v);
            System.out.println("New city " + name);
        }
        return v;
    }

    public void hasWarehouse(String name){
        City v = vertexMap.get(name);
        if(!v.hasWarehouse){
            v.hasWarehouse = true;
        }
    }

    public void setWarehouse(Warehouse w){
        City v = vertexMap.get(w.city);
        v.setWarehouse(w);
    }

    /* run a BFS form a given start vertex */
    public void bfs(City startVertex) {
        Deque<City> q = new ArrayDeque<City>();
        q.add(startVertex);
        startVertex.dist = 0;

        while (!q.isEmpty()) {
            City u = q.poll();
            System.out.println(u.name + " " +u.dist);
            for (Road e: u.nbs) {
                City v = e.v;
                if (v.dist == Integer.MAX_VALUE) {
                    q.add(v);
                    v.dist = u.dist+1;
                }
            }

        }
    }

    /* run a DFS from a given start vertex */
    public void dfs(City startVertex) {
        Deque<City> s = new ArrayDeque<City>();
        s.push(startVertex);
        startVertex.visited = true;
        while (!s.isEmpty()) {
            City u = s.pop();
            System.out.println(u.name);
            for (Road e: u.nbs) {
                City v = e.v;
                if (!v.visited) {
                    s.push(v);
                    v.visited = true;
                }
            }
        }
    }

    /* run a recursive DFS from a given vertex */
    public void recursiveDfs(City u) {
        u.visited = true;
        System.out.println(u.name);
        for (Road e: u.nbs) {
            City v = e.v;
            if (!v.visited) recursiveDfs(v);
        }

    }

    /* find the shortest path from the given start vertex */

    public void shortestPath(String startCity){
        this.shortestPath(this.getVertex(startCity));
    }

    public ArrayList<City> shortestPath(City startVertex) {

        reset();
        PriorityQueue<City> q = new PriorityQueue<City>();
        q.add(startVertex);
        startVertex.dist = 0;
        ArrayList<City> closestCities = new ArrayList<City>();
        while (!q.isEmpty()) {
            City u = q.poll();
            if (u.visited) continue;
            u.visited = true;
            closestCities.add(u);
            //System.out.println(u.name + " " + u.dist + " " + ((u.prev==null)?"":u.prev.name));
            for (Road e: u.nbs) {
                City v = e.v;
                if (v.dist > u.dist + e.w) {
                    q.remove(v);
                    v.dist = u.dist + e.w;
                    v.prev = u;
                    q.add(v);
                }
            }
        }
        return closestCities;

    }
    public void shortestPath(String startCity,int x){
        this.shortestPath(this.getVertex(startCity),x);
    }

    public void shortestPath(City startVertex,int x) {
        reset();
        PriorityQueue<City> q = new PriorityQueue<City>();
        q.add(startVertex);
        startVertex.dist = 0;
        startVertex.centerDist = 0;

        while (!q.isEmpty()) {
            City u = q.poll();
            if (u.visited) continue;
            u.visited = true;
            //System.out.println(u.name + " " + u.dist + " " + ((u.prev==null)?"":u.prev.name));
            for (Road e: u.nbs) {
                City v = e.v;
                if (v.dist > u.dist + e.w) {
                    q.remove(v);
                    v.dist = u.dist + e.w;
                    v.centerDist = u.dist + e.w;
                    v.prev = u;
                    q.add(v);
                }
            }
        }
    }

    public int returnDist(String s){
        City c = getVertex(s);
        return c.dist;
    }

    /* reset the parameters of all vertices */
    public void reset() {
        for (City v: vertexMap.values()) {
            v.dist = Integer.MAX_VALUE;
            v.visited = false;
            v.prev = null;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("graph1.txt"));

        Graph g = new Graph();

        while (in.hasNextLine()) {
            String[] line = in.nextLine().split(" ");
            if (line.length == 3) { // weighted graph
                int w = Integer.parseInt(line[2]);
                g.addEdge(line[0], line[1], w);
            } else {
                g.addEdge(line[0], line[1], 1);
            }
        }

        g.shortestPath(g.getVertex("A"));
    }
}
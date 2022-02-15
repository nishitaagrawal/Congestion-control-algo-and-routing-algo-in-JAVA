import java.io.*;
public class DistanceVectorRouting {
    static int graph[][]; //matrix to store initial routing table
    static int via[][]; //maintains hop
    static int rt[][]; // final routing table
    static int v; //vertex
    static int e; //edge
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the number of Vertices: ");
        v = Integer.parseInt(br.readLine());
        System.out.println("Please enter the number of Edges: ");
        e = Integer.parseInt(br.readLine());
        graph = new int[v][v];
        via = new int[v][v];
        rt = new int[v][v];
        for(int i = 0; i < v; i++)  //create initial routing table
            for(int j = 0; j < v; j++) {
                if(i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = 9999;
            }
        //enter data of the graph
        for(int i = 0; i < e; i++) {
            System.out.println("Please enter data for Edge " + (i + 1) + ":");
            System.out.print("Source: ");
            int s = Integer.parseInt(br.readLine());
            s--;
            System.out.print("Destination: ");
            int d = Integer.parseInt(br.readLine());
            d--;
            System.out.print("Cost: ");
            int c = Integer.parseInt(br.readLine());
            graph[s][d] = c;
            graph[d][s] = c;
        }
        dvr_calc_disp("The initial Routing Tables are: ");
        System.out.print("Please enter the Source Node for the edge whose cost has changed: ");
        int s = Integer.parseInt(br.readLine());
        s--;
        System.out.print("Please enter the Destination Node for the edge whose cost has changed: ");
        int d = Integer.parseInt(br.readLine());
        d--;
        System.out.print("Please enter the new cost: ");
        int c = Integer.parseInt(br.readLine());
        graph[s][d] = c;
        graph[d][s] = c;
        dvr_calc_disp("The new Routing Tables are: ");
    }
    static void dvr_calc_disp(String message) {
        System.out.println();
        init_tables();
        update_tables();
        System.out.println(message);
        print_tables();
        System.out.println();
    }
    static void update_table(int source) { // actual value of routing table is filled here it compares all possible values and updates table with shortest distance possible from node A to B
        for(int i = 0; i < v; i++) {
            if(graph[source][i] != 9999) {
                int dist = graph[source][i]; //updating distance based on source node
                for(int j = 0; j < v; j++) {
                    int inter_dist = rt[i][j]; //routing table distance is changed via shortest path
                    if(via[i][j] == source)
                        inter_dist = 9999;
                    if(dist + inter_dist < rt[source][j]) { //checks all possible paths from node A to B and selects the shortest one
                        rt[source][j] = dist + inter_dist;
                        via[source][j] = i;
                    }
                }
            }
        }
    }
    static void update_tables() {
        int k = 0;
        for(int i = 0; i < 4*v; i++) { //covering all possible combinations
            update_table(k); //source vertice se update
            k++;
            if(k == v)
                k = 0;
        }
    }
    static void init_tables() { //here cost value of each edge is added in routing table(matrix) and
        for(int i = 0; i < v; i++) {  //the node with no edge has infinite long cost assigned.
            for(int j = 0; j < v; j++) {
                if(i == j) {
                    rt[i][j] = 0;
                    via[i][j] = i;
                }
                else {
                    rt[i][j] = 9999;  //  if node in not in major diagnol then rt is assigned as infinie
                    via[i][j] = 100;  //and via is assigned as 100 (large value)
                }
            }
        }
    }
    static void print_tables() {  // print the routing tables
        for(int i = 0; i < v; i++) {
            System.out.println("Distance vector of routing table : "+i+1);
            for(int j = 0; j < v; j++) {
                System.out.print("Dist: " + rt[i][j] + "    ");
            }
            System.out.println();
        }
    }
}
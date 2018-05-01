import java.io.*;
import java.util.*;

public class GraphGenerator {
    private int[][] graph;
    private int v;//vertices
    private Random rand;
    private int MAX_RANGE_WEIGHT = 10;
    private int iterator;


    public GraphGenerator(int n, int i) {
        this.v = n;
        this.rand = new Random();
        this.iterator = i;
    }

    public void generateGraph(int selection) {
        switch (selection){
            case 0:
                this.graph = createMatrix(randomConnections(v));
                break;
            case 1:
                this.graph = createMatrix(createRandomGraph(v));
                break;
            case 2:
                this.graph = createMatrix(createCompleteGraph(v));
                break;
        }

        insertIntoFile(graph);
    }



    private void insertIntoFile(int[][] g) {
        StringBuilder graphString = new StringBuilder();
            for(int i = 0; i<v; i++){
                for (int j=0; j<v;j++){
                    graphString.append(g[i][j]).append("\t");
                }
                graphString.append("\n");
            }
            System.out.print(graphString.toString());

        try {
            String home = System.getProperty("user.home");
            File file = new File(home + File.separator + "Desktop" + File.separator + "G"+iterator+".txt");

            FileWriter fw = new FileWriter(file);
            fw.write(graphString.toString());
            fw.close();

        } catch (IOException iox) {
            iox.printStackTrace();
        }

    }

    public Set<Pair>  randomConnections(int v){
        Set<Pair> pairs = new HashSet<>();

        //generate connections i && i+1
        for(int i=0;i<v; i++){
            pairs.add(new Pair(i,(i+1)%v));
        }

        createOtherConnections(pairs);

       StringBuilder builder = new StringBuilder();
        for (Pair pair : pairs) {
            builder.append("[").append(pair.getPrev()).append(", ").append(pair.getNext()).append("]");
        }

        System.out.println(builder.toString());
        return pairs;
    }

    private Set<Pair> createRandomGraph(int v){ //tworzy dowolny graf
        Random rand = new Random();
        Set<Pair> pairsSet = new HashSet<>();

        int[] verticesToInsert = new int[v];
        ArrayList<Integer> insertedVertices = new ArrayList<>();

       for(int i=0; i<v; i++){
           verticesToInsert[i]=i;
       }

       //losujemy wierzchołek początkowy - indeks
       int startVertex = rand.nextInt(v);
       insertedVertices.add(verticesToInsert[startVertex]);

       while(insertedVertices.size()!=verticesToInsert.length){
            int endVertex = rand.nextInt(v);
            if(endVertex == startVertex) {
               continue;
            }
            pairsSet.add(new Pair(startVertex,endVertex));

            if(!insertedVertices.contains(verticesToInsert[endVertex])){
                insertedVertices.add(verticesToInsert[endVertex]);
            }

            startVertex = endVertex;
       }

       return pairsSet;
    }

    private Set<Pair> createCompleteGraph(int v){
        Set<Pair> pairs = new HashSet<>();

        int j = v-2;
        for(int i = v-1; i>=0; i--){
            while(j>=0){
                if(j==i) {
                    j--;
                    continue;
                }
                pairs.add(new Pair(i,j));
                j--;
            }
            j=i;
        }

        return pairs;
    }

    private void createOtherConnections(Set<Pair> pairs){
        if(v>2) {
            for (int i = 0; i < v; i++) {
                int howManyConnections = rand.nextInt(v-2);
                for (int j = 0; j < howManyConnections; j++) {
                    boolean ok = false;
                    while (!ok) {
                        int connectedPoint = rand.nextInt(v);

                        //bez petli wlasnych
                        if (connectedPoint != i && connectedPoint != i + 1) {
                            if(connectedPoint>v) connectedPoint = connectedPoint%v;
                            pairs.add(new Pair(i, connectedPoint));
                            ok = true;
                        }
                    }
                }
            }
        } else if(v == 2){
            pairs.add(new Pair(0,1));
            pairs.add(new Pair(1,0));
        } else pairs.add(new Pair(0,0));
    }

    private int[][] createMatrix(Set<Pair> pairs){
        int[][] matrix = new int[v][v];
        for (Pair pair: pairs) {
            int weight = rand.nextInt(MAX_RANGE_WEIGHT)+1;
            //dla grafu nieskierowanego
            matrix[pair.getPrev()][pair.getNext()] = weight;
            matrix[pair.getNext()][pair.getPrev()] = weight;
        }

        return matrix;
    }

    private void countEdges(int[][] graph){
        int edges = 0;
        System.out.println("ILOSC KRAWEDZI WYNOSI" + edges);
    }

    private class Pair{
        int prev;
        int next;

        private Pair(int p, int n){
            setNext(n);
            setPrev(p);
        }

        private void setPrev(int p){
            this.prev = p;
        }

        private void setNext(int n){
            this.next = n;
        }

        private int getPrev(){
            return this.prev;
        }

        private int getNext(){
            return this.next;
        }
    }

}


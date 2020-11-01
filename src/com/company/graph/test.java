package com.company.graph;


import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


import java.util.HashMap;
public class test {


    private static class EdgeWeightedGraphTestCase {
        EdgeWeightedGraph graph;
        int v;
        int w;


        public EdgeWeightedGraph getGraph() {
            return graph;
        }

        public int getV() {
            return v;
        }

        public int getW() {
            return w;
        }

        public EdgeWeightedGraphTestCase(EdgeWeightedGraph graph, int v , int w) {
            this.graph = graph;
            this.v = v;
            this.w = w;
        }

        public EdgeWeightedGraphTestCase(String path) {
            File file = new File(path);
            BufferedReader reader = null;
            String tempString = null;


            int v = 0, w = 0, line = 1;
            EdgeWeightedGraph G = null;
            Exception err = null;
            try {
                System.out.println("开始读取：");
                reader = new BufferedReader(new FileReader(file));
                while ((tempString = reader.readLine()) != null) {
                    tempString = tempString.trim();
                    if (line == 1) {
                        v = Integer.parseInt(tempString);
                        System.out.println("读取到顶点数: " + v);
                    } else if (line == 2) {
                        w = Integer.parseInt(tempString);
                        System.out.println("读取到边数: " + w);
                    }
                    else {
                        if (G == null) {
                            G = new EdgeWeightedGraph(v);
                            System.out.println("创建图， 有" + G.getV() + "个顶点");
                        }
                        String[] arr = tempString.split("\\s");
                        if (arr.length >= 3) {
                            int edgeV = Integer.parseInt(arr[0]), edgeW = Integer.parseInt(arr[1]);
                            double edgeWeight = Double.parseDouble(arr[2]);
                            System.out.println("加入边" + edgeV + "->" + edgeW + "(" + edgeWeight + ")");
                            G.addEdge(new Edge(edgeV, edgeW, edgeWeight));
                        }
                    }

                    line += 1;
                }
                reader.close();
            } catch (IOException e) {
                System.out.println(e);
            }

            if (err != null) {
                System.out.println(err.toString());
            } else {
                System.out.println(String.format("读取成功， %d个顶点， %d条边", v, w));
                this.graph = G;
                this.v = v;
                this.w = w;
            }

        }
    }
    public static void minTreeTest() {

        System.out.println("run minTree test");
        EdgeWeightedGraph G = (new EdgeWeightedGraphTestCase("src/com/company/graph/EWG.txt")).getGraph();


        Edge[] edges = G.minTree();
        double weights = 0;
        for (Edge e: edges) {
            System.out.println("最小生成树的一条边:" + e.either() + "->" + e.other(e.either()) + "(" + e.getWeight() + ")") ;
            weights += e.getWeight();
        }
        System.out.println("----------------------");
        System.out.println("最小生成树路径总和为:" + weights);
    }


    public static void bellmanTest() {
        System.out.println("run bellman test");

        EdgeWeightedGraphTestCase GCase = new EdgeWeightedGraphTestCase("src/com/company/graph/Bellman.txt");
        EdgeWeightedGraph G = GCase.getGraph();
        int v = GCase.getV();
        int w = GCase.getW();


        try {
            System.out.print("求取起点到其它所有顶点之间的最短距离： ");
            double[] stortestPaths = G.bellmanShortestPath();

            for(double val: stortestPaths) {
                System.out.print(" " + val + " ");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
    public static void main(String[] args) {

        boolean runMinTreeTest = false;
        boolean runBellmanTest = true;

        if (runMinTreeTest) {
            minTreeTest();
        }

        if (runBellmanTest) {
            bellmanTest();
        }
    }
}


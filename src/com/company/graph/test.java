package com.company.graph;

import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class test {
    public static void minTreeTest() {
        File file = new File( "src/com/company/graph/EWG.txt");
        System.out.println("hello world");

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

        if (err != null) return;
        System.out.println(String.format("读取成功， %d个顶点， %d条边", v, w));

        Edge[] edges = G.minTree();
        double weights = 0;
        for (Edge e: edges) {
            System.out.println("最小生成树的一条边:" + e.either() + "->" + e.other(e.either()) + "(" + e.getWeight() + ")") ;
            weights += e.getWeight();
        }
        System.out.println("----------------------");
        System.out.println("最小生成树路径总和为:" + weights);
    }
    public static void main(String[] args) {
        minTreeTest();
    }
}


package principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import modelo.Node;


public class BuscaLargura 
{
    private final Map<String, Node> grafo = new LinkedHashMap<>();
    
    public void preencherGrafo()
    {
        Node[] nos = {
                new Node("A"), new Node("B"), new Node("C"), new Node("D"),
                new Node("E"), new Node("F"), new Node("G"), new Node("H"),
                new Node("I"), new Node("J"), new Node("K"), new Node("L"),
                new Node("M"), new Node("N"), new Node("O"), new Node("P"),
                new Node("Q"), new Node("R")
            };


        Node[][] filhos = {{new Node("B")}, //A
                            {new Node ("C"), new Node ("A")}, //B
                            {new Node ("D"), new Node ("F"), new Node ("B")}, //C
                            {new Node ("C"), new Node ("G")},// D
                            {new Node ("F")}, //E
                            {new Node ("C"), new Node ("G"), new Node ("E")},//F
                            {new Node ("D"), new Node ("H"), new Node ("F"), new Node ("I")},// G
                            {new Node ("J"), new Node ("L"), new Node ("G")},// H
                            {new Node ("G"), new Node ("M"), new Node ("K")},// I
                            {new Node ("H"), new Node ("L")},// J
                            {new Node ("I"), new Node ("O")},// K
                            {new Node ("N"), new Node ("J"), new Node ("H")},// L
                            {new Node ("P"), new Node ("Q"), new Node ("I")},// M
                            {new Node("L")}, //N
                            {new Node ("K"), new Node ("Q"), new Node ("R")},// O
                            {new Node("M")}, //P
                            {new Node ("O"), new Node ("M")},// Q
                            {new Node("O")}, //R
                            
                            
                
        };
        
        //Insere os nós
        for (Node no : nos) 
        {
            grafo.put(no.getNo(), no);      
        }
        
        //Insere os Filhos
        for (int i = 0; i < nos.length; i++) 
        {
            Node node = grafo.get(nos[i].getNo());  
            List<Node> filhosDoNo = new ArrayList<>(Arrays.asList(filhos[i]));  
            node.setFilhos(filhosDoNo);  
        }
        
       
    }
    
   public void imprimirGrafo()
   {
       for(Map.Entry <String, Node> entry : grafo.entrySet())
       {
           System.out.println("No: " + entry.getKey());
           
           for (Node node : entry.getValue().getFilhos())
           {
               System.out.println("Filho: " + node.getNo());
           }
           
           System.out.println("");
       }
   }
   public boolean verificarNosVisitados(Set<Node> nosVisitados, Node node)
   {
       return nosVisitados.contains(node);
   }
   
    public List<Node> buscaLargura(Node origem, Node destino)
    {
        
        Queue<Node> fila = new LinkedList<>();
        Set<Node> nosVisitados = new HashSet<>();
        Map<Node, Node> pais = new HashMap<>();

        fila.add(origem);
        nosVisitados.add(origem);

        while(!fila.isEmpty())
        {
            Node nodeAtual = fila.poll();  
            System.out.println("No: " + nodeAtual.getNo());
            
            for (Node n : nodeAtual.getFilhos())
            {
                System.out.println("Filho: " + n.getNo());
            }

            for (Node nodeAux : nodeAtual.getFilhos())
            {
                if(!verificarNosVisitados(nosVisitados, nodeAux))
                {
                    if(nodeAux.equals(destino))
                    {
                        List<Node> caminhoPercorrido = new ArrayList<>();

                        for (Node n = nodeAux; n != null; n = pais.get(n))
                        {
                            caminhoPercorrido.add(n);
                        }

                        Collections.reverse(caminhoPercorrido);

                        return caminhoPercorrido;
                    }
                    else
                    {
                        fila.add(retornarNode(nodeAux.getNo()));
                        nosVisitados.add(nodeAux);
                        pais.put(nodeAux, nodeAtual);
                    }

                }

            }
        }            
      
        return null;
    }
    
    public Node retornarNode(String no)
    {
        return grafo.get(no);
    }
    
    public void imprimirCaminho(List<Node> caminho)
    {
        for (Node node : caminho) 
        {
            System.out.printf(node.getNo());
            System.out.printf("->");
        }
    }
    
    public void menu()
    {
        preencherGrafo();
        
        Scanner teclado = new Scanner(System.in);
   
        System.out.printf("Digite o no de origem: ");
        Node origem = retornarNode(teclado.nextLine());
        System.out.println("");
        
        System.out.printf("Digite o no de destino: ");
        Node destino = retornarNode(teclado.nextLine());
        
        if(origem.equals(destino))
        {
            System.out.println("Distancia: 0");
        }
        else
        {
            List<Node> caminho = buscaLargura(origem, destino);

          if(caminho == null)
          {
              System.out.println("Lista Vazia! Caminho nao encontrado");
          }
          else
          {
              imprimirCaminho(caminho);
          }

        }
        
    }
}

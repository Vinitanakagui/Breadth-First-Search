package modelo;

import java.util.List;


public class Node 
{
    private String no;
    private List<Node> filhos;

    public Node() {
    }

    public Node(String no) {
        this.no = no;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public List<Node> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<Node> filhos) {
        this.filhos = filhos;
    }
    
    

 
}

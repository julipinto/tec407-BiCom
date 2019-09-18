package util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Aeroporto;
import model.Passagem;

/**
 *
 * @author Juliana
 */
public class MapVerticesEArestas implements Serializable {
    private static final long serialVersionUID = 10L;
    private final Map<Integer, Vertice> vertices;
    private final Map<String, Aresta> arestas;

    public MapVerticesEArestas() {
        vertices = new HashMap<>();
        arestas = new HashMap<>();
        initializeVertices();
    }
    
    private void initializeVertices(){
        for (Settings.EnumAeroportos a : Settings.EnumAeroportos.values()) {
            System.out.println("Gravando estados: " + a.getEstado());
            vertices.put(a.getId(), new Vertice(new Aeroporto(a.getId(), a.getEstado(), a.getNome())));
        }          
    }

    public void merge(Map<Integer, Vertice> vertices1, Map<String, Aresta> arestas1,
                      Map<Integer, Vertice> vertices2, Map<String, Aresta> arestas2) {
        vertices.putAll(vertices1);
        vertices.putAll(vertices2);
        arestas.putAll(arestas1);
        arestas.putAll(arestas2);
    }
    
    void initializeRotas(List<Routs> rotas) {
        rotas.forEach((rota) -> {
            Vertice origem = vertices.get(rota.getOrigem());
            Vertice destino = vertices.get(rota.getDestino());
            arestas.put(generateIdAresta(origem, destino),
                    new Aresta(origem, destino, new Passagem(rota.getPreco(), rota.getCompanhia())));
        });
    }
    
    public String generateIdAresta(Vertice origem, Vertice destino){
        return origem.getAeroporto().getEstado() + ":" + destino.getAeroporto().getEstado();
    }

    public Map<String, Aresta> getArestas() {
        return arestas;
    }

    public Map<Integer, Vertice> getVertices() {
        return vertices;
    }
    
    public List<Vertice> getAeroportos(){
        return (List<Vertice>) vertices.values();
    }
    
    public Vertice getVerticeByEstado(String chave){
        Set<Map.Entry<Integer, Vertice>> entrySet = vertices.entrySet();
        for (Map.Entry<Integer, Vertice> entry : entrySet) {
            System.out.println("Buscando na hashmap: \n" + 
                    "Valor do nome do Aeroporto: " +  entry.getValue().getAeroporto().getNome() +
                    "\nValor do estado desse aeroporto: " + entry.getValue().getAeroporto().getEstado());
            
            if(entry.getValue().getAeroporto().getEstado().equalsIgnoreCase(chave)){
                return entry.getValue();
            }
        }
        return null;
    }
    
    public boolean hasAresta(String origem){
        Vertice o = getVerticeByEstado(origem);
        return o.hasAresta();  
    }
    
    public List<Aresta> getArestas(String chaveVertice){
        Vertice v = getVerticeByEstado(chaveVertice);
        return v.getArestas();
    }

    
}

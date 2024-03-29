package model;

import java.io.Serializable;
import java.util.List;
import util.Aresta;

/**
 *
 * @author Juliana
 */
public class Caminhos implements Serializable{
    private static final long serialVersionUID = 2L;
    private final List<List<Aresta>> possibilidades;

    public Caminhos(List<List<Aresta>> possibilidades) {
        this.possibilidades = possibilidades;
    }

    public List<List<Aresta>> getPossibilidades() {
        return possibilidades;
    }
}

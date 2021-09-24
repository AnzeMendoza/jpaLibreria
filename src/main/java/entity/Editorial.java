package entity;

import javax.persistence.*;

@Entity
public class Editorial {
    @Id // notación para el primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para que sea autoincremental
    private Integer id;
    private String nombre;
    private Boolean alta;

    public Editorial(Integer id, String nombre, Boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }

    public Editorial(String nombre, Boolean alta) {
        this.nombre = nombre;
        this.alta = alta;
    }

    public Editorial() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return "Editorial{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", alta=" + alta +
                '}' + "\n";
    }
}

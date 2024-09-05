package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Builder
@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private int dni;

    @Column(name = "nombre")
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio" )
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private List<Factura> facturas =  new ArrayList<Factura>();

}

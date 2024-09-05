package org.example;

import entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("en marcha Alberto");

        try {
            // Persistir una nueva entidad Person
            entityManager.getTransaction().begin();

            Categoria perecedero = Categoria.builder()
                    .denominacion("Perecedero")
                    .build();

            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();

            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();

            Articulo leche = Articulo.builder()
                    .cantidad(2)
                    .precio(100)
                    .denominacion("Leche")
                    .build();


            Articulo detergente = Articulo.builder()
                    .cantidad(5)
                    .precio(500)
                    .denominacion("Detergente")
                    .build();


            lacteos.getArticulos().add(leche);
            perecedero.getArticulos().add(leche);
            detergente.getCategorias().add(limpieza);
            limpieza.getArticulos().add(detergente);

            Factura fact1 = Factura.builder()
                    .fecha("02/09/2024")
                    .build();

            Cliente cliente = Cliente.builder()
                    .nombre("Julian")
                    .apellido("Olivera Balls")
                    .dni(45024273)
                    .build();

            Domicilio domicilio = Domicilio.builder()
                    .numero(1761)
                    .nombreCalle("Juan B Justo")
                    .build();

            cliente.setDomicilio(domicilio);

            fact1.setCliente(cliente);


            DetalleFactura linea1 = DetalleFactura.builder()
                    .build();

            linea1.setArticulo(leche);
            linea1.setCantidad(4);
            linea1.setSubtotal(450);
            linea1.setFactura(fact1);



            DetalleFactura linea2 = DetalleFactura.builder()
                    .build();


            linea2.setArticulo(detergente);
            linea2.setCantidad(1);
            linea2.setSubtotal(50);
            linea2.setFactura(fact1);

            List<DetalleFactura> detalles = new ArrayList<>();

            detalles.add(linea2);
            detalles.add(linea1);


            fact1.setDetalles(detalles);

            entityManager.persist(fact1);


            entityManager.flush();
            entityManager.getTransaction().commit();


            // Consultar y mostrar la entidad persistida


        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Articulo");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}

package utilidades;

import com.example.practica_jpa.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class HibernateListar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Cliente> clientes = em.createQuery("select c from Cliente c",
                Cliente.class).getResultList();
        clientes.forEach(System.out::println);
        em.close();
    }

}


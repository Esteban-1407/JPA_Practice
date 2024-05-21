package utilidades;
import com.example.practica_jpa.Cliente;
import jakarta.persistence.EntityManager;
import java.util.Scanner;

public class HibernateEditar {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);


        System.out.println("Ingrese el id del cliente a editar:");
        Long id = s.nextLong();
        s.nextLine();


        System.out.println("Ingrese el nuevo nombre del cliente:");
        String nuevoNombre = s.nextLine();

        System.out.println("Ingrese el nuevo apellido del cliente:");
        String nuevoApellido = s.nextLine();

        System.out.println("Ingrese la nueva forma de pago del cliente:");
        String nuevaFormaPago = s.nextLine();


        EntityManager em = JpaUtil.getEntityManager();

        try {
            // Encontrar el cliente por ID
            Cliente cliente = ((EntityManager) em).find(Cliente.class, id);

            // Verificar si el cliente existe
            if (cliente != null) {
                em.getTransaction().begin();


                cliente.setNombre(nuevoNombre);
                cliente.setApellido(nuevoApellido);
                cliente.setFormaPago(nuevaFormaPago);

                em.merge(cliente);
                em.getTransaction().commit();

                System.out.println("Cliente actualizado exitosamente.");
            } else {
                System.out.println("Cliente con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {

            em.close();
        }
    }
}


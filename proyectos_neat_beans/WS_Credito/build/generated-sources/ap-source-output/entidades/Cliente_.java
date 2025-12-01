package entidades;

import entidades.Operacion;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2025-12-01T13:18:59")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, BigDecimal> montoMax;
    public static volatile SingularAttribute<Cliente, BigDecimal> montoDisponible;
    public static volatile ListAttribute<Cliente, Operacion> operacionList;
    public static volatile SingularAttribute<Cliente, String> tel;
    public static volatile SingularAttribute<Cliente, BigDecimal> montoUtilizado;
    public static volatile SingularAttribute<Cliente, Integer> id;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, String> email;
    public static volatile SingularAttribute<Cliente, String> status;

}
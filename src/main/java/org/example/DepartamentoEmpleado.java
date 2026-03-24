package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DepartamentoEmpleado {
    public static void main(String[] args) {

        try(Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword());
            Statement stmt = conn.createStatement()) {
            String eliminarTablas = "DROP TABLE departamento CASCADE CONSTRAINTS";
            String eliminar2 = "DROP TABLE empleado CASCADE CONSTRAINTS";
            stmt.executeUpdate(eliminarTablas);
            stmt.executeUpdate(eliminar2);

            String sql = "CREATE TABLE departamento(" +
                            "id VARCHAR(10) PRIMARY KEY, " +
                            "nombre VARCHAR(25) NOT NULL)";
            stmt.executeUpdate(sql);

            System.out.println("Primera tabla insertada");

            String empleado = "CREATE TABLE empleado(" +
                                "id_Empleado VARCHAR(10) PRIMARY KEY," +
                                " nombre VARCHAR(25) NOT NULL, " +
                                "salario NUMBER(5) NOT NULL, " +
                                "departamento_id VARCHAR(10), " +
                                "FOREIGN KEY (DEPARTAMENTO_ID) REFERENCES departamento(id))";
            stmt.executeUpdate(empleado);

            System.out.println("Tablas creadas");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
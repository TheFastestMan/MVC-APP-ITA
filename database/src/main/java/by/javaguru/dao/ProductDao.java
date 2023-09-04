package by.javaguru.dao;

import by.javaguru.entity.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static final String SAVE_SQL = """
            INSERT INTO flight (
            flight_no, 
            departure_date,
            departure_airport_code, 
            arrival_date, 
            arrival_airport_code,
            aircraft_id,
            status
            )
            VALUES(?,?,?,?,?,?,?);
            """;
    private static final String DELETE_SQL = """
            DELETE FROM flight WHERE
            id = ?;
            """;
}
// deleteProduct, saveProduct, createNewProduct
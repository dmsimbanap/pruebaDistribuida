package com.programacion.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import javax.sql.DataSource;

import org.apache.hadoop.mapred.lib.db.DBInputFormat;

@ApplicationScoped
public class DatabaseConfig {

   
try  {
                
        DBInputFormat dbi = new DBI("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
        Handle h = dbi.open(); 
        List<LargeCities> list = h.createQuery("SELECT *, name FROM nooks").map(new LargeCitiesMapper()).list(); 
        
        for (LargeCities a : list) {
                System.out.println("Rank: " + a.getRank() + " Name: " + a.getName());
        }
        
        h.close();
}
catch (Exception e) {
    e.printStackTrace();
}
}

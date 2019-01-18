package com.acaisoft.orientdbdemo.demo;

import com.acaisoft.orientdbdemo.demo.config.SchemaCreator;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class OrientDbTest {
    private static final String CONNECTION_URL = "plocal:/home/michaldargiewicz/Apps/orientdb-3.0.13/databases/groups";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private OrientGraphNoTx graph;

    @BeforeClass
    public void init() {
        //DO THIS ONLY IF IT'S THE FIRST TIME YOU CREATE SCHEMA
//        SchemaCreator.create();

        this.graph = new OrientGraphNoTx(CONNECTION_URL, USERNAME, PASSWORD);
    }

    @Test
    public void checkSize() {
        long size = graph.countVertices();
        assert size == 100000;
    }
}

package com.acaisoft.orientdbdemo.demo.config;

import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

public class SchemaCreator {
    private static final String CONNECTION_URL = "plocal:/home/michaldargiewicz/Apps/orientdb-3.0.13/databases/groups";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static void create() {
        OrientGraphNoTx graph = new OrientGraphNoTx(CONNECTION_URL, USERNAME, PASSWORD);
        OrientVertexType organizationType = graph.createVertexType("Organization");
        organizationType.createProperty("organizationId", OType.STRING);
        organizationType.createProperty("name", OType.STRING);
        organizationType.createProperty("sum", OType.INTEGER);

        OrientVertexType groupType = graph.createVertexType("Group");
        groupType.createProperty("groupId", OType.STRING);
        groupType.createProperty("name", OType.STRING);
        groupType.createProperty("sum", OType.INTEGER);

        Vertex vOrganization = graph.addVertex("class:Organization");
        vOrganization.setProperty("organizationId", "1");
        vOrganization.setProperty("name", "bialystok");
        vOrganization.setProperty("sum", 32);
        for (int i = 0; i < 100000; i++) {
            Vertex vGroup = graph.addVertex("class:Group");
            vGroup.setProperty("groupId", Integer.toString(i));
            vGroup.setProperty("name", RandomStringUtils.random(10));
            vGroup.setProperty("sum", RandomUtils.nextInt());
            graph.addEdge(null, vOrganization, vGroup, "has");
        }
    }
}

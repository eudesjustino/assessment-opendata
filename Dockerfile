FROM jboss/wildfly:latest
ADD target/assessment-opendata-0.0.1-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/assessment-opendata-0.0.1-SNAPSHOT.war
# bold-risk-pricing-api

Risk & Pricing Engine REST api in spring-boot.

# Prerequisite

## Mumba client
The dependencies of mambu are added in this project, to clean the dependencies we need from mambu on
projects that need it.

### Download

- https://raw.githubusercontent.com/mambu-gmbh/Mambu-APIs-Java/master/build/Mambu-APIs-Java-4.3-bin.jar
- https://raw.githubusercontent.com/mambu-gmbh/Mambu-APIs-Java/master/lib/mambu-models-V4.3.jar

### Install in maven the mambu jars

```
$ mvn install:install-file -Dfile=Mambu-APIs-Java-4.3-bin.jar -DgroupId=com.mambu -DartifactId=mambuapis -Dversion=4.3 -Dpackaging=jar
$ mvn install:install-file -Dfile=mambu-models-V4.3.jar -DgroupId=com.mambu -DartifactId=mambumodels -Dversion=4.3 -Dpackaging=jar
$ mvn install:install-file -Dfile=tmp/pom.xml -DpomFile=tmp/pom.xml -DgroupId=com.bold.mambu -DartifactId=bold-mambu-api -Dversion=1.0-SNAPSHOT -Dpackaging=pom
`````


## Build

`$ mvn clean package`

## Run

Start the API:

`$ java -jar target/bold-risk-pricing-api-*.jar`

### IDE

Just run or debug:

`src/main/java/com.bold.risk.api/Application.java`

### Docker

Build:

`$ sudo docker build -t bold/riskmanager:SNAPSHOT .`

Run:

`$ sudo docker run -it -d -p 8080:8080 bold/riskmanager:SNAPSHOT`

Optional environment settings:

`$ sudo docker run -it -d -p 8080:8080 bold/riskmanager:SNAPSHOT -e MAMBU_URL='boldabnamro.sandbox.mambu.com' -e MAMBU_USERNAME='boldapp' -e MAMBU_PASSWORD='Xz!63OV*' -e MAMBU_LOAD_PRODUCT_TYPES='EPT_TL,INV_TL,VHL_TL'`

Open browser to [http://localhost:8080](http://localhost:8080)

#### Docker Swarm Mode

Run with 2 replicas for rolling update:

`$ sudo docker service create --name riskmanager -replicas 2 -p 8080:8080 --update-delay 15s bold/riskmanager:SNAPSHOT`

Open browser to [http://localhost:8080](http://localhost:8080)

#### Docker Swarm Visualizer

Run:
via Docker
`$ docker run -it -d -p 5000:8080 -v /var/run/docker.sock:/var/run/docker.sock manomarks/visualizer`

Via Jar

Java -jar bold-risk-pricing-api-1.0-SNAPSHOT.jar

Open browser to [http://localhost:5000](http://localhost:5000)

## Development

Make sure lombok is installed to make your IDE happy!

- In intelliJ install the lombok plugin and enable annotation processing.
- In Eclipse open up the lombok jar found in your maven repository under
  org.projectlombok. This will ask for your eclipse dir to install the
  lombok agent.

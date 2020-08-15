SERVICE := webflux-cassandra-demo

test:
	mvn clean test

verify:
	mvn clean verify sonar:sonar

package:
	mvn clean package -Dmaven.test.skip=true

run:
	java \
		-Dloader.path=target/libs \
		-Duser.timezone=UTC \
		-jar target/$(SERVICE).jar

docker-build:
	IMAGE_TAG=latest docker-compose build

docker-run:
	docker run -d \
		-p 8080:8080 \
		-p 6300:6300 \
		-e APP_JVM_OPTS="-javaagent:libs/org.jacoco.agent-runtime.jar=output=tcpserver,port=6300,address=127.0.0.1,append=false" \
		--name $(SERVICE) \
		edtech-kt/$(SERVICE)

docker-cleanup:
	docker stop $(SERVICE)
	docker rm $(SERVICE)
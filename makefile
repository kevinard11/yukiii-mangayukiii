IMAGE_NAME ?= yukiii/mangayukiii-spr
IMAGE_TAG ?= latest
.DEFAULT_GOAL := default

clean:
	mvn -U clean

compile: clean
	mvn package -Dmaven.test.skip=true

package: clean
	mvn verify -Dmaven.test.skip=true

build: clean
	mvn verify

run-local:
	mvn -U clean spring-boot:run -Dspring-boot.run.profiles=default,local

stack-up: compile
	docker-compose build mangayukiii-spr
	docker-compose up -d

image: compile
	docker build -t $(IMAGE_NAME):$(IMAGE_TAG) .

push-docker: image
	 docker push $(IMAGE_NAME):$(IMAGE_TAG)
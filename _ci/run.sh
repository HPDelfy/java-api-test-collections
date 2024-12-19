DOCKER_CONTAINER_NAME="javaTestContainer"
DOCKER_IMAGE_NAME="javaapitest"
docker rm -f ${DOCKER_CONTAINER_NAME}
docker build -t ${DOCKER_IMAGE_NAME} .
docker run --name ${DOCKER_CONTAINER_NAME} ${DOCKER_IMAGE_NAME}:latest
docker cp ${DOCKER_CONTAINER_NAME}:/java-api-test-collections/target/cucumber ./target/cucumber/
docker rm -f ${DOCKER_CONTAINER_NAME}
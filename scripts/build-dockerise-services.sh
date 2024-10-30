cd ../
echo "Building in $(pwd)"
docker build -t holamundoservice:0.0.1-SNAPSHOT --build-arg IMAGE_VERSION=$(grep appVersion ./gradle.properties | cut -d '=' -f2)  --build-arg GIT_REV=$(git rev-parse HEAD) .
echo "Built image holamundoservice:0.0.1-SNAPSHOT"
### K8S config
how to build the image
```
docker build -t holamundoservice:0.0.1-SNAPSHOT --build-arg IMAGE_VERSION=$(grep appVersion ./gradle.properties | cut -d '=' -f2)  --build-arg GIT_REV=$(git rev-parse HEAD) --no-cache .
```


run the following manifest
```
kubectl apply -f namespaces.yaml
kubectl apply -f hello-world.yaml
kubectl apply -f watcher.yaml
kubectl apply -f discovery.yaml
kubectl apply -f config.yaml
```

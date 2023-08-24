# gRPC - Auth

---

## How to Clone

#### 1. Clone this repository
```
git clone <url>
```

#### 2. Clone its submodules
This repo contains submodule to fetch proto files from [gRPC - Protos](https://github.com/swarawan/grpc-protos)
```
./submodule-clone.sh
```
To fetch lates commits of submodule, execute
```
./submodule.update.sh
```

## How to Run in Docker

Change to target profile-app host in compose.yml
```
    ...
    ...
    environment:
      - profileHost=host.docker.internal
```

Execute docker compose to build and create a docker container.
```
docker-compose --build -d
```
#!/bin/bash

# Get the latest commit hash
LATEST_COMMIT_HASH=$(git rev-parse HEAD)

# Update the deployment.yaml file
sed -i "s|image: 211125698062.dkr.ecr.ap-south-1.amazonaws.com/coinbase-docker-repo:coinbase-latest|image: 211125698062.dkr.ecr.ap-south-1.amazonaws.com/coinbase-docker-repo:$LATEST_COMMIT_HASH|g" k8s/deployment.yaml
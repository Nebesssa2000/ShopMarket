#!/bin/bash

# Define paths for Dockerfiles and Kubernetes manifests
declare -A dockerfiles=(
  ["order-service"]="docker/orderservice/Dockerfile"
  ["payment-service"]="docker/paymentservice/Dockerfile"
  ["delivery-service"]="docker/deliveryservice/Dockerfile"
  ["product-service"]="docker/productservice/Dockerfile"
  ["warehouse-service"]="docker/warehouseservice/Dockerfile"
  ["ui-service"]="docker/ui-service/Dockerfile"
)

declare -A deployments=(
  ["order-service"]="k8s/deployments/services/order-service-dc.yaml"
  ["payment-service"]="k8s/deployments/services/payment-service-dc.yaml"
  ["delivery-service"]="k8s/deployments/services/delivery-service-dc.yaml"
  ["product-service"]="k8s/deployments/services/product-service-dc.yaml"
  ["warehouse-service"]="k8s/deployments/services/warehouse-service-dc.yaml"
  ["ui-service"]="k8s/deployments/services/ui-service-deployment.yaml"
)

declare -A databases=(
  ["order-db"]="k8s/deployments/db/order-db.yaml"
  ["payment-db"]="k8s/deployments/db/payment-db.yaml"
  ["delivery-db"]="k8s/deployments/db/delivery-db.yaml"
  ["product-db"]="k8s/deployments/db/product-db.yaml"
  ["warehouse-db"]="k8s/deployments/db/warehouse-db.yaml"
)

# Build and push Docker images for each service
for service in "${!dockerfiles[@]}"; do
  echo "Building Docker image for $service..."
  docker build -t victor2023victorovich/$service:latest -f ${dockerfiles[$service]} .
  echo "Pushing Docker image for $service..."
  docker push victor2023victorovich/$service:latest
done

# Apply Istio manifests
echo "Applying Istio manifests..."
kubectl apply -f k8s/istio/istio-gateway.yaml
kubectl apply -f k8s/istio/istio-virtualservice.yaml

# Apply Kubernetes deployments for services
for service in "${!deployments[@]}"; do
  echo "Applying Kubernetes deployment for $service..."
  kubectl apply -f ${deployments[$service]}
done

# Apply Kubernetes deployments for databases
for db in "${!databases[@]}"; do
  echo "Applying Kubernetes deployment for $db..."
  kubectl apply -f ${databases[$db]}
done

echo "Deployment completed."

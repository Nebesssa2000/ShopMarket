#!/bin/bash

# Delete Kubernetes deployments and services
services=("order-service" "payment-service" "delivery-service" "product-service" "warehouse-service" "ui-service")
for service in "${services[@]}"; do
  echo "Deleting deployment and service for $service..."
  kubectl delete deployment $service
  kubectl delete service $service
done

# Delete PostgreSQL deployments and services
databases=("order-db" "payment-db" "delivery-db" "product-db" "warehouse-db")
for db in "${databases[@]}"; do
  echo "Deleting deployment and service for $db..."
  kubectl delete deployment $db
  kubectl delete service $db
done

# Delete Istio Gateway and VirtualService
echo "Deleting Istio Gateway and VirtualService..."
kubectl delete -f k8s/istio/istio-gateway.yaml
kubectl delete -f k8s/istio/istio-virtualservice.yaml

echo "Cleanup completed."
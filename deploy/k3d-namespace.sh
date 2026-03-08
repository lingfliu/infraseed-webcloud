#!/usr/bin/env bash
# Create namespace infraseed_p0 for deployment (used with infraseed-devops).
# Requires kubectl and a k3d (or other) cluster. If you get "permission denied" on ~/.kube/config, run:
#   sudo ./deploy/fix-kubeconfig.sh
set -e
NAMESPACE="${1:-infraseed_p0}"
if kubectl get namespace "$NAMESPACE" &>/dev/null; then
  echo "Namespace $NAMESPACE already exists."
else
  kubectl create namespace "$NAMESPACE"
  echo "Created namespace $NAMESPACE."
fi

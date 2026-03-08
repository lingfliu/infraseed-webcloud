#!/usr/bin/env bash
# Fix kubeconfig ownership so kubectl works without sudo.
# Run once with: sudo ./deploy/fix-kubeconfig.sh
set -e
REAL_USER="${SUDO_USER:-$USER}"
REAL_HOME=$(eval echo "~$REAL_USER")
KUBE_DIR="$REAL_HOME/.kube"
KUBE_CONFIG="$KUBE_DIR/config"
if [[ -f "$KUBE_CONFIG" ]]; then
  chown "$REAL_USER:$(id -gn "$REAL_USER")" "$KUBE_CONFIG"
  chmod 600 "$KUBE_CONFIG"
  echo "Fixed ownership of $KUBE_CONFIG (owner: $REAL_USER)"
else
  echo "No $KUBE_CONFIG found."
  exit 1
fi
if [[ -d "$KUBE_DIR" ]]; then
  chown -R "$REAL_USER:$(id -gn "$REAL_USER")" "$KUBE_DIR"
fi

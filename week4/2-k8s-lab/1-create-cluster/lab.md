
### install kubectl

```bash
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x kubectl
sudo mv kubectl /usr/local/bin/kubectl
kubectl version --client
kubectl config get-clusters
kubectl config get-users
kubectl config get-contexts

```

### install kind

```bash
curl -Lo ./kind https://kind.sigs.k8s.io/dl/latest/kind-linux-amd64
chmod +x ./kind
sudo mv ./kind /usr/local/bin/kind
kind version
```


### create k8s cluster
```bash
kind create cluster --image kindest/node:v1.30.0 --config kind-config.yaml --name npci-k8s-cluster
kind get clusters
kubectl get nodes
```


### delete k8s cluster

```bash
kind delete cluster --name my-k8s-cluster
```


### install helm and rancher

```bash
curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash
helm version
helm repo add rancher-latest https://releases.rancher.com/server-charts/latest
helm repo update
kubectl apply -f https://github.com/jetstack/cert-manager/releases/latest/download/cert-manager.yaml
kubectl wait --for=condition=available --timeout=600s deployment/cert-manager -n cert-manager
kubectl create namespace cattle-system
helm install rancher rancher-latest/rancher \
  --namespace cattle-system \
  --set hostname=rancher.localhost \
  --set bootstrapPassword=admin
kubectl port-forward --address 0.0.0.0 -n cattle-system svc/rancher 8443:443
```






### get all k8s resources

```bash
kubectl api-versions
kubectl api-resources
```

### create a namespace

```bash
kubectl create namespace batch9
kubectl get namespaces
```

### switch to a namespace

```bash
kubectl config set-context --current --namespace=batch9
```



### AKS
```bash
az aks create \
    --resource-group myResourceGroup \
    --name nag-aks \
    --generate-ssh-keys \
    --node-count 3 \
    --zones 1 2 3
kubectl get nodes -o wide
kubectl get nodes --show-labels
```
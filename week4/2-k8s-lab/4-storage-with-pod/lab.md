
### Pod with emptyDir & hostPath volume

```bash
kubectl delete -f java-web-service-pod.yaml
kubectl apply -f java-web-service-pod.yaml
kubectl get pods
kubectl exec -it java-web-service-pod -c java-web-service-container  -- /bin/sh
curl localhost:8080/hello
cat /app/log/java-web-service.log
kubectl exec -it java-web-service-pod -c log-reader-container  -- /bin/sh
cat /var/log/java-web-service.log
```


### persistent volumes with hostPath
```bash
kubectl apply -f pv.yaml
kubectl get pv
```


### persistent volume with NFS
```bash
# ✅ 1️⃣ Install NFS Server on your host (Ubuntu/Debian):
sudo apt update
sudo apt install -y nfs-kernel-server

# ✅ 2️⃣ Create and configure the NFS export directory:
sudo mkdir -p /npci/nfs/jws/log
sudo chown -R nobody:nogroup /npci/nfs/jws/log
sudo chmod -R 777 /npci/nfs/jws/log

# ✅ 3️⃣ Export the directory in /etc/exports:
sudo nano /etc/exports
# add below line
/npci/nfs/jws/log *(rw,sync,no_subtree_check,no_root_squash)

sudo exportfs -rav
sudo systemctl restart nfs-kernel-server
sudo exportfs -v

# ✅ 4️⃣ Install NFS Client on your host (Ubuntu/Debian):
sudo apt update
sudo apt install -y nfs-common

```

#### NFS Subdir External Provisioner with correct settings

```bash

curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3
chmod 700 get_helm.sh
./get_helm.sh

helm repo add nfs-subdir-external-provisioner https://kubernetes-sigs.github.io/nfs-subdir-external-provisioner/
helm repo update

helm install nfs-client nfs-subdir-external-provisioner/nfs-subdir-external-provisioner \
  --set nfs.server=10.0.0.4 \
  --set nfs.path=/npci/nfs/jws/log \
  --set storageClass.name=nfs-sc \
  --set storageClass.defaultClass=true

kubectl get pods -l app=nfs-subdir-external-provisioner
kubectl logs -l app=nfs-subdir-external-provisioner

kubectl get sc

```


```bash
kubectl get pods
```
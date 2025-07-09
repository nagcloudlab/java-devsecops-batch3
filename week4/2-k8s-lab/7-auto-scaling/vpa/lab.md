


### vpa

```bash


kubectl apply -f nginx.yaml



git clone https://github.com/kubernetes/autoscaler.git
cd autoscaler/vertical-pod-autoscaler/

kubectl delete -f deploy/

kubectl get pods -n kube-system | grep vpa

kubectl apply -f nginx-vpa.yaml
kubectl get vpa


kubectl run -i --tty load-generator --rm --image=busybox -- /bin/sh -c "while true; do wget -q -O- http://nginx; done"


kubectl delete -f nginx-vpa.yaml
kubectl delete -f nginx.yaml


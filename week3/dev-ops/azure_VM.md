create a resource group

```bash
az group create --name myResourceGroup --location centralindia
```

create a ubuntu virtual machine

```bash
az vm create \
  --resource-group myResourceGroup \
  --name myVM \
  --image Ubuntu2404 \
  --size Standard_D4s_v3 \
  --admin-username azureuser \
  --ssh-key-values ~/.ssh/id_rsa.pub \
  --public-ip-sku Standard
```

delete the virtual machine

```bash
az vm delete --resource-group myResourceGroup --name myVM
```

delete a resource group

```bash
az group delete --name myResourceGroup
```

open port 80 on the VM

```bash
az vm open-port --resource-group myResourceGroup --name myVM1 --port "*"
```

get the public IP address of the VM

```bash
az vm list-ip-addresses --resource-group myResourceGroup --name myVM1 --output table
```

ssh into the VM

```bash
chmod 600 ~/.ssh/id_rsa
sudo ssh -i ~/.ssh/id_rsa azureuser@4.240.126.74
```

---

```bash
cat /etc/os-release
```

install zsh in ubuntu 24.04

```bash
sudo apt update
sudo apt install zsh -y
```

install oh-my-zsh

```bash
sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)" -y
```


```bash
scp -i ~/.ssh/id_rsa /Users/nag/java-devsecops-batch3/week3/dev-ops/transfer-service/target/transfer-service-0.0.1-SNAPSHOT.jar azureuser@4.240.126.74:/home/azureuser
```
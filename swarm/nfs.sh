sudo apt update
sudo apt install -y nfs-kernel-server

sudo chmod 777 /home/david/leare_document_ms/temp

sudo vim /etc/exports
/home/david/leare_document_ms/temp 35.203.60.206(rw,sync,no_subtree_check) 35.234.244.116(rw,sync,no_subtree_check)

echo "/home/david/leare_document_ms/temp 35.203.60.206(rw,sync,no_subtree_check) 35.234.244.116(rw,sync,no_subtree_check)" >> /etc/exports
sudo exportfs -r

sudo systemctl restart nfs-kernel-server
sudo systemctl restart rpcbind

sudo systemctl start rpcbind
sudo systemctl enable rpcbind
sudo systemctl start nfs-common
sudo systemctl enable nfs-common

    #if it fails
rm /lib/systemd/system/nfs-common.service
sudo passwd
systemctl daemon-reload

# client
sudo apt update
sudo apt install -y nfs-common

mkdir document_temp
mkdir -p ./leare_document_ms/temp
sudo docker volume create --driver local \
  --opt type=nfs \
  --opt o=addr=34.47.19.29,rw,nolock  \
  --opt device=:/home/david/leare_document_ms/temp \
  document_temp


sudo mkdir -p /mnt/nfs_test
sudo mount -t nfs 34.47.19.29:/home/david/leare_document_ms/temp /mnt/nfs_test

sudo apt update
sudo apt install -y nfs-kernel-server

sudo chmod 777 /home/david/leare_document_ms/temp

sudo vim /etc/exports
/home/david/leare_document_ms/temp 34.95.41.74(rw,sync,no_subtree_check) 34.47.29.120(rw,sync,no_subtree_check)

echo "/home/david/leare_document_ms/temp 34.118.140.169(rw,sync,no_subtree_check)" >> /etc/exports
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

sudo docker volume create --driver local \
  --opt type=nfs \
  --opt o=addr=35.203.15.126,rw \
  --opt device=:/home/david/leare_document_ms/temp \
  document_temp


sudo mkdir -p /mnt/nfs_test
sudo mount -t nfs 35.203.15.126:/home/david/leare_document_ms/temp /mnt/nfs_test

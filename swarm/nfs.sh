sudo apt update
sudo apt install -y nfs-kernel-server

sudo chmod 755 /home/david/leare_document_ms/temp

sudo vim /etc/exports
/home/david/leare_document_ms/temp 34.95.41.74(rw,sync,no_subtree_check) 34.47.29.120(rw,sync,no_subtree_check)

sudo exportfs -r

sudo systemctl restart nfs-kernel-server
sudo systemctl restart rpcbind

sudo systemctl start rpcbind
sudo systemctl enable rpcbind
sudo systemctl start nfs-common
sudo systemctl enable nfs-common


# client
sudo mkdir -p /mnt/nfs_test
sudo mount -t nfs 35.203.77.161:/home/david/leare_document_ms/temp /mnt/nfs_test

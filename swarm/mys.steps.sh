# Master
<INSIDE-CONTAINER>
mariadb -u root -p # password
CREATE USER 'replicator'@'%' IDENTIFIED BY 'replica';
GRANT REPLICATION SLAVE ON *.* TO 'replicator'@'%';
FLUSH PRIVILEGES;
exit
echo "[mysqld]" >> /etc/mysql/my.cnf
echo "server-id=1" >> /etc/mysql/my.cnf
echo "log_bin=mysql-bin" >> /etc/mysql/my.cnf
echo "binlog-do-db=my_app_development" >> /etc/mysql/my.cnf
echo "auto_increment_increment = 2" >> /etc/mysql/my.cnf
echo "auto_increment_offset = 1" >> /etc/mysql/my.cnf
exit

sudo docker restart <container-id>
mariadb -u root -p # password
SHOW MASTER STATUS;


# Slave
<INSIDE-CONTAINER>
swarm-courses-db-1
mariadb -u root -p # password
CREATE USER 'replicator'@'%' IDENTIFIED BY 'replica';
GRANT REPLICATION SLAVE ON *.* TO 'replicator'@'%';
FLUSH PRIVILEGES;
SHOW MASTER STATUS;
exit
echo "[mysqld]" >> /etc/mysql/my.cnf
echo "server-id=2" >> /etc/mysql/my.cnf
echo "log_bin=mysql-bin" >> /etc/mysql/my.cnf
echo "binlog_do_db=my_app_development" >> /etc/mysql/my.cnf
echo "auto_increment_increment = 2" >> /etc/mysql/my.cnf
echo "auto_increment_offset = 2" >> /etc/mysql/my.cnf
exit

sudo docker restart <container-id>
mariadb -u root -p # password
SHOW MASTER STATUS;

# OUT
sudo docker network ls
sudo docker network inspect <the network name>
# Master
<INSIDE-CONTAINER>
mariadb -u root -p # password
STOP SLAVE;
CHANGE MASTER TO #remember that this is from the other guy
    MASTER_HOST='<other-dns-name>', #change
    MASTER_USER='replicator',
    MASTER_PASSWORD='replica',
    MASTER_LOG_FILE='<file-from-show-status>', #change (this is the file name from the show master status, normally mysql-bin.000001 )
    MASTER_LOG_POS=<the-number-from-above>; #change same from above

# Slave
<INSIDE-CONTAINER>
mariadb -u root -p # password
STOP SLAVE;
CHANGE MASTER TO
    MASTER_HOST='<other-dns-name>', #change
    MASTER_USER='replicator',
    MASTER_PASSWORD='replica',
    MASTER_LOG_FILE='<file-from-show-status>', #change (this is the file name from the show master status, normally mysql-bin.000001 )
    MASTER_LOG_POS=<the-number-from-above>; #change same from above
START SLAVE;
exit
sudo docker restart <container-id>

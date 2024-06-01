# Master
<INSIDE-CONTAINER>
mariadb -u root -p'password'
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

# FROM THE MASTER LEARE-LARGE
sudo docker service update --force leare_users-db

mariadb -u root -p'password'
SHOW MASTER STATUS;


# Slave
<INSIDE-CONTAINER>
mariadb -u root -p'password'
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

# FROM THE MASTER LEARE-LARGE
sudo docker service update --force leare_users-db

mariadb -u root -p'password'
SHOW MASTER STATUS; 

# OUT
sudo docker network ls
sudo docker network inspect <the network name>
# Master
<INSIDE-CONTAINER>
mariadb -u root -p'password'
STOP SLAVE;
CHANGE MASTER TO #remember that this is from the other guy
    MASTER_HOST='<DNS-Name-2>', #change
    MASTER_USER='replicator',
    MASTER_PASSWORD='replica',
    MASTER_LOG_FILE='<log-file-name-2>', #change (this is the file name from the show master status, normally mysql-bin.000001 )
    MASTER_LOG_POS=<log-position-2>; #change same from above

# Slave
<INSIDE-CONTAINER>
mariadb -u root -p'password'
STOP SLAVE;
CHANGE MASTER TO
    MASTER_HOST='<DNS-Name-2>', #change
    MASTER_USER='replicator',
    MASTER_PASSWORD='replica',
    MASTER_LOG_FILE='<log-file-name-1>', #change (this is the file name from the show master status, normally mysql-bin.000001 )
    MASTER_LOG_POS=<log-position-1>; #change same from above
START SLAVE;
exit
sudo docker restart <container-id>

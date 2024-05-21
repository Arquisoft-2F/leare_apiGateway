# master
<INSIDE OF THE CONTAINER>
su - postgres
initdb -D /var/lib/postgresql/data
pg_ctl -o "-p 5490" start -D /var/lib/postgresql/data
psql -p 5490
CREATE USER replicator WITH REPLICATION ENCRYPTED PASSWORD 'replica';
\q

echo "listen_addresses = '*'" >> /var/lib/postgresql/data/postgresql.conf
echo "wal_level = replica" >> /var/lib/postgresql/data/postgresql.conf
echo "max_wal_senders = 10" >> /var/lib/postgresql/data/postgresql.conf
echo "wal_keep_size = 64" >> /var/lib/postgresql/data/postgresql.conf
echo "host replication replicator 0.0.0.0/0 md5" >> /var/lib/postgresql/data/pg_hba.conf
exit
pg_ctl restart -D /var/lib/postgresql/data
    #if db is not up
    su - postgres
    pg_ctl -o "-p 5490" start -D /var/lib/postgresql/data
# Slave
sudo docker network ls
sudo docker network inspect <the network name>
<INSIDE OF THE CONTAINER>
rm -rf /var/lib/postgresql/data/*
pg_basebackup -h <master-dns-name> -D /var/lib/postgresql/data -U replicator -p 5490 -P --wal-method=stream
touch /var/lib/postgresql/data/standby.signal
echo "primary_conninfo = 'host=<master-dns-name> port=5490 user=replicator password=replica'" >> /var/lib/postgresql/data/postgresql.conf
chown -R postgres:postgres /var/lib/postgresql/data
chmod -R 700 /var/lib/postgresql/data
su - postgres
pg_ctl -o "-p 5491" start -D /var/lib/postgresql/data

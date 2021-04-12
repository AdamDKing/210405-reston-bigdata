sudo apt-get update
sudo apt-get install postgresql postgresql-contrib
sudo -u postgres psql postgres
\password postgres
\q

Then try to connect using DBeaver.  If we run into trouble:
sudo nano /etc/postgresql/12/main/pg_hba.conf
change peer to md5
sudo nano /etc/postgresql/12/main/postgresql.conf
change port to something else, I use 5026

may also have trouble with WSL2 localhost
check netstat on Ubuntu to make sure postgres is listening on your port
check resource monitor on windows to make sure windows can see postgres listening
if netstat is good and resource monitor is bad, try wsl --shutdown
there *is* a better solution: https://gist.github.com/coltenkrauter/608cfe02319ce60facd76373249b8ca6
the original solution in that gist looks better to me than the new one?
  657  apt install ufw
  658  ufw enable
  659  ufw allow 22/tcp
  660  ufw allow 7822/tcp
  661  ufw allow http
  662  ufw allow https
  663  ufw enable
  664  ufw status verbose


## reference
===============
ufw status verbose
ufw app list

ufw allow 22/tcp
sudo ufw allow http
sudo ufw allow https
sudo ufw allow 80/tcp
sudo ufw allow 443/tc

To allow incoming tcp and udp packet on port 53, enter:
sudo ufw allow 53

To allow IP address 192.168.1.10 access to port 22 for all protocols
sudo ufw allow from 192.168.1.10 to any port 22

Open port 74.86.26.69:443 (SSL 443 nginx/apache/lighttpd server) for all, enter:
sudo ufw allow from any to 74.86.26.69 port 443 proto tcp

====================
apt install ufw
ufw enable
ufw status verbose
ufw show raw
ufw disable
ufw allow 53/tcp
ufw deny 53/tcp

ufw deny 80/tcp
ufw delete deny 80/tcp

ufw allow <service name>
ufw deny <service name>

ufw logging on|off
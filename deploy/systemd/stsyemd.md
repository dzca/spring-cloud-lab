Useful SystemD commands (hints for systemctl or systemctl vs chkconfig and service)

all configuration in 
/etc/systemd/system

List all running services

# systemctl
Start/stop or enable/disable services

Activates a service immediately:

# systemctl start foo.service
Deactivates a service immediately:

# systemctl stop foo.service
Restarts a service:

# systemctl restart foo.service
Shows status of a service including whether it is running or not:

# systemctl status foo.service
Enables a service to be started on bootup:

# systemctl enable foo.service
Disables a service to not start during bootup:

# systemctl disable foo.service
Check whether a service is already enabled or not:

# systemctl is-enabled foo.service; echo $?

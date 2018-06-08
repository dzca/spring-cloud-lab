erlang version

erl -eval 'erlang:display(erlang:system_info(otp_release)), halt().'  -noshell


wget https://packagecloud.io/rabbitmq/rabbitmq-server/packages/ubuntu/xenial/rabbitmq-server_3.6.3-1_all.deb

dpkg -i rabbitmq-server_3.6.3-1_all.deb

wget https://packages.erlang-solutions.com/erlang-solutions_1.0_all.deb
  763  dpkg -i erlang-solutions_1.0_all.deb
  764  apt-get update
  765  apt-get install erlang erlang-nox
  766  echo 'deb http://www.rabbitmq.com/debian/ testing main' | sudo tee /etc/apt/sources.list.d/rabbitmq.list
  767  wget -O- https://www.rabbitmq.com/rabbitmq-release-signing-key.asc | sudo apt-key add -
  768  apt-get update
  769  apt-get install rabbitmq-server
  770  systemctl enable rabbitmq-server
  771  systemctl start rabbitmq-server
  
==========================
RabbitMQ是最受欢迎的开源消息代理。 RabbitMQ是适用于大多数流行操作系统的轻量级应用程序。 RabbitMQ支持多种消息协议。 RabbitMQ可以轻松部署在分布式和联合配置中，以满足高规模，高可用性要求。 本教程将帮助您在Ubuntu 16.04 LTS和14.04 LTS系统上安装RabbitMQ。
步骤1 - 安装Erlang

首先，使用以下命令在系统上添加erlang apt存储库。 您可以从其官方网站下载erlang存储库包并安装在您的系统上。

$ wget https://packages.erlang-solutions.com/erlang-solutions_1.0_all.deb
$ sudo dpkg -i erlang-solutions_1.0_all.deb

 现在，您可以使用以下命令在系统上安装erlang软件包。 这也将安装所有的依赖项。 
$ sudo apt-get update
$ sudo apt-get install erlang erlang-nox

 步骤2 - 安装RabbitMQ服务器

安装要求后，现在可以在系统上启用RabbitMQ apt存储库。 您还需要在系统上导入rabbitmq签名密钥。 使用以下命令执行此操作。 

$ echo 'deb http://www.rabbitmq.com/debian/ testing main' | sudo tee /etc/apt/sources.list.d/rabbitmq.list
$ wget -O- https://www.rabbitmq.com/rabbitmq-release-signing-key.asc | sudo apt-key add -

之后更新apt缓存并在您的系统上安装RabbitMQ服务器。

$ sudo apt-get update
$ sudo apt-get install rabbitmq-server

步骤3 - 管理RabbitMQ服务

完成上述安装后，启用系统上的RabbitMQ服务。 此外，启动RabbitMQ服务。 使用以下方法将sysvinit用于较旧的系统或systemctl作为最新的操作系统

$ sudo systemctl enable rabbitmq-server
$ sudo systemctl start rabbitmq-server
$ sudo systemctl stop rabbitmq-server

 步骤4 - 在RabbitMQ中创建管理员用户

默认情况下，rabbitmq创建一个名为“guest”的用户，密码为“guest”。 您还可以使用以下命令在RabbitMQ服务器上创建自己的管理员帐户。 用自己的密码更改密码。 

$ sudo rabbitmqctl add_user admin password 
$ sudo rabbitmqctl set_user_tags admin administrator
$ sudo rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"

rabbitmqctl change_password guest ocbl2018
步骤5 - 安装RabbitMQ Web管理控制台

 RabbitMQ还提供用于管理整个RabbitMQ的Web管理控制台。 启用Web管理控制台在系统上运行以下命令。 Web管理控制台可帮助您管理RabbitMQ服务器。

$ sudo rabbitmq-plugins enable rabbitmq_management

RabbitMQ仪表板从端口15672启动。访问端口上的服务器以获取仪表板。 使用在步骤4中创建的用户名和密码
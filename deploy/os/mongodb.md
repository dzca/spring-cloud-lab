## install

apt-get purge mongodb-org

apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0C49F3730359A14518585931BC711F9BA15703C6
echo "deb http://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.4.list
apt-get update
apt-get install -y mongodb-org

## systemd
```
[Unit]
Description=MongoDB Database Service
Wants=network.target
After=network.target

[Service]
ExecStart=/usr/bin/mongod --config /etc/mongod.conf
ExecReload=/bin/kill -HUP $MAINPID
Restart=always
User=mongodb
Group=mongodb
StandardOutput=syslog
StandardError=syslog

[Install]
WantedBy=multi-user.target

```

## Backup and restore data

mongodump -d <database_name> -o <directory_backup>
mongorestore -d <database_name> <directory_backup>

Upstart scripts /etc/init/mongod.conf

#!upstart
description "upstart that runs the  mongod daemon"

start on startup
stop on shutdown

# Automatically Respawn:
# respawn

env BIN_DIR="/usr/bin"
env MONGO_CONFIG="/etc/mongod.conf"

script
    PATH=$BIN_DIR:$PATH
    exec sudo -u mongodb $BIN_DIR/mongod --quiet --config $MONGO_CONFIG
end script

Commands

``` bash
[] insert
> post = {"title" : "My Blog Post",
... "content" : "Here's my blog post.",
... "date" : new Date()}
{
"title" : "My Blog Post",
"content" : "Here's my blog post.",
"date" : "Sat Dec 12 2009 11:23:21 GMT-0500 (EST)"
}
> db.blog.insert(post)
>db.event_count.findOne()

[] find returns all of the documents in a collection
> db.blog.find()
db.events.find(null, {host: 1,user:1 });
[] update
> db.blog.update({title : "My Blog Post"}, post)
> db.unicorns.update({name: 'Roooooodles'}, {$set: {weight: 590}})

[] update field name
db.events_event.update({ $rename : { 'id' : 'sequence' } })
db.events_event.update({}, {"$rename":{"id":"sequence" }, false, true});

db.events_event.update({}, {"$rename":{"id":"sequence" }});

[]remove
> db.event_count.remove({number : 1})
db.unicorns.update({name: 'Roooooodles'}, {$set: {weight: 590}})

[]add index
>>> 
db.events.ensureIndex('create_time')

        event = {'create_time': time, 
                 'user': log_item_list[0], 
                 'ip': log_item_list[1], 
                 'host':log_item_list[2],

[]show collections
db.getCollectionNames()
[]drop collection
db.drop_collection("bar")

[] drop a db
> use sfm_test
switched to db sfm_test
> db.dropDatabase();
{ "dropped" : "sfm_test", "ok" : 1 }

dumping
mongoexport --db sfm --collection events --out sfm_events.json
mongoexport --db bd --collection orders --out orders.json
import
mongoimport -d sfm_test -c events --file sfm_events.json

use sfm_test
db.dropDatabase();

mongoimport -d bd -c city --type csv --file canada.csv --headerline
mongoimport -d bd -c business --type csv --file business.csv --headerline

mongoimport -d bd -c people --type csv --file people.csv --headerline

```
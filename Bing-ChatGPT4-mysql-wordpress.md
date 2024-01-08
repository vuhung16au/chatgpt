# Overview 

Using Bing/ChatGPT 4 to generate a `docker-compose.yaml` file and install Wordpress/MySQL 

# Thoughts 
- It runs in the first place, no modification required 
- This is a simple docker compose file and ChatGPT could do it
- Great assistant! 

## ChatGPT failed when adding nginx requirements 

- Use `nginx` as the web server 
- Use `alpine` as the base OS

Probably, I need some changes to the `nginx.conf` file Bing/ChatGPT4 gave me. 

# The Prompt

Write a Docker compose file `docker-compose.yaml` with following requirements 

Database: 
- Install docker mysql:5.7
- Root password: My$SqlPass
- Create a database: WordpressDB
- Create an user/password: wordpress/WpP@ssword and grant full privildge for user `wordpress` to the database WordpressDB
- Run on default port 3306
- Name of docker image: "mysql2024Jan"

Wordpress CMS:
- docker wordpress:6.4.2
- depends on mysql:5.7
- Default admin/password/email for Wordpress:6.4.2: admin/vuhungOz2024/vuhung16oz@gmail.com
- Use database `WordpressDBSelenium` we have created in the mysql docker
- Wordpress runs on port 8080
- name of the docker image: "wordpress2024Jan"


Volumes / map to /var/www/html

Guide me which docker commandlines I should run to bring up and down wordpress

# Bing/ChatGPT4's Answer 

```yaml
version: '3.3'

services:
  db:
    image: mysql:5.7
    container_name: mysql2024Jan
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: My$SqlPass
      MYSQL_DATABASE: WordpressDB
      MYSQL_USER: wordpress
      MYSQL_PASSWORD: WpP@ssword
    ports:
      - "3306:3306"
    volumes:
      - db_data:/var/lib/mysql

  wordpress:
    depends_on:
      - db
    image: wordpress:6.4.2
    container_name: wordpress2024Jan
    restart: always
    environment:
      WORDPRESS_DB_HOST: db:3306
      WORDPRESS_DB_USER: wordpress
      WORDPRESS_DB_PASSWORD: WpP@ssword
      WORDPRESS_DB_NAME: WordpressDB
      WORDPRESS_TABLE_PREFIX: wp_
      WORDPRESS_CONFIG_EXTRA: |
        define('WP_DEBUG', false);
        define('WP_DEBUG_LOG', false);
        define('WP_DEBUG_DISPLAY', false);
    ports:
      - "8080:80"
    volumes:
      - ./wp-content:/var/www/html/wp-content

volumes:
  db_data:
```
To bring up the containers, navigate to the directory containing the docker-compose.yaml file and run the following command:

```
docker-compose up -d
```
To stop and remove the containers, run the following command:

```
docker-compose down
```

This will stop and remove the containers, but will preserve the data in the database volume.

# Bring up the containers 

```vhmac:00.Docker-Wordpress-MySQL-Nginx vuhung$ docker-compose up -d
WARN[0000] The "SqlPass" variable is not set. Defaulting to a blank string.
[+] Running 22/22
 ⠿ wordpress Pulled                                                                                                   143.8s
...
   ⠿ dcace32066bc Pull complete                                                                                       137.8s
[+] Running 3/3
 ⠿ Network 00docker-wordpress-mysql-nginx_default  Created                                                              0.1s
 ⠿ Container mysql2024Jan                          Started                                                              1.8s
 ⠿ Container wordpress2024Jan                      Started                                                              1.4s
```

## Verify that They Are Running 

```
$ docker ps
CONTAINER ID   IMAGE             COMMAND                  CREATED         STATUS         PORTS                               NAMES
906c437ac756   wordpress:6.4.2   "docker-entrypoint.s…"   5 minutes ago   Up 5 minutes   0.0.0.0:8080->80/tcp                wordpress2024Jan
cde2d8b667ac   mysql:5.7         "docker-entrypoint.s…"   5 minutes ago   Up 5 minutes   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql2024Jan
```

Everything looks good

# Setup Wordpress

Open `http://localhost:8080/`
and we we will Wordpress setup page: `http://localhost:8080/wp-admin/install.php`

![Bing-ChatGPT4-mysql-wordpress](https://github.com/vuhung16au/chatgpt/blob/main/Bing-ChatGPT4-mysql-wordpress-01.png?raw=true)
![Bing-ChatGPT4-mysql-wordpress](https://github.com/vuhung16au/chatgpt/blob/main/Bing-ChatGPT4-mysql-wordpress-02.png?raw=true)
![Bing-ChatGPT4-mysql-wordpress](https://github.com/vuhung16au/chatgpt/blob/main/Bing-ChatGPT4-mysql-wordpress-03.png?raw=true)
![Bing-ChatGPT4-mysql-wordpress](https://github.com/vuhung16au/chatgpt/blob/main/Bing-ChatGPT4-mysql-wordpress-04.png?raw=true)

# A Prompt (MariaDB + Wordpress)

This works like a charm: 

Write a Docker compose file `docker-compose.yaml` with following requirements 

Database: 
- Install docker mariadb, fixed latest stable version 
- Root password: Mari@Pass
- Create a database: WordpressDB
- Create an user/password: wordpress/WpP@ssword and grant full privildge for user `wordpress` to the database WordpressDB
- Run on default port 3306
- Name of docker image: "Maria2024Jan"

Wordpress CMS:
- docker wordpress:6.4.2
- depends on mariadb docker (see above)
- Default admin/password/email for Wordpress:6.4.2: admin/vuhungOz2024/vuhung16oz@gmail.com
- Use database `WordpressDB` we have created in the mysql docker
- Wordpress runs on port 8080
- name of the docker image: "wordpress2024Jan"

Volumes / map to /var/www/html

Guide me which docker commandlines I should run to bring up and down wordpress


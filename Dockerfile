FROM postgres
ENV POSTGRES_DB=siap_bd
ENV POSTGRES_PASSWORD=justina
COPY init.sql /docker-entrypoint-initdb.d/
COPY postgresql.conf/var/lib/postgresql/data/postgresql.conf

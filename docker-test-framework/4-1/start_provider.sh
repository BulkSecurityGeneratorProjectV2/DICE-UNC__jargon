#! /bin/bash

# Start the Postgres database.
service postgresql start
counter=0
until pg_isready -q
do
    sleep 1
    ((counter += 1))
done
echo Postgres took approximately $counter seconds to fully start ...

# Set up iRODS.
/var/lib/irods/packaging/setup_irods.sh < /var/lib/irods/tests/localhost_setup_postgres.input
echo Running Test Setup Script
su irods -c '/testsetup-consortium.sh'
echo Completed Test Setup Script

# Keep container running if the test fails.
tail -f /dev/null
# Is this better? sleep 2147483647d


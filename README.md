## Deployment procedure

1. Deploy judge0 on a server with docker installed
  https://github.com/judge0/judge0/blob/master/CHANGELOG.md#deployment-procedure
2. Copy the `docker-compose.yml` file from this repository to the server
3. Create a `.env` file

```shell
# .env
SMTP_SERVER=
SMTP_PORT=
SMTP_USER=
SMTP_PASSWORD=
JUDGE0_KEY=
JUDGE0_HOST=45.79.123.242:2358
GITHUB_PAT=
JWT_SECRET_KEY=
MYSQL_DATABASE=
MYSQL_ROOT_PASSWORD=
```

Fill this with your credentials `JUDGE0_KEY` with the judge0 key you set in judge0.conf while 
configuring judge0.
4. Run `docker compose up -d` to start the server in detached mode
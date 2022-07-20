const FetchMethods = require('./methods')

const knexConfig = {
    client: "mysql",
    connection: {
        host: "127.0.0.1",
        port: 3306,
        user: "root",
        password: "123456",
        database: "ALGORTHIM_DATABASE",
    },
};

const fetchMethods = new FetchMethods(knexConfig)

module.exports = fetchMethods
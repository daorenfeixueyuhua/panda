const {SQLDataSource} = require('datasource-sql')

class FetchMethods extends SQLDataSource {
    getPerson() {
        return this.knex("sys_person")
            .select("id, name")
            .then((res) => {
                console.table(res)
                return res
            }).catch(() => {
                0
            })
    }
}

module.exports = FetchMethods
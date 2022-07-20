const books = require('../data/Index')


const resolvers = {
    Query: {
        books: () => books,
        numberSix: () => 6,
        person(_source, _args, {dataSources}) {
            return dataSources.db.getPerson()
        }
    }
};

module.exports = resolvers
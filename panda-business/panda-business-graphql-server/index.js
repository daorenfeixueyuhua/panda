const {ApolloServer} = require('apollo-server');
const typeDefs = require('./model/schema/Index')
const resolvers = require('./model/resolver/Index')
// const db = require('./model/data/mysql.config')

let server = new ApolloServer({
    typeDefs,
    resolvers,
    // dataSources: ()=>{{db}},// 会报错
    csrfPrevention: true,
    cache: 'bounded'
});

server.listen().then(({url}) => {
    console.log(`Server ready at ${url}`)
})
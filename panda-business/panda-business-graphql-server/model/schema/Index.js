const {gql} = require('apollo-server')

const typeDefs = gql`
    type Book{
        title: String
        author: String
    }
    type Person{
        # 主键
        id: String
        # 姓名
        name: String
    }
    type Query{
        books: [Book],
        numberSix: Int,
        person: [Person]
    }


`;
module.exports = typeDefs
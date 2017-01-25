import React from 'react'
import { render } from 'react-dom'
import UsersTable from './UsersTable'
import { getUsers, postUser } from '../fetcher'
import auth from '../auth'

class UsersComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {users: []}

    this.createUserAndSetToState = this.createUserAndSetToState.bind(this)
  }

  getUsersAndSetToState() {
    return getUsers(auth.getToken())
      .then((users) => {
        this.setState({users})
      })
  }

  createUserAndSetToState(body) {
    return postUser(auth.getToken(), body)
      .then((user) => {
        let users = this.state.users
        users.push(user)
        this.setState({users})
      })
  }

  componentWillMount() {
    this.getUsersAndSetToState()
  }

  render() {
    let usersTable = () => {
      if (this.state.users) {
        return (
          <UsersTable
            users={this.state.users}
            createUser={this.createUserAndSetToState}/>
        )
      }
    }

    return (
      <div>
        <h1>Users</h1>
        {usersTable()}
      </div>
    )
  }
}

export default UsersComponent

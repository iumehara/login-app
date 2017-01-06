import React from 'react'
import { render } from 'react-dom'
import UsersTable from './UsersTable'

class UsersComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {users: []}
  }

  getUsers() {
    return fetch('http://localhost:8080/users', {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer token'
        }
    }).then((response) => {
        return response.json()
      })
  }

  getUsersAndSetToState() {
    return this.getUsers().then((users) => {
        this.setState({users})
      })
  }

  componentWillMount() {
    this.getUsersAndSetToState()
  }

  render() {
    return (
      <UsersTable users={this.state.users}/>
    )
  }
}

export default UsersComponent

import React from 'react'
import { render } from 'react-dom'
import UsersTable from './UsersTable'
import { get } from './fetcher'

class UsersComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {users: []}
  }

  getUsers() {
    return get('users')
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
